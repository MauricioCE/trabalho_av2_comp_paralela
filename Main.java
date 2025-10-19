import Algoritmos.CountingSort.CountingSortSerial;
import Algoritmos.MergeSort.MergeParalelo;
import Algoritmos.MergeSort.MergeSortSerial;
import Common.Helper;

public class Main {

    public static void main(String[] args) {
        int[] arr = Helper.criarArray(10_000_000);

        MergeSortSerial mergerSortSerial = new MergeSortSerial();
        MergeParalelo mergerSortParalelo = new MergeParalelo(20);

        CountingSortSerial countingSortSerial = new CountingSortSerial();

        try {
            mergerSortSerial.sort(Helper.copiarArray(arr));
            System.out.println(mergerSortSerial.getDuracao());

            mergerSortParalelo.sort(Helper.copiarArray(arr));
            System.out.println(mergerSortParalelo.getDuracao());

            countingSortSerial.sort(Helper.copiarArray(arr));
            System.out.println(countingSortSerial.getDuracao());

        } catch (Exception e) {
            System.out.println("Erro:\n" + e.toString());
        }

    }
}
