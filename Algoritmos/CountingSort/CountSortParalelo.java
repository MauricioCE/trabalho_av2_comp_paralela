package Algoritmos.CountingSort;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import Algoritmos.Base.SorteadorParalelo;
import Common.Helper;

public class CountSortParalelo extends SorteadorParalelo {

    public CountSortParalelo() {
        super();
    }

    public CountSortParalelo(int quantThreads) {
        super(quantThreads);
    }

    @Override
    public String getNome() {
        return "Countsort Paralelo";
    }

    @Override
    public void sort(int[] array) throws Exception {

        if (array == null || array.length <= 1)
            return;

        cronometro.start();

        int[] limites = Helper.encontrarMinimoMaximo(array);
        int valorMinimo = limites[0];
        int valorMaximo = limites[1];
        int tamanhoArray = array.length;
        int tamanhoIntervalo = valorMaximo - valorMinimo + 1;
        int numeroThreads = Math.max(1, Math.min(quantThreads, tamanhoArray));
        int[][] arraysContagemLocais = new int[numeroThreads][tamanhoIntervalo];
        RecursiveAction[] tarefas = new RecursiveAction[numeroThreads];
        int elementosPorParticao = tamanhoArray / numeroThreads;
        int resto = tamanhoArray % numeroThreads;
        int inicioAtual = 0;

        for (int i = 0; i < numeroThreads; i++) {
            int tamanhoParticao = elementosPorParticao + (i < resto ? 1 : 0);
            int fimAtual = inicioAtual + tamanhoParticao;

            tarefas[i] = new CountSortAction(array, inicioAtual, fimAtual, arraysContagemLocais[i], valorMinimo);

            inicioAtual = fimAtual;
        }

        try (ForkJoinPool poolCustomizado = new ForkJoinPool(numeroThreads)) {
            poolCustomizado.invoke(new RecursiveAction() {
                @Override
                protected void compute() {
                    invokeAll(tarefas);
                }
            });

            int[] arrayContagemGlobal = new int[tamanhoIntervalo];
            for (int[] contagemLocal : arraysContagemLocais) {
                for (int i = 0; i < tamanhoIntervalo; i++) {
                    arrayContagemGlobal[i] += contagemLocal[i];
                }
            }

            for (int i = 1; i < tamanhoIntervalo; i++) {
                arrayContagemGlobal[i] += arrayContagemGlobal[i - 1];
            }

            int[] arrayOrdenado = new int[tamanhoArray];

            for (int i = tamanhoArray - 1; i >= 0; i--) {
                int indiceContagem = array[i] - valorMinimo;
                int indiceDestino = arrayContagemGlobal[indiceContagem] - 1;

                arrayOrdenado[indiceDestino] = array[i];
                arrayContagemGlobal[indiceContagem]--;
            }

            System.arraycopy(arrayOrdenado, 0, array, 0, tamanhoArray);

            this.duracao = cronometro.getDuracao();

        } catch (Exception e) {
            throw e;
        }
    }
}
