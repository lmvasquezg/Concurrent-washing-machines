#include "agentevoid.h"
#include "semaphore.h"

sem_t mutex1, mutex2, sync1, sync2 ; 
int sumCargas, contInicio,contFinal;

AgenteVoid::AgenteVoid(GenCarga& genCarga) : Sincronizador(), genCarga(genCarga) {
  sem_init(&mutex1,0,1);
  sem_init(&mutex2,0,1);
  sem_init(&sync1,0,0);
  sem_init(&sync2,0,0);
  sumCargas=0;
  contFinal=0;
  contInicio=0;
}

AgenteVoid::~AgenteVoid() { 
  sem_destroy(&mutex1);
  sem_destroy(&mutex2);
  sem_destroy(&sync1);
  sem_destroy(&sync2);
}

void AgenteVoid::arrancar(LavadoraID lavadoraID, int carga) {
  // Sección critica para contadores.
  sem_wait(&mutex1);
  sumCargas+= carga;
  contInicio++;
  if(contInicio == 1) {
    // Al arrancar la primera espera a la segunda, permitiendo el acceso a la seccion critica.
    sem_post(&mutex1);
    sem_wait(&sync1);
    sem_wait(&mutex1);
  } else  {
    if(sumCargas == genCarga.obtenerCargaMax()){
        // Al arrancar la segunda verifica que se cumpla la condicion para lavar en paralelo, si es así despierta a la primera.
        sem_post(&sync1);
    }
  }
  sem_post(&mutex1);
}

void AgenteVoid::parar(LavadoraID lavadoraID) {
    //  Seccion critica para contadores.
    sem_wait(&mutex2);
    contFinal++;
    if(contFinal==1){
        // Si llega la primera revisa si la otra está en reposo y si es así la despierta y espera.
        if(sumCargas != genCarga.obtenerCargaMax()){
            sem_post(&sync1); 
        }
        sem_post(&mutex2);
        sem_wait(&sync2);
        sem_wait(&mutex2);
    }else {
        // Al llegar la segunda despierta a la primera y reinicia el proceso.
        sem_post(&sync2);
        sumCargas=0;
        contInicio=0;
        contFinal=0;
    }
    sem_post(&mutex2);
}
