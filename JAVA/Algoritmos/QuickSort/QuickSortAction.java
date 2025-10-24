package Algoritmos.QuickSort;

import java.util.concurrent.RecursiveAction;
import Common.Helper;

public class QuickSortAction extends RecursiveAction {

    private final int[] array;
    private final int inicio;
    private final int fim;

    public QuickSortAction(int[] array, int inicio, int fim) {
        this.array = array;
        this.inicio = inicio;
        this.fim = fim;
    }

    @Override
    protected void compute() {
        if (inicio < fim) {
            int tamanhoDoProblema = fim - inicio;
            if (tamanhoDoProblema < Helper.LIMITE_SEQUENCIAL) {
                quickSortSerial(array, inicio, fim);
            } else {
                int pi = partition(array, inicio, fim);
                QuickSortAction tarefaEsquerda = new QuickSortAction(array, inicio, pi - 1);
                QuickSortAction tarefaDireita = new QuickSortAction(array, pi + 1, fim);
                invokeAll(tarefaEsquerda, tarefaDireita);
            }
        }
    }

    private void quickSortSerial(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int pi = partition(array, inicio, fim);
            quickSortSerial(array, inicio, pi - 1);
            quickSortSerial(array, pi + 1, fim);
        }
    }

    private int partition(int[] array, int inicio, int fim) {
        int pivo = array[fim];
        int i = (inicio - 1);
        for (int j = inicio; j < fim; j++) {
            if (array[j] < pivo) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[fim];
        array[fim] = temp;
        return i + 1;
    }
}
