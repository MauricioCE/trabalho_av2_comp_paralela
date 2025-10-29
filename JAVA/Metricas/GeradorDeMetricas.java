package Metricas;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Algoritmos.Base.SorteadorBase;
import Algoritmos.Base.SorteadorParalelo;
import Algoritmos.CountingSort.CountSortParalelo;
import Algoritmos.CountingSort.CountSortSerial;
import Algoritmos.MergeSort.MergeSortParalelo;
import Algoritmos.MergeSort.MergeSortSerial;
import Algoritmos.QuickSort.QuickSortParalelo;
import Algoritmos.QuickSort.QuickSortSerial;
import Algoritmos.SelectionSort.SelectionSortParalelo;
import Algoritmos.SelectionSort.SelectionSortSerial;
import Common.Cronometro;
import Common.Helper;

public class GeradorDeMetricas {

    private static final boolean MOSTRAR_LOG = false;

    public static void run() {
        Cronometro cronometro = new Cronometro();
        int seed = 6549;

        System.out.println("Trabalhando...");
        cronometro.start();

        // Algoritmos a serem testados
        SorteadorBase[] algoritmos = new SorteadorBase[] {
                new MergeSortSerial(),
                new MergeSortParalelo(),
                new CountSortSerial(),
                new CountSortParalelo(),
                new QuickSortSerial(),
                new QuickSortParalelo(),
                new SelectionSortSerial(),
                new SelectionSortParalelo(),
        };

        int[] quantThreadsArr = { 2, 4, 8, 10, 24 };
        int[] tamanhosArr = { 1_000, 10_000, 100_000, 1_000_000, 10_000_000 };
        // int[] tamanhosArr = { 1000000, 10000000 };

        List<Metrica> resultadorArr = new ArrayList<>(quantThreadsArr.length * tamanhosArr.length * algoritmos.length);

        int count = 0;
        int total = 5 * quantThreadsArr.length * tamanhosArr.length * algoritmos.length;

        for (SorteadorBase algoritmo : algoritmos) {
            for (int i = 0; i < 5; i++) {
                for (int quantThreads : quantThreadsArr) {
                    if (algoritmo instanceof SorteadorParalelo)
                        ((SorteadorParalelo) algoritmo).setQuantThreads(quantThreads);

                    for (int tamanho : tamanhosArr) {
                        int[] arr = Helper.gerarArrayDeInteiros(seed, tamanho, 3 * tamanho + 1);

                        try {
                            Metrica metrica;
                            if (tamanho >= 1_000_000 &&
                                    (algoritmo instanceof SelectionSortParalelo ||
                                            algoritmo instanceof SelectionSortSerial)) {
                                System.out.println("Progresso: " + ++count + "/" + total);
                                continue;
                            }

                            algoritmo.sort(Helper.copiarArray(arr));
                            metrica = new Metrica(algoritmo.getNome(), algoritmo.getTipo().name(),
                                    algoritmo.getDuracao(), algoritmo.getQuantThreads(), tamanho);
                            resultadorArr.add(metrica);

                            System.out.println("Progresso: " + ++count + "/" + total);

                            if (MOSTRAR_LOG) {
                                System.out.println(metrica.getNomeAlgoritmo() + " " + metrica.getTipoAlgoritmo());
                                System.out.println("Duração: " + algoritmo.getDuracao());
                                System.out.println("Quant. Threads: " + algoritmo.getQuantThreads());
                                System.out.println("Tamanho Array: " + tamanho);
                                System.out.println("-------------------------------");
                            }

                        } catch (Exception e) {
                        }
                    }
                }
            }
        }

        // for (int tamanho : tamanhosArr) {
        // int[] arr = Helper.gerarArrayDeInteiros(seed, tamanho, 3 * tamanho + 1);

        // for (int quantThreads : quantThreadsArr) {
        // for (SorteadorBase algoritmo : algoritmos) {
        // if (algoritmo instanceof SorteadorParalelo) {
        // ((SorteadorParalelo) algoritmo).setQuantThreads(quantThreads);
        // }

        // try {
        // algoritmo.sort(Helper.copiarArray(arr));
        // Metrica metrica = new Metrica(algoritmo.getNome(),
        // algoritmo.getTipo().name(),
        // algoritmo.getDuracao(), algoritmo.getQuantThreads(), tamanho);
        // resultadorArr.add(metrica);
        // duracaoTotal += algoritmo.getDuracao();

        // System.out.println(metrica.getNomeAlgoritmo());

        // System.out.println("Duração: " + algoritmo.getDuracao());
        // System.out.println("Quant. Threads: " + quantThreads);
        // System.out.println("Tamanho Array: " + tamanho);
        // System.out.println("Progresso: " + ++count + "/" + total);
        // System.out.println("-------------------------------");

        // } catch (Exception e) {
        // Metrica metrica = new Metrica(algoritmo.getNome(),
        // algoritmo.getTipo().name(),
        // -1, algoritmo.getQuantThreads(), tamanho);
        // resultadorArr.add(metrica);
        // }
        // }
        // }
        // }

        String saida = String.format(Locale.US, "\n=> Duração total dos testes: %.2f segundos\n",
                cronometro.getDuracao() / 1_000.0);
        System.out.println(saida);

        exportarParaCSV(resultadorArr, "metricas_resultados.csv");
    }

    public static void exportarParaCSV(List<Metrica> resultados, String nomeArquivo) {
        if (resultados == null || resultados.isEmpty()) {
            System.out.println("Nenhuma métrica para exportar.");
            return;
        }

        final String NOME_PASTA = "Resultados";

        try {
            Path pastaResultados = Paths.get(NOME_PASTA);

            if (Files.notExists(pastaResultados)) {
                Files.createDirectories(pastaResultados);
                System.out.println("Diretório criado: " + NOME_PASTA);
            }

            Path caminhoCompleto = pastaResultados.resolve(nomeArquivo);

            try (BufferedWriter escritor = Files.newBufferedWriter(
                    caminhoCompleto, StandardCharsets.UTF_8)) {

                escritor.append("Algoritmo;Tipo;Duração (ms);Tamanho do array;Quant. de threads\n");

                for (Metrica metrica : resultados) {
                    String linha = String.format(
                            Locale.US,
                            "%s;%s;%.4f;%d;%d\n",
                            metrica.getNomeAlgoritmo(),
                            metrica.getTipoAlgoritmo(),
                            metrica.getDuracao(),
                            metrica.getTamanhoArray(),
                            metrica.getThreads());
                    escritor.append(linha);
                }

                System.out.println("✅ Dados exportados com sucesso para: " + caminhoCompleto.toString());
            }

        } catch (IOException e) {
            System.err.println("Erro ao exportar o arquivo CSV: " + e.getMessage());
        }
    }
}
