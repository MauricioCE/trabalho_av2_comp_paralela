package Algoritmos.CountingSort;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.IntStream;

import Algoritmos.Base.SorteadorParalelo;
import Common.Helper;
import java.util.concurrent.ForkJoinTask;

public class CountSortParalelo extends SorteadorParalelo {

    @Override
    public String getNome() {
        return "Countsort";
    }

    @Override
    public void sort(int[] array) throws Exception, OutOfMemoryError {

        if (array == null || array.length <= 1)
            return;

        cronometro.start();

        int tamanhoArray = array.length;

        if (quantThreads <= 0)
            quantThreads = 1;

        // int numeroThreads = Math.max(1, Math.min(quantThreads, tamanhoArray));
        int numeroThreads = quantThreads;

        try (ForkJoinPool pool = new ForkJoinPool(numeroThreads)) {
            int[] limites = Helper.encontrarMinimoMaximo(array);
            int valorMinimo = limites[0];
            int valorMaximo = limites[1];
            int tamanhoIntervalo = valorMaximo - valorMinimo + 1;
            RecursiveAction[] tarefas = new RecursiveAction[numeroThreads];
            int elementosPorParticao = tamanhoArray / numeroThreads;
            int resto = tamanhoArray % numeroThreads;
            int inicioAtual = 0;
            int[][] arraysContagemLocais = new int[numeroThreads][tamanhoIntervalo];

            for (int i = 0; i < numeroThreads; i++) {
                int tamanhoParticao = elementosPorParticao + (i < resto ? 1 : 0);
                int fimAtual = inicioAtual + tamanhoParticao;

                tarefas[i] = new CountSortAction(array, inicioAtual, fimAtual, arraysContagemLocais[i], valorMinimo);

                inicioAtual = fimAtual;
            }

            pool.invoke(new RecursiveAction() {
                @Override
                protected void compute() {
                    ForkJoinTask.invokeAll(tarefas);
                }
            });

            int[] arrayContagemGlobal = new int[tamanhoIntervalo];

            // for (int[] contagemLocal : arraysContagemLocais) {
            // for (int i = 0; i < tamanhoIntervalo; i++) {
            // arrayContagemGlobal[i] += contagemLocal[i];
            // }
            // }

            pool.invoke(new RecursiveAction() {
                @Override
                protected void compute() {
                    ForkJoinTask.invokeAll(IntStream.range(0, numeroThreads)
                            .mapToObj(i -> new RecursiveAction() {
                                @Override
                                protected void compute() {
                                    for (int j = 0; j < tamanhoIntervalo; j++) {
                                        arrayContagemGlobal[j] += arraysContagemLocais[i][j];
                                    }
                                }
                            }).toList());
                }
            });

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
        } catch (OutOfMemoryError e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
}