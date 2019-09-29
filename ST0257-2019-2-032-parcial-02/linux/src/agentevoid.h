#pragma once
#include "sincronizador.h"
#include "gencarga.h"
#include "gencargaimpl.h"
#include "lavadoraid.h"

class AgenteVoid : public Sincronizador {
private:
  GenCarga& genCarga;
 public:
  AgenteVoid(GenCarga& genCarga);
  ~AgenteVoid();
  void arrancar(LavadoraID lavadoraID, int carga);
  void parar(LavadoraID lavadoraID);
};
