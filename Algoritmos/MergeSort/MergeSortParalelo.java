package Algoritmos.MergeSort;

import java.util.concurrent.ForkJoinPool;
import Algoritmos.Base.SorteadorParalelo;

public class MergeSortParalelo extends SorteadorParalelo {

    public MergeSortParalelo() {
        super();
    }

    public MergeSortParalelo(int quantThreads) {
        super(quantThreads);
    }

    @Override
    public String getNome() {
        return "Mergesort Paralelo";
    }

    @Override
    public void sort(int[] array) throws Exception {
        if (array == null || array.length < 2)
            return;

        this.cronometro.start();

        if (quantThreads <= 0)
            quantThreads = 1;

        try (ForkJoinPool pool = new ForkJoinPool(quantThreads)) {
            MergeSortAction mainTask = new MergeSortAction(array, 0, array.length - 1);
            pool.invoke(mainTask);
            this.duracao = this.cronometro.getDuracao();
        } catch (Exception e) {
            throw e;
        }
    }
}
