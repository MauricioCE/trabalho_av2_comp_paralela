
import Algoritmos.CountingSort.CountSortParalelo;
import Algoritmos.CountingSort.CountSortSerial;
import Algoritmos.MergeSort.MergeSortParalelo;
import Algoritmos.MergeSort.MergeSortSerial;
import Common.Helper;
import Metricas.GeradorDeMetricas;

public class Main {

    public static void main(String[] args) {
        // teste();
        GeradorDeMetricas.run();
    }

    public static void teste() {
        MergeSortSerial mergeSort = new MergeSortSerial();
        MergeSortParalelo mergeSortParalelo = new MergeSortParalelo();
        CountSortSerial countSort = new CountSortSerial();
        CountSortParalelo countSortParalelo = new CountSortParalelo();

        int[] array = Helper.gerarArrayDeInteiros(66, 2000000, 10000);

        int threads = 4;

        try {

            mergeSort.sort(Helper.copiarArray(array));
            System.out.println("mergeSort" + ": " + mergeSort.getDuracao());

            countSort.sort(Helper.copiarArray(array));
            System.out.println("countSort" + ": " + countSort.getDuracao());

            mergeSortParalelo.setQuantThreads(threads);
            mergeSortParalelo.sort(Helper.copiarArray(array));
            System.out.println("mergeSortParalelo" + ": " + mergeSortParalelo.getDuracao());

            countSortParalelo.setQuantThreads(threads);
            countSortParalelo.sort(Helper.copiarArray(array));
            System.out.println("countSortParalelo" + ": " + countSortParalelo.getDuracao());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
