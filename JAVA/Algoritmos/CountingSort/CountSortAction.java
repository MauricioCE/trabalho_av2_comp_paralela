package Algoritmos.CountingSort;

import java.util.concurrent.RecursiveAction;

public class CountSortAction extends RecursiveAction {

    private final int[] input;
    private final int start;
    private final int end;
    private final int[] arrayDeContagemLocal;
    private final int offsetDoValorMin;

    public CountSortAction(int[] input, int inicio, int fim, int[] arrayDeContagemLocal, int offsetDoValorMin) {
        this.input = input;
        this.start = inicio;
        this.end = fim;
        this.arrayDeContagemLocal = arrayDeContagemLocal;
        this.offsetDoValorMin = offsetDoValorMin;
    }

    @Override
    protected void compute() {
        for (int i = start; i < end; i++) {
            arrayDeContagemLocal[input[i] - offsetDoValorMin]++;
        }
    }
}
