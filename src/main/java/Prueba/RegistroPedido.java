package Prueba;

import Dao.CategoriaDao;
import Dao.ClienteDao;
import Dao.PedidoDao;
import Dao.ProductoDao;
import Utils.JPAUtils;
import Vo.RelatorioVentas;
import org.example.modelo.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class RegistroPedido {
    public static  void main(String[] args){
    registroExtracto();
    EntityManager em = JPAUtils.getEntityManager();
    ProductoDao productoDao = new ProductoDao(em);
    Producto producto = productoDao.findById(1l);

        PedidoDao pedidoDao = new PedidoDao(em);
        Clientes cliente = new Clientes("JOHN", "0606253169");
        Pedido pedido = new Pedido(cliente);

        ClienteDao clienteDao = new ClienteDao(em);
        clienteDao.Guardar(cliente);
        pedido.agregarItems(new ItemsPedido(5, producto, pedido));

        em.getTransaction().begin(); // Iniciamoas la transaccion
        pedidoDao.Guardar(pedido);
        em.getTransaction().commit(); // sicroniza los valores con la base de datos

        BigDecimal valorTotal = pedidoDao.ValorTotalVendido();
        System.out.println("VALOR TOTAL ==> = "  + valorTotal);

      /* Imprimimimos primer metodo de datos
      List<Object[]> relatorio =  pedidoDao.relatorioVentas();
       for (Object[] obj : relatorio){
           System.out.println(obj[0]);
           System.out.println(obj[1]);
           System.out.println(obj[2]);
       }*/
        List<RelatorioVentas> relatorio =  pedidoDao.relatorioVentasVO();
       relatorio.forEach(System.out::println);
    }

    private static void registroExtracto() {
        Categoria ceulares  = new Categoria("Ceulares");
        Producto celular = new Producto("ONE PLUS", "CELULAR ALTA GAMA", new BigDecimal("800"), ceulares);

        EntityManager em = JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.guardar(ceulares);
        productoDao.Guardar(celular);

        em.getTransaction().commit();
        em.close();
    }
}
