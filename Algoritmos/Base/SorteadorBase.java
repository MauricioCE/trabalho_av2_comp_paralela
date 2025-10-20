package Algoritmos.Base;

import Common.Cronometro;

public abstract class SorteadorBase {

    protected double duracao;
    protected Cronometro cronometro;

    public SorteadorBase() {
        this.cronometro = new Cronometro();
        this.duracao = 0.0;
    }

    public double getDuracao() {
        return this.duracao;
    }

}
