package Algoritmos.CountingSort;

import java.util.concurrent.RecursiveAction;

public class CountSortAction extends RecursiveAction {

    private final int[] input;
    private final int start;
    private final int end;
    private final int[] arrayDeContagem;
    private final int offsetDoValorMin;

    public CountSortAction(int[] input, int inicio, int fim, int[] arrayDeContagem, int offsetDoValorMin) {
        this.input = input;
        this.start = inicio;
        this.end = fim;
        this.arrayDeContagem = arrayDeContagem;
        this.offsetDoValorMin = offsetDoValorMin;
    }

    @Override
    protected void compute() {
        int tamanho = end - start;

        if (tamanho <= 1000) {
            for (int i = start; i < end; i++) {
                arrayDeContagem[input[i] - offsetDoValorMin]++;
            }

            return;
        }

        int mid = start + tamanho / 2;
        CountSortAction left = new CountSortAction(input, start, mid, arrayDeContagem, offsetDoValorMin);
        CountSortAction right = new CountSortAction(input, mid, end, arrayDeContagem, offsetDoValorMin);
        invokeAll(left, right);
    }
}
