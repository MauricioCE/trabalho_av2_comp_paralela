package Algoritmos.QuickSort;

import java.util.concurrent.ForkJoinPool;
import Algoritmos.Base.SorteadorParalelo;

public class QuickSortParalelo extends SorteadorParalelo {

    public QuickSortParalelo() {
        super();
    }

    @Override
    public String getNome() {
        return "Quicksort Paralelo";
    }

    @Override
    public void sort(int[] array) throws Exception {
        if (array == null || array.length < 2)
            return;

        this.cronometro.start();

        if (quantThreads <= 0)
            quantThreads = 1;

        try (ForkJoinPool pool = new ForkJoinPool(quantThreads)) {
            QuickSortAction mainTask = new QuickSortAction(array, 0, array.length - 1);
            pool.invoke(mainTask);
            this.duracao = this.cronometro.getDuracao();
        } catch (Exception e) {
            throw e;
        }
    }
}
