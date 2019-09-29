package co.edu.eafit.dis.st0257.concurrencia.lavdual;

public class AgenteVoid implements Sincronizador {

    private GenCargas genCargas;

    int cargaTotal = 0;
    int lavIniciadas = 0;
    int lavParadas = 0;

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
        // ToDo This must be implemented

        System.out.println("Arrancar " + lavadoraID + "   " + cap);
        cargaTotal += cap;
        lavIniciadas++;
        if (lavIniciadas == 1){
            try{
                System.out.println(lavadoraID + " esperando");
                wait();
            }
            catch(InterruptedException ie){ }
        }
        else{
            if (cargaTotal == genCargas.obtenerCargaMax()){
                notify();
                System.out.println("Lavando las dos");
            }
        }
        System.out.println(lavadoraID + " lavando");
        // int max = genCargas.obtenerCargaMax();

        // int carga = max - cap;
        
    }


    /**
     * parar - se encarga de parar la ejecución de una
     *         lavadora.
     * @param lavadoraID determina quien pone un carga.
     */
    public synchronized void parar(LavadoraID lavadoraID) {
        // ToDo This must be implemented
        System.out.println("Parar " + lavadoraID); 
        lavParadas++;
        if (lavParadas == 1){
            if (cargaTotal != genCargas.obtenerCargaMax()){
                notify();
            }
            try{
                // System.out.println(lavadoraID + " parada");
                wait();
            }
            catch(InterruptedException ie){ }
        }
        else{
            notify();
            cargaTotal = 0;
            lavIniciadas = 0;
            lavParadas = 0;
        }
        

    }
}
