package com.alexb.iterators;

import java.lang.reflect.Array;
import java.util.*;

public class PartitionsIterator<T> implements Iterator<List<List<T>>> {
    private final List<T> items;
    private int k = 2;
    private Templates templates;
    private Partitions<T> partitions;

    public PartitionsIterator(Collection<T> items) {
        this.items = new ArrayList<>(items);
        partitions = new Partitions<>(this.items, new int[]{items.size()});
    }

    @Override
    public boolean hasNext() {
        return k <= items.size();
    }

    @Override
    public List<List<T>> next() {
        if (k > items.size()) {
            throw new NoSuchElementException();
        }

        List<List<T>> partition = null;

/*
        if (partitions.hasNext()) {
            partition = partitions.next();
        } else if (templates != null && templates.hasNext()) {
            partitions = new Partitions<>(items, templates.next());
            partition = partitions.next();
        } else {
            templates = new Templates(items.size(), k, 1);
            partitions = new Partitions<>(items, templates.next());
            partition = partitions.next();
        }
*/

        if (!partitions.hasNext() && !templates.hasNext()) {
            k++;
        }

        return partition;
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
        private final Map<T, Integer> itemPos;
        private List<CombinationsIterator<T>> parts;
        private List<List<T>> partition;

        public Partitions(List<T> items, int[] template) {
            this.items = items;
            this.template = template;
            itemPos = new HashMap<>(items.size());
            for (int i = 0; i < items.size(); i++) {
                itemPos.put(items.get(i), i);
            }
            if (items.size() != sum(template)) {
                return;
            }
            parts = new ArrayList<>(template.length);
            partition = new ArrayList<>(template.length);
            List<T> pool = new ArrayList<>(items);
            for (int partSize : template) {
                CombinationsIterator<T> it = new CombinationsIterator<>(pool, partSize);
                List<T> part = it.next();
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

            List<List<T>> result = new ArrayList<>(partition);

            for (int i = parts.size() - 1; i >= 0; i--) {
                if (!parts.get(i).hasNext()) {
                    if (i == 0) {
                        // Partition combinations exhausted.
                        partition = null;
                    }
                    continue;
                }

                List<T> pool = new ArrayList<>(items);
                for (int j = 0; j < i; j++) {
                    pool.removeAll(partition.get(j));
                }
                boolean done = true;
                for (
                        CombinationsIterator<T> it = parts.get(i);
                        i < parts.size();
                        i++
                ) {
                    List<T> part = it.next();
                    if (i > 0 && template[i] == template[i - 1]) {
                        // Ensure natural order of parts, thus uniqueness of combinations.
                        while (gt(partition.get(i - 1), part)) {
                            if (it.hasNext()) {
                                part = it.next();
                            } else {
                                part = null;
                                break;
                            }
                        }
                    }
                    if (part != null) {
                        parts.set(i, it);
                        partition.set(i, part);
                        pool.removeAll(part);
                        if (i + 1 < template.length) {
                            it = new CombinationsIterator<>(pool, template[i + 1]);
                        }
                    } else {
                        done = false;
                        break;
                    }
                }
                if (done) {
                    break;
                }
            }

            return result;
        }

        private boolean gt(List<T> a, List<T> b) {
            int i = 0;
            while (i < a.size() && itemPos.get(a.get(i)).equals(itemPos.get(b.get(i)))) {
                i++;
            }
            return itemPos.get(a.get(i)) > itemPos.get(b.get(i));
        }
    }

    public static void main(String[] args) {
        Partitions<Character> partitions = new Partitions<>(
                Arrays.asList('A', 'B', 'C', 'D'),
                new int[]{2, 2}
        );
        while (partitions.hasNext()) {
            System.out.println(partitions.next());
        }
    }
}
