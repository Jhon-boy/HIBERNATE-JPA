package org.example.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate fecha= LocalDate.now();
    private BigDecimal valorTotal= new BigDecimal("0");

    @ManyToOne(fetch = FetchType.LAZY) // Llama a los datos unicamente cuando sean solicitados
    private Clientes cliente;

   /* @ManyToMany  ---> relacion de muchos a muchos
    @JoinTable(name="item_pedido")*/
    //private List<Producto> productos;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) // uno  a muchos
    private List<ItemsPedido> items = new ArrayList<>();

    // construcor default
    public Pedido() {

    }
    // Constructor donde unicamente instancio el Cliente
    public Pedido(Clientes cliente) {
        this.cliente = cliente;
    }

    public void agregarItems(ItemsPedido item ){
        item.setPedido(this); // agregamos los datos que se encuentren, de esta manera se encuentran las dos tablas
        this.valorTotal = this.valorTotal.add(item.getValor());
        this.items.add(item);
    }
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

}
