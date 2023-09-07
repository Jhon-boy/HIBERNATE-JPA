package Dao;

import org.example.modelo.Categoria;

import javax.persistence.EntityManager;

public class CategoriaDao {
    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em  = em;
    }
    public  void guardar (Categoria categoria){
        this.em.persist(categoria);
    }
// merge actualizamos  -- persist ==> Guarda los datos
    public  void actualizarCategoria (Categoria categoria){
        this.em.merge(categoria);
    }
    public void eliminar(Categoria categoria){
        categoria = this.em.merge(categoria);
        this.em.remove(categoria);
    }
}
