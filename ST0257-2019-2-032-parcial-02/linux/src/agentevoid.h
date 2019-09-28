#pragma once
#include "sincronizador.h"
#include "gencarga.h"
#include "lavadoraid.h"

class AgenteVoid : public Sincronizador {
 public:
  AgenteVoid();
  ~AgenteVoid();
  void arrancar(LavadoraID lavadoraID, int carga);
  void parar(LavadoraID lavadoraID);
};
