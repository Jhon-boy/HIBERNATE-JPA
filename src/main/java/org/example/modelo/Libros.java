package org.example.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Libros extends  Producto{
    private String Auto;
    private int paginas;

    private Libros(){

    }

    public Libros(String auto, int paginas) {
        Auto = auto;
        this.paginas = paginas;
    }

    public String getAuto() {
        return Auto;
    }

    public void setAuto(String auto) {
        Auto = auto;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
}
