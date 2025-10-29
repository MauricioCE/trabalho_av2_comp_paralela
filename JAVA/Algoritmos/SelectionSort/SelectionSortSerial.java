package Algoritmos.SelectionSort;

import Algoritmos.Base.SorteadorSerial;

public class SelectionSortSerial extends SorteadorSerial {

    public SelectionSortSerial() {
        super();
    }

    @Override
    public String getNome() {
        return "Selectionsort";
    }

    @Override
    public void sort(int[] array) {
        if (array == null || array.length < 2) {
            System.out.println("Array nulo ou muito pequeno para medir tempo.");
            return;
        }

        this.cronometro.start();
        selectionSort(array, 0, array.length - 1);
        this.duracao = this.cronometro.getDuracao();
    }

    private void selectionSort(int[] array, int inicio, int fim) {
        for (int i = inicio; i <= fim; i++) {
            int min_idx = i;
            for (int j = i + 1; j <= fim; j++) {
                if (array[j] < array[min_idx]) {
                    min_idx = j;
                }
            }
            int temp = array[min_idx];
            array[min_idx] = array[i];
            array[i] = temp;
        }
    }
}
