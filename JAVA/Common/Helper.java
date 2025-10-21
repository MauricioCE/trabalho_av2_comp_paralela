package Common;

import java.util.Arrays;
import java.util.Random;

public class Helper {

    public static final int LIMITE_SEQUENCIAL = 2048;

    // Gera um novo array que é cópia exata do de entrada
    public static int[] copiarArray(int[] array) {
        return Arrays.copyOf(array, array.length);
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

    public static int[] gerarArrayDeInteiros(int seed, int tamanhoArray, int limiteSuperior) {
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
}
