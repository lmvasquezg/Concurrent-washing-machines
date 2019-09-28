package co.edu.eafit.dis.st0257.concurrencia.lavdual;

public interface Sincronizador {

    /**
     * arracar - se encarga de iniciar la ejecución de una
     *           lavadora.
     * @param lavadoraID determina quien pone una carga
     * @param cap        determina la capacidad inicial de la lavadora.
     */
    public void arrancar(LavadoraID lavadoraID, int cap);


    /**
     * parar - se encarga de parar la ejecución de una
     *         lavadora.
     * @param lavadoraID determina quien pone un carga.
     */
    public void parar(LavadoraID lavadoraID);
}
