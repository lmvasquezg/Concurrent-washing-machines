#pragma once
#include "lavadoraid.h"

class Sincronizador {
 public:
  Sincronizador();
  virtual ~Sincronizador();
  virtual void arrancar(LavadoraID lavadoraID, int carga) = 0;
  virtual void parar(LavadoraID lavadoraID) = 0;
};
