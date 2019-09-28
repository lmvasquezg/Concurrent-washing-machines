package co.edu.eafit.dis.st0257.concurrencia.lavdual;

import java.util.Random;

/**
 * Esta clase simula el comportamiento de una de las lavadoras
 * que van a trabajar de manera dual.
 */
public class Lavadora implements Runnable {

    // Agente de sincronización
    private Sincronizador agenteSin;
    private GenCargas genCargas;
    private Random random;
    private double p;
    private long veces;
    private LavadoraID lavadoraID;

    public Lavadora(LavadoraID lavadoraID,
		    Sincronizador agenteSin,
		    GenCargas genCargas,
		    double p) {

	this.lavadoraID = lavadoraID;
	this.agenteSin = agenteSin;
	this.genCargas = genCargas;
	this.random = new Random((int) System.currentTimeMillis());
	this.p = p;
    }

    /**
     * lavar - se encarga de realizar el lavado
     */
    public void lavar() {
	veces++;
	try {
	    Thread.sleep(random.nextInt(2000));
	}
	catch (InterruptedException ie) { }
    }

    /**
     *run se encarga de ejecutar el proyecto
     */
    public void run() {

	int cap = 0;

	while (true) {

	    cap = (int) Math.round(genCargas.obtenerSigCarga() * p);
	    agenteSin.arrancar(lavadoraID, cap);
	    lavar();
	    agenteSin.parar(lavadoraID);
	}
    }

    public long obtenerVeces() {
	return veces;
    }
}
