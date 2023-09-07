package org.example.modelo;

import javax.persistence.*;

@Entity
@Table (name = "clientes")
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded // le decimos que es un embemido o que sera heredado de datos Clientes
   private DatosPersonales  datosPersonales;

    public  Clientes() {

    }

    public Clientes(String nombre, String dni) {
        this.datosPersonales = new DatosPersonales(nombre, dni);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return datosPersonales.getNombre();
    }

    public void setNombre(String nombre) {
        this.datosPersonales.setNombre(nombre);
    }

    public String getDni() {
        return datosPersonales.getCedula();
    }

    public void setDni(String dni) {
        this.datosPersonales.setCedula(dni); // delegamos responsabilidades
    }
}
