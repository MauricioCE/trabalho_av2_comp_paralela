package Metricas;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
import Common.Helper;

public class GeradorDeMetricas {

    public static void run() {
        // Semente do teste
        int seed = 6549;

        // Algoritmos a serem testados
        SorteadorBase[] algoritmos = new SorteadorBase[] {
                new MergeSortSerial(),
                new MergeSortParalelo(),
                new CountSortSerial(),
                new CountSortParalelo()
        };

        int[] quantThreadsArr = { 2, 4, 8, 16, 24, 32 };
        // int[] tamanhosArr = { 1000000, 10000000 };
        int[] tamanhosArr = { 1000, 10000, 100000, 1000000, 10000000 };
        List<Metrica> resultadorArr = new ArrayList<>(quantThreadsArr.length * tamanhosArr.length * algoritmos.length);

        for (int tamanho : tamanhosArr) {
            int[] arr = Helper.gerarArrayDeInteiros(seed, tamanho, 3 * tamanho + 1);

            for (int quantThreads : quantThreadsArr) {
                for (SorteadorBase algoritmo : algoritmos) {
                    // System.out.println("*** Testando " + algoritmo.getNome() + " | Tamanho: " +
                    // tamanho
                    // + " | Threads: " + quantThreads + " ***");

                    if (algoritmo instanceof SorteadorParalelo) {
                        ((SorteadorParalelo) algoritmo).setQuantThreads(quantThreads);
                    }

                    try {
                        algoritmo.sort(Helper.copiarArray(arr));
                        Metrica metrica = new Metrica(algoritmo.getNome(), algoritmo.getTipo().name(),
                                algoritmo.getDuracao(), algoritmo.getQuantThreads(), tamanho);
                        resultadorArr.add(metrica);
                        System.out.println(algoritmo.getDuracao());
                    } catch (Exception e) {
                        Metrica metrica = new Metrica(algoritmo.getNome(), algoritmo.getTipo().name(),
                                -1, algoritmo.getQuantThreads(), tamanho);
                        resultadorArr.add(metrica);
                    }
                }
            }
        }

        // Helper.imprimirTabelaMetricas(resultadorArr);
        exportarParaCSV(resultadorArr, "metricas_resultados.csv");
    }

    public static void exportarParaCSV(List<Metrica> resultados, String nomeArquivo) {
        if (resultados == null || resultados.isEmpty()) {
            System.out.println("Nenhuma métrica para exportar.");
            return;
        }

        final String NOME_PASTA = "../Resultados";

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
