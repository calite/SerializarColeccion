/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dam1campostello_daniel_ejercicio109901clientes;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Daniel
 */
public class DAM1CamposTello_Daniel_Ejercicio109901Clientes {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Clientes cl = new Clientes();
    while (true) {
      System.out.println("--MENU--");
      System.out.println("1-Añadir cliente");
      System.out.println("2-Listar clientes");
      System.out.println("3-Buscar clientes");
      System.out.println("4-Borrar cliente");
      System.out.println("5-Borrar fichero de clientes completamente");
      System.out.println("6-Salir de la aplicación");
      int opcion = sc.nextInt();
      switch (opcion) {
        case 1:
          cl.add();
          break;
        case 2:
          cl.list();
          break;
        case 3:
          cl.search();
          break;
        case 4:
          cl.remove();
          break;
        case 5:
          cl.removeAll();
          break;
        case 6:
          System.out.println("Ha salido de la aplicación");
          return;
        default:
          System.out.println("Opcion no valida");
      }
    }
  }

}

class Clientes {

  private ArrayList<Cliente> al;
  private File fichero;

  public Clientes() {
    al = new ArrayList<>();
    fichero = new File("clientes.dat");
    if (fichero.exists()) {
      try {
        al = (ArrayList<Cliente>) new ObjectInputStream(new FileInputStream(fichero)).readObject();
      } catch (Exception e) {
      }
    }
  }

  public void add() {
    Cliente c = new Cliente();
    Scanner sc = new Scanner(System.in);
    System.out.println("Introduce NIF");
    c.setNif(sc.next());
    System.out.println("Introduce Nombre");
    c.setNombre(sc.next());
    System.out.println("Introduce Teléfono");
    c.setTelefono(sc.next());
    System.out.println("Introduce Dirección");
    c.setDireccion(sc.next());
    System.out.println("Introduce Deuda");
    c.setDeuda(sc.next());
    al.add(c);
    try {
      new ObjectOutputStream(new FileOutputStream(fichero)).writeObject(al);
    } catch (Exception e) {
    }
  }

  public void list() {
    for (Cliente c : al) {
      System.out.println(c);
    }
  }

  public void search() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Introduce NIF");
    String nif = sc.next();
    for (Cliente c : al) {
      if (c.getNif().equals(nif)) {
        System.out.println(c);
        return;
      }
    }
    System.out.println("No existe el cliente");
  }

  public void remove() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Introduce NIF");
    String nif = sc.next();
    Iterator<Cliente> it = al.iterator();
    while (it.hasNext()) {
      Cliente c = it.next();
      if (c.getNif().equals(nif)) {
        it.remove();
        try {
          new ObjectOutputStream(new FileOutputStream(fichero)).writeObject(al);
        } catch (Exception e) {
        }
        return;
      }
    }
    System.out.println("No existe el cliente");
  }

  public void removeAll() {
    al.clear();
    fichero.delete();
  }
}

class Cliente implements Serializable {

  private String nif;
  private String nombre;
  private String telefono;
  private String direccion;
  private String deuda;

  public Cliente() {

  }

  public Cliente(String nif, String nombre, String telefono, String direccion, String deuda) {
    this.nif = nif;
    this.nombre = nombre;
    this.telefono = telefono;
    this.direccion = direccion;
    this.deuda = deuda;
  }

  public String getNif() {
    return nif;
  }

  public void setNif(String nif) {
    this.nif = nif;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getDeuda() {
    return deuda;
  }

  public void setDeuda(String deuda) {
    this.deuda = deuda;
  }

  @Override
  public String toString() {
    return nif + " " + nombre + " " + telefono + " " + direccion + " " + deuda;
  }
}
