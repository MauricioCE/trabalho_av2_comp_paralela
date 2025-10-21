package Algoritmos.Base;

import Common.TipoDeAlgoritmo;

public abstract class SorteadorParalelo extends SorteadorBase {

    protected int quantThreads;

    public SorteadorParalelo() {
        super(TipoDeAlgoritmo.PARALELO);
        this.quantThreads = Runtime.getRuntime().availableProcessors();
    }

    public void setQuantThreads(int quant) {
        this.quantThreads = quant;
    }

    @Override
    public int getQuantThreads() {
        return this.quantThreads;
    }
}
