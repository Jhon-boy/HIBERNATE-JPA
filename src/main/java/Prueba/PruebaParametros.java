package Prueba;

import Dao.CategoriaDao;
import Dao.ProductoDao;
import Utils.JPAUtils;
import org.example.modelo.Categoria;
import org.example.modelo.Producto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PruebaParametros {
    public static void main(String[] args) {
        cargaDatos();
        EntityManager em =  JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(em);

        //List<Producto> resultado =  productoDao.consultarByParameters("HUAWEI", null, null);
        List<Producto> resultado =  productoDao.consultaApiCriterias("HUAWEI", null, null);
        System.out.println("RESULTADO ==> " + resultado.get(0).getNombre());
    }

    private static void cargaDatos() {
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

