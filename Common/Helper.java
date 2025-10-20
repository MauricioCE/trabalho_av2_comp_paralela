package Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Helper {

    // Gera um novo array que é cópia exata do de entrada
    public static int[] copiarArray(int[] array) {
        return Arrays.copyOf(array, array.length);
    }

    public static int encontrarMaximo(int[] array) {
        if (array == null || array.length == 0) {
            return Integer.MIN_VALUE;
        }

        int maiorValor = array[0];

        for (int num : array) {
            if (num > maiorValor) {
                maiorValor = num;
            }
        }

        return maiorValor;
    }

    public static int[] encontrarMinimoMaximo(int[] array) {
        if (array == null || array.length == 0)
            return new int[] { 0, 0 };

        int valorMinimo = array[0];
        int valorMaximo = array[0];
        for (int valor : array) {
            if (valor < valorMinimo)
                valorMinimo = valor;
            if (valor > valorMaximo)
                valorMaximo = valor;
        }
        return new int[] { valorMinimo, valorMaximo };
    }

    // O array gerado é de 1 até quantidade, porém os valores são embaralhados
    public static int[] gerarArrayEmbaralhado(int quantidade) {
        if (quantidade <= 0) {
            return new int[0];
        }

        List<Integer> list = new ArrayList<>(quantidade);
        for (int i = 0; i <= quantidade; i++) {
            list.add(i + 1);
        }

        Collections.shuffle(list);

        int[] array = new int[quantidade];
        for (int i = 0; i < quantidade; i++) {
            array[i] = list.get(i);
        }

        return array;
    }

    public static int[] gerarArrayFixo(int seed, int tamanhoArray, int limiteSuperior) {
        Random random = new Random(seed);
        int[] arrayDeNumeros = new int[tamanhoArray];

        for (int i = 0; i < tamanhoArray; i++) {
            arrayDeNumeros[i] = random.nextInt(limiteSuperior);
        }

        return arrayDeNumeros;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void printMetricas(double duracaoEmMillis, String nomeDoMetodo) {
        System.out.println("\n--- " + nomeDoMetodo + "---");
        System.out.println("Em Milissegundos: " + String.format("%.6f", duracaoEmMillis) + " ms");
        System.out.println("-----------------------------------------------------");
    }

}
