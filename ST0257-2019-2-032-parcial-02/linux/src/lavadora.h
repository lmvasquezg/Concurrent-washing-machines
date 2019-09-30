#pragma once
#include "lavadoraid.h"
#include "sincronizador.h"
#include "gencarga.h"

class Lavadora {
 private:
  LavadoraID lavadoraID;
  Sincronizador& agenteSin;
  GenCarga& genCarga;
  double p; 
  long veces;
 public:
  Lavadora(LavadoraID lavadoraID,
	   Sincronizador& agenteSin,
	   GenCarga& genCarga,
	   double p);
  ~Lavadora();
  void operator()(void);
  void lavar();
  long obtenerVeces();
};

