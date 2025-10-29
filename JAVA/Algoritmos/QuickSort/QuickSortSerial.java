package Algoritmos.QuickSort;

import Algoritmos.Base.SorteadorSerial;

public class QuickSortSerial extends SorteadorSerial {

    public QuickSortSerial() {
        super();
    }

    @Override
    public String getNome() {
        return "Quicksort";
    }

    @Override
    public void sort(int[] array) {
        if (array == null || array.length < 2) {
            System.out.println("Array nulo ou muito pequeno para medir tempo.");
            return;
        }

        this.cronometro.start();
        quickSort(array, 0, array.length - 1);
        this.duracao = this.cronometro.getDuracao();
    }

    private void quickSort(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int pi = partition(array, inicio, fim);
            quickSort(array, inicio, pi - 1);
            quickSort(array, pi + 1, fim);
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
