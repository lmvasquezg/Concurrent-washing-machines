#include <cstdlib>
#include <iostream>
#include <thread>
#include <string>
#include <cstdlib>
#include <ctime>
#include "lavadora.h"
#include "sincronizador.h"
#include "gencargaimpl.h"
#include "agentevoid.h"

static void usage(const char *progname) {
  std::cerr << "Usage: " << progname << " <maxCarga> p" << std::endl;

  ::exit(EXIT_FAILURE);
}

int toInt(const char*);
double toDouble(const char*);

int
main(int argc, char *argv[]) {

  if (argc != 3) usage(argv[0]);

  srand(time(nullptr));

  int maxload = toInt(argv[1]);
  double p = toDouble(argv[2]);

  GenCarga* genCarga = new GenCargaImpl(maxload);
  Sincronizador* agenteSin = new AgenteVoid();

  Lavadora lavadoraA { LavadoraA, *agenteSin, *genCarga, p };
  Lavadora lavadoraB { LavadoraB, *agenteSin, *genCarga, 1-p };

  std::thread hiloLavA(lavadoraA);
  std::thread hiloLavB(lavadoraB);

  hiloLavA.join();
  return EXIT_SUCCESS;
}

int
toInt(const char* cstr) {
  std::string str { cstr };

  return std::stoi(str);
}

double
toDouble(const char* cstr) {
  std::string str { cstr };

  return std::stod(str);
}
