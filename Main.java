import Algoritmos.CountingSort.CountSortParalelo;
import Algoritmos.CountingSort.CountSortSerial;
import Algoritmos.MergeSort.MergeParalelo;
import Algoritmos.MergeSort.MergeSortSerial;
import Common.Helper;

public class Main {

    public static void main(String[] args) {
        final int[] arr = Helper.gerarArrayFixo(999, 1_000_000, 1_000_001);
        final int QUANT_THREADS = 1000;

        MergeSortSerial mergerSortSerial = new MergeSortSerial();
        MergeParalelo mergerSortParalelo = new MergeParalelo(QUANT_THREADS);

        CountSortSerial countingSortSerial = new CountSortSerial();
        CountSortParalelo countingSortParalelo = new CountSortParalelo(QUANT_THREADS);

        try {
            mergerSortSerial.sort(Helper.copiarArray(arr));
            System.out.println(mergerSortSerial.getDuracao());

            mergerSortParalelo.sort(Helper.copiarArray(arr));
            System.out.println(mergerSortParalelo.getDuracao());

            countingSortSerial.sort(Helper.copiarArray(arr));
            System.out.println(countingSortSerial.getDuracao());

            countingSortParalelo.sort(Helper.copiarArray(arr));
            System.out.println(countingSortParalelo.getDuracao());

        } catch (Exception e) {
            System.out.println("Erro:\n" + e.toString());
        }

    }
}
