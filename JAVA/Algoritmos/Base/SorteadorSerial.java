package Algoritmos.Base;

import Common.TipoDeAlgoritmo;

public abstract class SorteadorSerial extends SorteadorBase {
    public SorteadorSerial() {
        super(TipoDeAlgoritmo.SERIAL);
    }

    public int getQuantThreads() {
        return 1;
    }

}
