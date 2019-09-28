#pragma once

class GenCarga {
 private:
  int cargaMax;
 public:
  GenCarga(int cargaMax);
  virtual ~GenCarga();
  virtual int obtenerSigCarga() = 0;
  void establecerCargaMax(int cargaMax);
  int obtenerCargaMax();
};
