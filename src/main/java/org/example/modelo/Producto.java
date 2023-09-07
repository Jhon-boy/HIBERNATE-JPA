package org.example.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@SuppressWarnings("all")
@Entity
@Table(name = "productos")
@NamedQuery(name="Producto.consultaPrecio", query = "SELECT  P.precio FROM Producto AS P WHERE P.nombre =:nombre")
@Inheritance(strategy=InheritanceType.JOINED)
public class Producto { // entidad que refiere a la creacion de la base de datos

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  long id; //
        private String nombre; //
    private String descripcion;
    private BigDecimal precio; // un atributo especifico para los precios
    private LocalDate fechaDeRegistro = LocalDate.now(); //

    @ManyToOne(fetch=FetchType.LAZY)
    private Categoria categoria;  // PARA CATEGORIAS

    public Producto(String nombre, String descripcion, BigDecimal precio, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }


    public Producto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
