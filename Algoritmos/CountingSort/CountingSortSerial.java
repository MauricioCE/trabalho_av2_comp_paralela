package Algoritmos.CountingSort;

import Algoritmos.Base.SorteadorBase;

public class CountingSortSerial extends SorteadorBase {

    @Override
    public void sort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        cronometro.start();

        int maiorValor = array[0];
        int[] arrayDeContagem;
        int[] arrayOrdenado;

        // Achando o maior valor
        for (int num : array) {
            if (num > maiorValor) {
                maiorValor = num;
            }
        }

        arrayDeContagem = new int[maiorValor + 1];
        arrayOrdenado = new int[array.length];

        // Fazendo a contagem
        for (int num : array) {
            arrayDeContagem[num]++;
        }

        // Passo de soma do valor do índece anterior ao atual
        for (int i = 1; i < arrayDeContagem.length; i++) {
            arrayDeContagem[i] += arrayDeContagem[i - 1];
        }

        // Ordenando. Quando se começa pelo final, a ordem dos elementos iguais é
        // mantida
        for (int i = array.length - 1; i >= 0; i--) {
            int valor = array[i];
            int posicao = arrayDeContagem[valor] - 1;

            arrayOrdenado[posicao] = valor;
            arrayDeContagem[valor]--;
        }

        // Copiando os dados do array auxiliar (ordenado) para o array recebido como
        // parâmetro
        System.arraycopy(arrayOrdenado, 0, array, 0, array.length);

        this.duracao = cronometro.getDuracao();
    }

}