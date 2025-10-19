package Algoritmos.MergeSort;

import java.util.concurrent.ForkJoinPool;

import Algoritmos.Base.SorteadorBase;

public class MergeParalelo extends SorteadorBase {

    private int quantidadeDeThreads;

    public MergeParalelo(int quantidadeDeThreads) {
        super();
        if (quantidadeDeThreads <= 0) {
            this.quantidadeDeThreads = 1;
        } else {
            this.quantidadeDeThreads = quantidadeDeThreads;
        }
    }

    public void sort(int[] array) throws Exception {
        if (array == null || array.length < 2) {
            return;
        }

        try (ForkJoinPool pool = new ForkJoinPool(this.quantidadeDeThreads)) {
            this.cronometro.start();
            MergeSortAction mainTask = new MergeSortAction(array, 0, array.length - 1);
            pool.invoke(mainTask);
            this.duracao = this.cronometro.getDuracao();
        } catch (Exception e) {
            throw e;
        }
    }
}
