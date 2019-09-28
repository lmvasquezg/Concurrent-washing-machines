#include "lavadora.h"
#include <cmath>

Lavadora::Lavadora(LavadoraID lavadoraID,
		   Sincronizador& agenteSin,
		   GenCarga& genCarga,
		   double p) : lavadoraID(lavadoraID),
			       agenteSin(agenteSin),
			       genCarga(genCarga),
			       p(p) { }

Lavadora::~Lavadora() { }

void Lavadora::operator()() {
  int cap = 0;

  for (;;) {
    cap = (int) ::round(genCarga.obtenerSigCarga() * p);
    agenteSin.arrancar(lavadoraID, cap);
    lavar();
    agenteSin.parar(lavadoraID);
  }
}

void Lavadora::lavar() {

}
