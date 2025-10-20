package Metricas;

public class Metrica {
    private String nomeAlgoritmo;
    private String tipoAlgoritmo;
    private double duracao;
    private int tamanhoArray;
    private int threads;

    public Metrica(String nomeAlgoritmo, String tipoAlgoritmo, double duracao, int threads, int tamanhoArray) {
        this.nomeAlgoritmo = nomeAlgoritmo;
        this.tipoAlgoritmo = tipoAlgoritmo;
        this.duracao = duracao;
        this.tamanhoArray = tamanhoArray;
        this.threads = threads;
    }

    public String getNomeAlgoritmo() {
        return nomeAlgoritmo;
    }

    public String getTipoAlgoritmo() {
        return tipoAlgoritmo;
    }

    public double getDuracao() {
        return duracao;
    }

    public int getTamanhoArray() {
        return tamanhoArray;
    }

    public int getThreads() {
        return threads;
    }

}
