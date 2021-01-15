package com.alexb.iterators;

import java.util.*;

public class PartitionsIterator<T> implements Iterator<List<List<T>>> {
    private final List<T> items;
    private int k = 2;
    private Templates templates;
    private Partitions<T> partitions;

    public PartitionsIterator(Collection<T> items) {
        this.items = new ArrayList<>(items);
        partitions = new Partitions<>(this.items, Collections.singletonList(items.size()));
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
    private static class Partitions<T> implements Iterator<List<List<T>>> {
        private List<T> items;
        private List<Integer> template;

        public Partitions(List<T> items, List<Integer> template) {
            this.items = items;
            this.template = template;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public List<List<T>> next() {
            return null;
        }
    }
}
