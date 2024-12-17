package com.jgc.ejercicios.multihilos.manejoBBDD;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Cliente {
  private static final String SERVER_HOST = "localhost";
  private static final int SERVER_PORT = 9876;
  
  public static void main (String[] args) {
    try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
         ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
         ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
      Empleado nuevoEmpleado = new Empleado("Juan Perez", (short) 30, "Calle Falsa 123");
      Operacion operacionCrear = new Operacion(Operacion.Metodo.CREAR, nuevoEmpleado);
      oos.writeObject(operacionCrear);
      int idCreado = (int) ois.readObject();
      System.out.println("Empleado creado con ID: " + idCreado);

      Operacion operacionConsultarAll = new Operacion(Operacion.Metodo.CONSULTAR_ALL, null);
      oos.writeObject(operacionConsultarAll);
      List<Empleado> empleados = (List<Empleado>) ois.readObject();
      empleados.forEach(System.out::println);

      Operacion operacionConsultarEmpleado = new Operacion(Operacion.Metodo.CONSULTAR_EMPLEADO, idCreado);
      oos.writeObject(operacionConsultarEmpleado);
      Empleado empleado = (Empleado) ois.readObject();
      System.out.println("Empleado consultado: " + empleado);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
