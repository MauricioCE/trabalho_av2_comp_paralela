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

    public double getDuracao() {
        if (duracao == 0.0) {
            long tempoFinal = System.nanoTime();
            this.duracao = (double) (tempoFinal - tempoInicial) / 1_000_000.0;
        }
        return duracao;
    }
}
