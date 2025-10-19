package Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Helper {
    public static void PrintMetricas(double duracaoEmMillis, String nomeDoMetodo) {
        System.out.println("\n--- " + nomeDoMetodo + "---");
        System.out.println("Em Milissegundos: " + String.format("%.6f", duracaoEmMillis) + " ms");
        System.out.println("-----------------------------------------------------");
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int[] criarArray(int quantidade) {
        if (quantidade <= 0) {
            return new int[0];
        }

        List<Integer> list = new ArrayList<>(quantidade);
        for (int i = 0; i < quantidade; i++) {
            list.add(i + 1);
        }

        Collections.shuffle(list);

        int[] array = new int[quantidade];
        for (int i = 0; i < quantidade; i++) {
            array[i] = list.get(i);
        }

        return array;
    }
}
