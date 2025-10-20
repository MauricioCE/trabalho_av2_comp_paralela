package Algoritmos.Base;

import java.util.concurrent.ForkJoinPool;

public abstract class SorteadorParalelo extends SorteadorBase {

    protected int quantThreads;

    public SorteadorParalelo() {
        super();
        this.quantThreads = ForkJoinPool.commonPool().getParallelism();
    }

    public SorteadorParalelo(int quantThreads) {
        super();
        this.quantThreads = quantThreads <= 0 ? ForkJoinPool.commonPool().getParallelism() : quantThreads;
    }

    public void setQuantThreads(int quant) {
        this.quantThreads = quant;
    }
}
