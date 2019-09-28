#include "gencarga.h"

GenCarga::GenCarga(int cargaMax) : cargaMax(cargaMax) { }

GenCarga::~GenCarga() { }

void GenCarga::establecerCargaMax(int cargaMax) {
  this->cargaMax = cargaMax;
}

int GenCarga::obtenerCargaMax() {

  return cargaMax;
}
