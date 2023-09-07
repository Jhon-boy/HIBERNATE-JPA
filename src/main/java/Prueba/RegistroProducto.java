package Prueba;

import Dao.CategoriaDao;
import Dao.ProductoDao;
import Utils.JPAUtils;
import org.example.modelo.Categoria;
import org.example.modelo.Producto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class RegistroProducto {
    public static  void main(String[] args){
     /*   Categoria celulares = new Categoria("CELULARES");

        // esun patro de diseño --> Iniciamos la conexion mediante esta misma conexion
        EntityManager em = JPAUtils.getEntityManager(); // instancia una clase,


        em.getTransaction().begin();
        em.persist(celulares);
        celulares.setNombre("Libros");

      *//*  em.getTransaction().commit(); // envia los valores que fueron configurados*//*
        em.flush(); // hace lo mismo que el Commit pero este tiene un Roll Back
    //        em.close(); // cerramos
        em.clear();

        celulares = em.merge(celulares);
        celulares.setNombre("SOFTWARE");

        em.remove(celulares);
        em.flush();*/

        registroExtracto();
        EntityManager em = JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(em);
        Producto producto = productoDao.findById(1l);
        System.out.println(producto.getNombre() +  "==> ENCONTRADO");

        //Busqueda por Id y todos  // Waooo
//        List<Producto> productos = productoDao.findAll();
//        productos.forEach(produc -> System.out.println(produc.getDescripcion()));

        // Consulta por nombre
        /* List<Producto> products = productoDao.consulByName("ONE PLUS");
        products.forEach(produc -> System.out.println(produc.getNombre()));
*/
        // cONSULTA POR CATEGORIA
/*
        List<Producto> products = productoDao.consutByCategory("Ceulares");
        products.forEach(produc -> System.out.println(produc.getNombre() + " ==> categoria"));*/

        // consulta por precio
        BigDecimal products = productoDao.consultarPrecioByNombre("ONE PLUS");
        System.out.println("PRECIO: " + products);
    }

    private static void registroExtracto() {
        Categoria ceulares  = new Categoria("Ceulares");
        Categoria Tablets  = new Categoria("Tablets");
        Categoria Teles  = new Categoria("Teles");

        Producto celular = new Producto("ONE PLUS", "CELULAR ALTA GAMA", new BigDecimal("800"), ceulares);
        Producto tablet = new Producto("HUAWEI", "TABLET", new BigDecimal("600"), Tablets);
        Producto televisores = new Producto("LG", "TELEVISOR ALTA GAMA", new BigDecimal("400"), Teles);

        // Iniciamos creando las instancias
        EntityManager em = JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.guardar(ceulares);
        categoriaDao.guardar(Teles);
        categoriaDao.guardar(Tablets);

        productoDao.Guardar(celular);
        productoDao.Guardar(tablet);
        productoDao.Guardar(televisores);


        em.getTransaction().commit();
        em.close();
    }
}
