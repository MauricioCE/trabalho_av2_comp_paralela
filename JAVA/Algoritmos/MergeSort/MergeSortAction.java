package Algoritmos.MergeSort;

import java.util.concurrent.RecursiveAction;

import Common.Helper;

public class MergeSortAction extends RecursiveAction {

    private final int[] array;
    private final int inicio;
    private final int fim;

    public MergeSortAction(int[] array, int inicio, int fim) {
        this.array = array;
        this.inicio = inicio;
        this.fim = fim;
    }

    @Override
    protected void compute() {

        int tamanhoDoProblema = fim - inicio;

        if (tamanhoDoProblema < Helper.LIMITE_SEQUENCIAL) {
            mergeSerial(array, inicio, fim);
        } else if (inicio < fim) {
            int meio = inicio + (fim - inicio) / 2;
            MergeSortAction tarefaEsquerda = new MergeSortAction(array, inicio, meio);
            MergeSortAction tarefaDireita = new MergeSortAction(array, meio + 1, fim);

            invokeAll(tarefaEsquerda, tarefaDireita);
            merge(array, inicio, meio, fim);
        }

    }

    private void mergeSerial(int[] arrayParaOrdenar, int indiceInicio, int indiceFim) {
        if (indiceInicio < indiceFim) {
            int indiceMeio = indiceInicio + (indiceFim - indiceInicio) / 2;

            mergeSerial(arrayParaOrdenar, indiceInicio, indiceMeio);
            mergeSerial(arrayParaOrdenar, indiceMeio + 1, indiceFim);

            merge(arrayParaOrdenar, indiceInicio, indiceMeio, indiceFim);
        }
    }

    // É igual ao do mergesort serial
    private void merge(int[] array, int inicio, int meio, int fim) {
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = array[inicio + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[meio + 1 + j];

        int i = 0, j = 0;
        int k = inicio;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k++] = L[i++];
        }
        while (j < n2) {
            array[k++] = R[j++];
        }
    }
}