package com.alexb.iterators;

import java.util.*;

/**
 * Generates k-length subsequences of elements from input collection.
 * <p>
 * The combination tuples are emitted in lexicographic ordering according
 * to the order of the input collection. So, if the input collection is
 * sorted, the combination tuples will be produced in sorted order.
 * <p>
 * ([A, B, C, D], 2) &rarr; AB AC AD BC BD CD <br>
 * ([0, 1, 2, 3], 3) &rarr; 012 013 023 123
 */
public class CombinationsIterator<T> implements Iterator<List<T>> {
    private final T[] items;
    private final int k;
    private final int n;
    private final int[] indices;
    private List<T> combination;

    /**
     * @param items the input collection
     * @param k     size of the combination tuple
     */
    @SuppressWarnings({"unchecked"})
    public CombinationsIterator(Collection<T> items, int k) {
        this.items = (T[]) items.toArray();
        this.k = k;
        n = items.size();
        indices = new int[k];
        for (int i = 0; i < k; i++) {
            indices[i] = i;
        }
        if (!items.isEmpty() && k > 0 && k <= n) {
            combination = getCombination();
        }
    }

    private List<T> getCombination() {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(items[indices[i]]);
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return combination != null;
    }

    @Override
    public List<T> next() {
        if (combination == null) {
            throw new NoSuchElementException();
        }
        List<T> result = combination;
        combination = null;

        int i = k - 1;
        for (; i >= 0; i--) {
            if (indices[i] != i + n - k) {
                break;
            }
        }

        if (i >= 0) {
            indices[i]++;
            for (int j = i + 1; j < k; j++) {
                indices[j] = indices[j - 1] + 1;
            }
            combination = getCombination();
        }

        return result;
    }
}
