package com.alexb.iterators;

import java.util.*;

/**
 * Generates successive k-length permutations of elements in the collection.
 * <p>
 * The permutation tuples are emitted in lexicographic ordering according
 * to the order of the input collection. So, if the input collection is
 * sorted, the combination tuples will be produced in sorted order.
 * <p>
 * ([A, B, C, D], 2) &rarr; AB AC AD BA BC BD CA CB CD DA DB DC <br>
 * ([0, 1, 2], 3) &rarr; 012 021 102 120 201 210
 */
public class PermutationsIterator<T> implements Iterator<List<T>> {
    private final T[] items;
    private final int k;
    private final int n;
    private final int[] indices;
    private final int[] cycles;
    private int pos;
    private List<T> permutation;

    /**
     * @param items the input collection
     * @param k     size of the permutation tuple
     */
    @SuppressWarnings("unchecked")
    public PermutationsIterator(Collection<T> items, int k) {
        this.items = (T[]) items.toArray();
        this.k = k;
        n = items.size();
        indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        cycles = new int[k];
        for (int i = 0; i < k; i++) {
            cycles[i] = n - i;
        }
        pos = k - 1;
        if (!items.isEmpty() && k > 0 && k <= n) {
            permutation = getPermutation();
        }
    }

    private List<T> getPermutation() {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(items[indices[i]]);
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return permutation != null;
    }

    @Override
    public List<T> next() {
        if (permutation == null) {
            throw new NoSuchElementException();
        }
        List<T> result = permutation;
        permutation = null;

        for (; pos >= 0; pos--) {
            cycles[pos] -= 1;
            if (cycles[pos] == 0) {
                rotateLeft(indices, pos, n - 1);
                cycles[pos] = n - pos;
            } else {
                swap(indices, pos, n - cycles[pos]);
                pos = k - 1;
                permutation = getPermutation();
                break;
            }
        }

        return result;
    }

    private static void rotateLeft(int[] array, int startPos, int endPos) {
        if (startPos == endPos) {
            return;
        }
        int x = array[startPos];
        System.arraycopy(array, startPos + 1, array, startPos, endPos - startPos);
        array[endPos] = x;
    }

    private static void swap(int[] array, int i, int j) {
        int x = array[i];
        array[i] = array[j];
        array[j] = x;
    }
}
