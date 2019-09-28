package co.edu.eafit.dis.st0257.concurrencia.lavdual;

import java.util.Random;

public class GenCargasImpl implements GenCargas {

    private int cargaMax;
    private Random random;

    public GenCargasImpl(int cargaMax) {

	this.cargaMax = cargaMax;

	random = new Random((int) System.currentTimeMillis());
    }

    public int obtenerSigCarga() {

	return random.nextInt(cargaMax + 1);
    }

    public void establecerCargaMax(int cargaMax) {
	this.cargaMax = cargaMax;
    }

    public int obtenerCargaMax() {
	return this.cargaMax;
    }
}
