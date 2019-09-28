#pragma once
#include "gencarga.h"

class GenCargaImpl : public GenCarga {
 public:
  GenCargaImpl(int cargaMax);
  ~GenCargaImpl();
  int obtenerSigCarga();
};
