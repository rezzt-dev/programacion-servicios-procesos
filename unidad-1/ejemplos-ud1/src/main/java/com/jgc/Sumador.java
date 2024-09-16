package com.jgc;

public class Sumador {
  public static int sumar (int n1, int n2) {
    return (n1 + n2);
  }

  public static void main(String[] args) {
    int n1 = Integer.parseInt(args[0]);
    int n2 = Integer.parseInt(args[1]);

    int suma = Sumador.sumar(n1, n2);
    System.out.println(suma);
    System.out.println("Terminado");
  }
}


