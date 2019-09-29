#include "agentevoid.h"
#include "semaphore.h"
#include <iostream>
#include <cstdio>

sem_t mutex1, mutex2, sync1, sync2 ; 
int sumCargas, contInicio,contFinal;
using namespace std;

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

  printf("Lavadora %d carga %d \n", lavadoraID, carga);
  sem_wait(&mutex1);
  sumCargas+= carga;
  contInicio++;
  if(contInicio == 1) {
    sem_post(&mutex1);
    sem_wait(&sync1);
    sem_wait(&mutex1);
  } else  {
    if(sumCargas == genCarga.obtenerCargaMax()){
      sem_post(&sync1);
      cout<<"Lavando las dos"<<endl;
    }
  }
  sem_post(&mutex1);
  
  printf("Lavando %d \n", lavadoraID);
}

void AgenteVoid::parar(LavadoraID lavadoraID) {
  sem_wait(&mutex2);
  printf("Parando lavadora %d \n", lavadoraID);
  contFinal++;
  if(contFinal==1){
    if(sumCargas != genCarga.obtenerCargaMax()){
      sem_post(&sync1); 

      printf("Inciiando a %d \n",1-lavadoraID);
    }
    sem_post(&mutex2);
    sem_wait(&sync2);
    sem_wait(&mutex2);
  }else {
    sem_post(&sync2);
    sumCargas=0;
    contInicio=0;
    contFinal=0;
  }
  sem_post(&mutex2);
}
