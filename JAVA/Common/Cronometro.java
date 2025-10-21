package Common;

public class Cronometro {

    private double tempoInicial;

    public static Cronometro novo() {
        return new Cronometro();
    }

    public void start() {
        this.tempoInicial = System.nanoTime();
    }

    public double getDuracao() {
        double tempoFinal = System.nanoTime();
        return (double) (tempoFinal - tempoInicial) / 1_000_000.0;
    }
}
