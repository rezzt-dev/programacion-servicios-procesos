/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciofilosofos;

/**
 *
 * @author rezzt
 */
public class Mesa {
  private final Tenedor[] listTenedores;
  private final Filosofo[] listFilosofos;
  
  public Mesa (int numFilosofos) {
    listTenedores = new Tenedor[numFilosofos];
    listFilosofos = new Filosofo[numFilosofos];
    
    for (int i=0; i<numFilosofos; i++) {
      listTenedores[i] = new Tenedor();
    }
    
    for (int i=0; i<numFilosofos; i++) {
      listFilosofos[i] = new Filosofo(i, listTenedores[i], listTenedores[(i + 1) % numFilosofos]);
    }
  }
  
  public void comenzarCena () {
    for (Filosofo filosofo : listFilosofos) {
      filosofo.start();
    }
  }
}
