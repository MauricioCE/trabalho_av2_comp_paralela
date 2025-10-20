package Algoritmos.Base;

public abstract class SorteadorParalelo extends SorteadorBase {
    public abstract void sort(int[] array, int quantThreads) throws Exception;
}
