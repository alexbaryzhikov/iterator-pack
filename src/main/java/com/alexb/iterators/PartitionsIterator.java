/*
Copyright 2020-present, Alex Baryzhikov.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.alexb.iterators;

import java.util.*;

/**
 * Generates all possible ways to partition elements from input collection.
 * <p>
 * The partition tuples are emitted in lexicographic ordering according
 * to the order of the input collection. So, if the input collection is
 * sorted, the combination tuples will be produced in sorted order.
 * <p>
 * [0, 1] &rarr; (01) (0, 1) <br>
 * [A, B, C] &rarr; (ABC) (AB, C) (AC, B) (BC, A) (A, B, C) <br>
 */
public class PartitionsIterator<T> implements Iterator<List<List<T>>> {
    private final List<T> items;
    private int parts = 1;
    private Templates templates;
    private Partitions<T> partitions;

    public PartitionsIterator(Collection<T> items) {
        this.items = new ArrayList<>(items);
    }

    @Override
    public boolean hasNext() {
        return parts <= items.size();
    }

    @Override
    public List<List<T>> next() {
        if (parts > items.size()) {
            throw new NoSuchElementException();
        }
        if (partitions == null || !partitions.hasNext()) {
            if (templates == null || !templates.hasNext()) {
                templates = new Templates(items.size(), parts++);
            }
            partitions = new Partitions<>(items, templates.next());
        }
        return partitions.next();
    }

    /**
     * Yields partition templates (e.g. [3, 1, 1], where values are part sizes).
     */
    public static class Templates implements Iterator<int[]> {
        private final int p; // number of parts
        private int[] template;

        public Templates(int n, int p) {
            this.p = p;
            if (p > 0 && p <= n) {
                template = new int[p];
                Arrays.fill(template, 1);
                template[0] = n - p + 1;
            }
        }

        @Override
        public boolean hasNext() {
            return template != null;
        }

        @Override
        public int[] next() {
            if (template == null) {
                throw new NoSuchElementException();
            }
            int[] result = Arrays.copyOf(template, template.length);
            int sum = template[p - 1];
            // Find rightmost part to decrement.
            int i = p - 2;
            for (; i > -1; i--) {
                sum += template[i];
                int q = p - i;
                int min = sum % q == 0 ? sum / q : sum / q + 1;
                if (template[i] > min) break;
            }
            if (i == -1) {
                // All parts have minimum value.
                template = null;
            } else {
                template[i] -= 1;
                sum -= template[i++];
                for (; i < p; sum -= template[i++]) {
                    int q = p - i;
                    int value = sum - q + 1;
                    template[i] = Math.min(value, template[i - 1]);
                }
            }
            return result;
        }
    }

    /**
     * Yields all combinations of elements for a partition template.
     */
    public static class Partitions<T> implements Iterator<List<List<T>>> {
        private final List<T> items;
        private final int[] template;
        private final List<Integer> indexes;
        private List<CombinationsIterator<Integer>> parts;
        private List<List<Integer>> partition;

        public Partitions(List<T> items, int[] template) {
            this.items = items;
            this.template = template;
            indexes = new ArrayList<>(items.size());
            for (int i = 0; i < items.size(); i++) {
                indexes.add(i);
            }
            if (items.size() != sum(template)) {
                return;
            }
            parts = new ArrayList<>(template.length);
            partition = new ArrayList<>(template.length);
            List<Integer> pool = new ArrayList<>(indexes);
            for (int i : template) {
                CombinationsIterator<Integer> it = new CombinationsIterator<>(pool, i);
                List<Integer> part = it.next();
                parts.add(it);
                partition.add(part);
                pool.removeAll(part);
            }
        }

        private int sum(int[] array) {
            int result = 0;
            for (int i : array) {
                result += i;
            }
            return result;
        }

        @Override
        public boolean hasNext() {
            return partition != null;
        }

        @Override
        public List<List<T>> next() {
            if (partition == null) {
                throw new NoSuchElementException();
            }
            List<List<T>> result = partitionAsItems();
            int i = parts.size() - 1;
            while (i >= 0 && !updateParts(i)) {
                i--;
            }
            if (i == -1) {
                // Partition combinations exhausted.
                partition = null;
            }
            return result;
        }

        private List<List<T>> partitionAsItems() {
            List<List<T>> result = new ArrayList<>(partition.size());
            for (List<Integer> part : partition) {
                List<T> p = new ArrayList<>(part.size());
                for (int i : part) {
                    p.add(items.get(i));
                }
                result.add(p);
            }
            return result;
        }

        private boolean updateParts(int from) {
            if (!parts.get(from).hasNext()) {
                return false;
            }
            List<Integer> pool = new ArrayList<>(indexes);
            for (int i = 0; i < from; i++) {
                pool.removeAll(partition.get(i));
            }
            CombinationsIterator<Integer> it = parts.get(from);
            for (int i = from; i < template.length; i++) {
                List<Integer> part = it.next();
                if (i > 0 && template[i] == template[i - 1]) {
                    // Ensure natural order of parts, thus uniqueness of combinations.
                    while (gt(partition.get(i - 1), part)) {
                        if (it.hasNext()) {
                            part = it.next();
                        } else {
                            return false;
                        }
                    }
                }
                parts.set(i, it);
                partition.set(i, part);
                if (i + 1 < template.length) {
                    pool.removeAll(part);
                    it = new CombinationsIterator<>(pool, template[i + 1]);
                }
            }
            return true;
        }

        private boolean gt(List<Integer> a, List<Integer> b) {
            int size = Math.min(a.size(), b.size());
            int i = 0;
            while (i < size && a.get(i).equals(b.get(i))) {
                i++;
            }
            return i == size ? a.size() > b.size() : a.get(i) > b.get(i);
        }
    }
}