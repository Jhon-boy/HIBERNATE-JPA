package Prueba;

import Dao.PedidoDao;
import Utils.JPAUtils;
import org.example.modelo.Pedido;

import javax.persistence.EntityManager;

public class PruebDesempeño {
    public static void main(String[] args) {
        EntityManager em = JPAUtils.getEntityManager();
        PedidoDao pedido = new PedidoDao(em);

        Pedido pedidoCliente =  pedido.consultaPedidoCliente(1l);
        em.close();

        System.out.println(pedidoCliente.getCliente().getNombre());

    }
}
