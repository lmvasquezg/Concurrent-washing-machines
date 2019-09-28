package co.edu.eafit.dis.st0257.concurrencia.lavdual;

public class AgenteVoid implements Sincronizador {

    private GenCargas genCargas;
    public AgenteVoid(GenCargas genCargas) {
	this.genCargas = genCargas;
    }
    /**
     * arracar - se encarga de iniciar la ejecución de una
     *           lavadora.
     * @param lavadoraID determina quien pone una carga
     * @param cap        determina la capacidad inicial de la lavadora.
     */
    public void arrancar(LavadoraID lavadoraID, int cap) {
	// ToDo This must be implemented
    }


    /**
     * parar - se encarga de parar la ejecución de una
     *         lavadora.
     * @param lavadoraID determina quien pone un carga.
     */
    public void parar(LavadoraID lavadoraID) {
	// ToDo This must be implemented
    }
}
