package Algoritmos.MergeSort;

import java.util.concurrent.ForkJoinPool;

public class MergeParalelo {

    public void sort(int[] array, int numThreads) throws Exception {
        if (array == null || array.length < 2) {
            return;
        }

        if (numThreads <= 0) {
            numThreads = 1;
        }

        try (ForkJoinPool pool = new ForkJoinPool(numThreads)) {
            MergeSortAction mainTask = new MergeSortAction(array, 0, array.length - 1);
            pool.invoke(mainTask);
        } catch (Exception e) {
            throw e;
        }
    }
}
