package Algoritmos.Base;

import Common.Cronometro;
import Common.TipoDeAlgoritmo;

public abstract class SorteadorBase {

    protected double duracao;
    protected TipoDeAlgoritmo tipo;
    protected Cronometro cronometro;

    public SorteadorBase(TipoDeAlgoritmo tipo) {
        this.cronometro = new Cronometro();
        this.duracao = 0.0;
        this.tipo = tipo;
    }

    public abstract int getQuantThreads();

    public abstract String getNome();

    public double getDuracao() {
        return this.duracao;
    }

    public TipoDeAlgoritmo getTipo() {
        return this.tipo;
    }

    public abstract void sort(int[] array) throws Exception, OutOfMemoryError;

}
