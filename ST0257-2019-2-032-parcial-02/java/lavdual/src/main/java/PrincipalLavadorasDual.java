import co.edu.eafit.dis.st0257.concurrencia.lavdual.Lavadora;
import co.edu.eafit.dis.st0257.concurrencia.lavdual.Sincronizador;
import co.edu.eafit.dis.st0257.concurrencia.lavdual.GenCargas;
import co.edu.eafit.dis.st0257.concurrencia.lavdual.LavadoraID;
import co.edu.eafit.dis.st0257.concurrencia.lavdual.AgenteVoid;
import co.edu.eafit.dis.st0257.concurrencia.lavdual.GenCargasImpl;

public class PrincipalLavadorasDual {

    public static void usage() {
	System.err.println("Usage: java PricipalLavadoraDual <maxCarga> <p>");
	System.exit(1);
    }

    public static void main(String args[]) {

	if (args.length != 2) {
	    usage();
	}

	int maxCarga = Integer.parseInt(args[0]);
	Double p = Double.parseDouble(args[1]);

	GenCargas genCargas = new GenCargasImpl(maxCarga);

	Sincronizador agenteSin = new AgenteVoid(genCargas);

	Lavadora lavadoraA = new Lavadora(LavadoraID.LavadoraA,
					  agenteSin, genCargas, p);
	Lavadora lavadoraB = new Lavadora(LavadoraID.LavadoraB,
					  agenteSin, genCargas, 1.0 - p);

	Thread hiloLavA = new Thread(lavadoraA);
	Thread hiloLavB = new Thread(lavadoraB);
	hiloLavA.start();
	hiloLavB.start();

	try {
	    hiloLavA.join();
	} catch(InterruptedException ie) { }
    }
}
