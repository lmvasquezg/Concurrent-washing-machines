package co.edu.eafit.dis.st0257.concurrencia.lavdual;

import java.util.Random;

public interface GenCargas {
    public int obtenerSigCarga();
    public void establecerCargaMax(int cargaMax);
    public int obtenerCargaMax();
}
