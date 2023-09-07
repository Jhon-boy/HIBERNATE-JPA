package Dao;

import org.example.modelo.Producto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProductoDao {
    private EntityManager em;

    //constructor
    public  ProductoDao(EntityManager em) {
        this.em = em;
    }
    //metodo de acceso o guardado de datos
    public void  Guardar(Producto producto){
        this.em.persist(producto);
    }
    // merge = actualizar los datos
    public  void actualizar(Producto producto){
        this.em.merge(producto); // Merge se actualiza
    }
     // meotodo de eliminar
    public void eliminar(Producto producto){
        producto = this.em.merge(producto); // no podemos retornar algo que se elimini, por tanto lo almacenamos en una constante
        this.em.remove(producto); // lo eliminamos
    }
 // busqueda por id
    public Producto findById(Long id){
        return em.find(Producto.class, id); // metodo de busqueda realizada
    }
    public List<Producto> findAll(){
        String jpql = "Select P FROM  Producto as P";
        return em.createQuery(jpql, Producto.class).getResultList(); // Consulta de todos los elementos de la lista
    }
    public  List<Producto> consulByName(String nombre){
        String jpql = "Select P FROM Producto as P WHERE P.nombre=:nombre";
        return  em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList(); //
    }

    public  List<Producto> consutByCategory(String nombre){
        String jpql = "SELECT P FROM Producto as P WHERE P.categoria.nombre ==:nombre";
        return  em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();
    }
    public BigDecimal consultarPrecioByNombre (String nombre){
        /* String jpql = "SELECT  P.precio FROM Producto AS P WHERE P.nombre =:nombre"; // Traemos una unica seleccion*/
        return  em.createQuery("Producto.consultaPrecio", BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }

    public  List<Producto> consultarByParameters(String nombre, BigDecimal precio, LocalDate fecha){
        StringBuilder jplq = new StringBuilder("Select p from Producto as p WHERE 1=1  ");
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
    public  List<Producto> consultaApiCriterias(String nombre, BigDecimal precio, LocalDate fecha){
        CriteriaBuilder buidler=  em.getCriteriaBuilder();
        CriteriaQuery<Producto> query =  buidler.createQuery(Producto.class);
        Root<Producto> from = query.from(Producto.class);

        Predicate filtro = buidler.and();

        StringBuilder jplq = new StringBuilder("Select p from Producto WHERE 1=1  ");
        if(nombre != null && nombre.trim().isEmpty()){
            filtro = buidler.and(filtro, buidler.equal(from.get("nombre"), nombre));
        }
        if(precio != null && precio.equals(new BigDecimal("0"))){
            filtro = buidler.and(filtro, buidler.equal(from.get("precio"), precio));
        }
        if(fecha != null){
            filtro = buidler.and(filtro, buidler.equal(from.get("fechaDeRegistro"), fecha));
        }
        query=  query.where(filtro);
        return em.createQuery(query).getResultList();
    }
}
