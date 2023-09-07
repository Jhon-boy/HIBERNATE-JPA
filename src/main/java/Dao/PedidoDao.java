package Dao;

import Vo.RelatorioVentas;
import org.example.modelo.Pedido;
import org.example.modelo.Pedido;
import org.example.modelo.Producto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PedidoDao {
    private EntityManager em;

    //constructor
    public PedidoDao(EntityManager em) {
        this.em = em;
    }
    //metodo de acceso o guardado de datos
    public void  Guardar(Pedido pedido){
        this.em.persist(pedido);
    }
    // merge = actualizar los datos
    public  void actualizar(Pedido pedido){
        this.em.merge(pedido); // Merge se actualiza
    }
     // meotodo de eliminar
    public void eliminar(Pedido pedido){
        pedido = this.em.merge(pedido); // no podemos retornar algo que se elimini, por tanto lo almacenamos en una constante
        this.em.remove(pedido); // lo eliminamos
    }
 // busqueda por id
    public Pedido findById(Long id){
        return em.find(Pedido.class, id); // metodo de busqueda realizada
    }
    public List<Pedido> findAll(){
        String jpql = "Select P FROM  Producto as P";
        return em.createQuery(jpql, Pedido.class).getResultList(); // Consulta de todos los elementos de la lista
    }
    public  List<Pedido> consulByName(String nombre){
        String jpql = "Select P FROM Pedido as P WHERE P.nombre=:nombre";
        return  em.createQuery(jpql, Pedido.class).setParameter("nombre", nombre).getResultList(); //
    }

    public  List<Pedido> consutByCategory(String nombre){
        String jpql = "SELECT P FROM Pedido as P WHERE P.categoria.nombre ==:nombre";
        return  em.createQuery(jpql, Pedido.class).setParameter("nombre", nombre).getResultList();
    }
    public BigDecimal consultarPrecioByNombre (String nombre){
         String jpql = "SELECT  P.precio FROM Pedido AS P WHERE P.nombre =:nombre"; // Traemos una unica seleccion
        return  em.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }
    public BigDecimal ValorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) from Pedido p";
        return  em.createQuery(jpql, BigDecimal.class).getSingleResult();

    }
    public  Double valorPromedio(){
        String cadena = "Select AVG(valorTotal) FROM Pedido p";
        return em.createQuery(cadena,Double.class).getSingleResult(); // Indicamos el tipo de dato a devolver despues de la cadena
    }
    public List<Object[]> relatorioVentas(){
        String cadena = "Select producto.nombre, "+
                "SUM(item.cantidad), " +
                "MAX(pedido.fecha)  "
                + "FROM Pedido pedido  " +
                "JOIN pedido.items item  " +
                "JOIN item.producto producto  "
                +"GROUP BY producto.nombre  "  +
                "ORDER BY item.cantidad DESC  ";
        return em.createQuery(cadena, Object[].class).getResultList();
    }
    public List<RelatorioVentas> relatorioVentasVO(){
        String cadena = "Select new Vo.RelatorioVentas(producto.nombre, "+
                "SUM(item.cantidad), " +
                "MAX(pedido.fecha) ) "
                + "FROM Pedido pedido  " +
                "JOIN pedido.items item  " +
                "JOIN item.producto producto  "
                +"GROUP BY producto.nombre  "  +
                "ORDER BY item.cantidad DESC  ";
        return em.createQuery(cadena, RelatorioVentas.class).getResultList();
    }
    public  Pedido  consultaPedidoCliente (Long id){
        String sql = "SELECT p from Pedido p    JOIN FETCH p.cliente WHERE p.id=:id";
        return  em.createQuery(sql, Pedido.class).setParameter("id", id).getSingleResult();

    }
    public  List<Producto> consultarByParameters(String nombre, BigDecimal precio, LocalDate fecha){
        StringBuilder jplq = new StringBuilder("Select p from Producto WHERE 1=1  ");
                if(nombre != null && nombre.trim().isEmpty()){
                    jplq.append("AND p.nombre =:nombre   ");
                }
        if(precio != null && precio.equals(new BigDecimal("0"))){
            jplq.append("AND p.precio =:precio  ");
        }
        if(fecha != null){
            jplq.append("AND p.fechaDeRegistro =:fecha  ");
        }
        TypedQuery<Producto> consulta = em.createQuery(jplq.toString(), Producto.class);
        if(nombre != null && nombre.trim().isEmpty()){
            consulta.setParameter("nombre  ", nombre);
        }
        if(precio != null && precio.equals(new BigDecimal("0"))){
            consulta.setParameter("precio  ", precio);
        }
        if(fecha != null){
            consulta.setParameter("fechaDeRegistro  ", fecha);
        }
        return consulta.getResultList();
    }
}
