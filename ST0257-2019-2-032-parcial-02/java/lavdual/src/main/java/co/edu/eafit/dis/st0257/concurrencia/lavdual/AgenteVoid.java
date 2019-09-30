package co.edu.eafit.dis.st0257.concurrencia.lavdual;

public class AgenteVoid implements Sincronizador {

    private GenCargas genCargas;

    private int cargaTotal = 0;
    private int lavIniciadas = 0;
    private int lavParadas = 0;

    public AgenteVoid(GenCargas genCargas) {
	    this.genCargas = genCargas;
    }
    /**
     * arracar - se encarga de iniciar la ejecución de una
     *           lavadora.
     * @param lavadoraID determina quien pone una carga
     * @param cap        determina la capacidad inicial de la lavadora.
     */
    public synchronized void arrancar(LavadoraID lavadoraID, int cap) {

        cargaTotal += cap;
        lavIniciadas++;
        if (lavIniciadas == 1){
            try{
                // Si inicia una, se queda esperando a que la otra arranque para revisar si pueden lavar las dos al mismo tiempo.
                wait();
            }
            catch(InterruptedException ie){ }
        }
        else{
            // Al llegar la segunda se revisa si pueden lavar en paralelo, si es así despierta a la primera para continuar juntas.
            if (cargaTotal <= genCargas.obtenerCargaMax()){
                notify();
            }
        }
    }


    /**
     * parar - se encarga de parar la ejecución de una
     *         lavadora.
     * @param lavadoraID determina quien pone un carga.
     */
    public synchronized void parar(LavadoraID lavadoraID) {

        lavParadas++;
        if (lavParadas == 1){
            // Una vez termina la primera, verifica si la otra tambien estaba en reposo, si es así la despierta y la espera. 
            if (cargaTotal > genCargas.obtenerCargaMax()){
                notify();
            }
            try{
                wait();
            }
            catch(InterruptedException ie){ }
        }
        else{
            // Una vez para la segunda lavadora notifica a la primera y reinicia el proceso.
            notify();
            cargaTotal = 0;
            lavIniciadas = 0;
            lavParadas = 0;
        }
    }
}
