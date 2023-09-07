package Dao;

import org.example.modelo.Clientes;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ClienteDao {
    private EntityManager em;

    //constructor
    public ClienteDao(EntityManager em) {
        this.em = em;
    }
    //metodo de acceso o guardado de datos
    public void  Guardar(Clientes clientes){
        this.em.persist(clientes);
    }
    // merge = actualizar los datos
    public  void actualizar(Clientes clientes){
        this.em.merge(clientes); // Merge se actualiza
    }
     // meotodo de eliminar
    public void eliminar(Clientes pedido){
        pedido = this.em.merge(pedido); // no podemos retornar algo que se elimini, por tanto lo almacenamos en una constante
        this.em.remove(pedido); // lo eliminamos
    }
 // busqueda por id
    public Clientes findById(Long id){
        return em.find(Clientes.class, id); // metodo de busqueda realizada
    }
    public List<Clientes> findAll(){
        String jpql = "Select P FROM  Clientes as P";
        return em.createQuery(jpql, Clientes.class).getResultList(); // Consulta de todos los elementos de la lista
    }
    public  List<Clientes> consulByName(String nombre){
        String jpql = "Select P FROM Clientes as P WHERE P.nombre=:nombre";
        return  em.createQuery(jpql, Clientes.class).setParameter("nombre", nombre).getResultList(); //
    }

    public  List<Clientes> consutByCategory(String nombre){
        String jpql = "SELECT P FROM Clientes as P WHERE P.categoria.nombre ==:nombre";
        return  em.createQuery(jpql, Clientes.class).setParameter("nombre", nombre).getResultList();
    }
    public BigDecimal consultarPrecioByNombre (String nombre){
         String jpql = "SELECT  P.precio FROM Clientes AS P WHERE P.nombre =:nombre"; // Traemos una unica seleccion
        return  em.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }
}
