package Utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {
    public  static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("tienda");

    public static  EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
    }

}
