#include "gencargaimpl.h"
#include <cstdlib>

GenCargaImpl::GenCargaImpl(int cargaMax) : GenCarga(cargaMax) {
}

GenCargaImpl::~GenCargaImpl() { }

int
GenCargaImpl::obtenerSigCarga() {
  return (rand() % obtenerCargaMax()) + 1;
}
