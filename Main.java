import Algoritmos.MergeSort.MergeParalelo;
import Algoritmos.MergeSort.MergeSortSerial;
import Common.Cronometro;
import Common.Helper;

public class Main {

    public static void main(String[] args) {
        Cronometro c = Cronometro.novo();
        int[] arr = Helper.criarArray(10_000_000);
        MergeSortSerial mergerSortSerial = new MergeSortSerial();
        MergeParalelo mergerSortParalelo = new MergeParalelo();

        try {
            c.start();
            mergerSortSerial.sort(arr);
            c.end();
            System.out.println(c.getDuracao());

            c.start();
            mergerSortParalelo.sort(arr, 100);
            c.end();
            System.out.println(c.getDuracao());
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.toString());
        }

    }
}
