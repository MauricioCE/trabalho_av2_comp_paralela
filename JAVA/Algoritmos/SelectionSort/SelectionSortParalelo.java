package Algoritmos.SelectionSort;

import java.util.concurrent.ForkJoinPool;
import Algoritmos.Base.SorteadorParalelo;

public class SelectionSortParalelo extends SorteadorParalelo {

    public SelectionSortParalelo() {
        super();
    }

    @Override
    public String getNome() {
        return "Selection Sort Paralelo";
    }

    @Override
    public void sort(int[] array) throws Exception {
        if (array == null || array.length < 2)
            return;

        this.cronometro.start();

        if (quantThreads <= 0)
            quantThreads = 1;

        try (ForkJoinPool pool = new ForkJoinPool(quantThreads)) {
            selectionSort(array);
            this.duracao = this.cronometro.getDuracao();
        } catch (Exception e) {
            throw e;
        }
    }

    private void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
}
