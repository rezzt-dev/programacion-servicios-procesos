/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.servidorBBDD.manage;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author rezzt
 */
@Entity
@Table(name = "EMPLEADO")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
  @NamedQuery(name = "Empleado.findByIdEmpleado", query = "SELECT e FROM Empleado e WHERE e.idEmpleado = :idEmpleado"),
  @NamedQuery(name = "Empleado.findByNombre", query = "SELECT e FROM Empleado e WHERE e.nombre = :nombre"),
  @NamedQuery(name = "Empleado.findByEdad", query = "SELECT e FROM Empleado e WHERE e.edad = :edad"),
  @NamedQuery(name = "Empleado.findByDireccion", query = "SELECT e FROM Empleado e WHERE e.direccion = :direccion")})
public class Empleado implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID_EMPLEADO")
  private Long idEmpleado;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 75)
  @Column(name = "NOMBRE")
  private String nombre;
  @Basic(optional = false)
  @NotNull
  @Column(name = "EDAD")
  private short edad;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 200)
  @Column(name = "DIRECCION")
  private String direccion;

  public Empleado() {
  }

  public Empleado(Long idEmpleado) {
    this.idEmpleado = idEmpleado;
  }

  public Empleado(Long idEmpleado, String nombre, short edad, String direccion) {
    this.idEmpleado = idEmpleado;
    this.nombre = nombre;
    this.edad = edad;
    this.direccion = direccion;
  }

  public Long getIdEmpleado() {
    return idEmpleado;
  }

  public void setIdEmpleado(Long idEmpleado) {
    this.idEmpleado = idEmpleado;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public short getEdad() {
    return edad;
  }

  public void setEdad(short edad) {
    this.edad = edad;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Empleado)) {
      return false;
    }
    Empleado other = (Empleado) object;
    if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.jgc.ejercicios.multihilos.servidorBBDD.Empleado[ idEmpleado=" + idEmpleado + " ]";
  }
  
}
