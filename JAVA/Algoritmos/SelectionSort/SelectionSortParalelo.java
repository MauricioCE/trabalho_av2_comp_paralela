package Algoritmos.SelectionSort;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import Algoritmos.Base.SorteadorParalelo;
import Common.Helper;

public class SelectionSortParalelo extends SorteadorParalelo {

    public SelectionSortParalelo() {
        super();
    }

    @Override
    public String getNome() {
        return "Selectionsort";
    }

    @Override
    public void sort(int[] array) throws Exception {
        if (array == null || array.length < 2)
            return;

        this.cronometro.start();

        if (quantThreads <= 0) {
            quantThreads = Runtime.getRuntime().availableProcessors();
        }

        try (ForkJoinPool pool = new ForkJoinPool(quantThreads)) {
            for (int i = 0; i < array.length - 1; i++) {
                // Encontra o índice do menor elemento no resto do array de forma paralela
                FindMinIndexTask task = new FindMinIndexTask(array, i, array.length - 1);
                int minIndex = pool.invoke(task);

                // Troca o menor elemento encontrado com o elemento atual
                if (minIndex != i) {
                    int temp = array[i];
                    array[i] = array[minIndex];
                    array[minIndex] = temp;
                }
            }
            this.duracao = this.cronometro.getDuracao();
        } catch (Exception e) {
            throw e;
        }
    }

    // Tarefa ForkJoin para encontrar o índice do menor elemento em um subarray
    private static class FindMinIndexTask extends RecursiveTask<Integer> {
        private final int[] array;
        private final int start;
        private final int end;

        public FindMinIndexTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int length = end - start + 1;
            if (length <= Helper.LIMITE_SEQUENCIAL) {
                return findMinIndexSequencial();
            }

            int mid = start + (end - start) / 2;
            FindMinIndexTask leftTask = new FindMinIndexTask(array, start, mid);
            FindMinIndexTask rightTask = new FindMinIndexTask(array, mid + 1, end);

            leftTask.fork(); // Executa a tarefa da esquerda em background
            int rightResult = rightTask.compute(); // Executa a tarefa da direita na thread atual
            int leftResult = leftTask.join(); // Espera pelo resultado da tarefa da esquerda

            // Compara os resultados e retorna o índice do menor valor
            if (array[leftResult] <= array[rightResult]) {
                return leftResult;
            } else {
                return rightResult;
            }
        }

        private int findMinIndexSequencial() {
            int minIndex = start;
            for (int i = start + 1; i <= end; i++) {
                if (array[i] < array[minIndex]) {
                    minIndex = i;
                }
            }
            return minIndex;
        }
    }
}
