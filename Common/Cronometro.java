package Common;

public class Cronometro {

    private double tempoInicial;
    private double duracao;

    public static Cronometro novo() {
        return new Cronometro();
    }

    public void start() {
        this.tempoInicial = System.nanoTime();
    }

    public void end() {
        long tempoFinal = System.nanoTime();
        this.duracao = (double) (tempoFinal - tempoInicial) / 1_000_000.0;
    }

    public double getDuracao() {
        return duracao;
    }

}
