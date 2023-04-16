package services;

import entities.Catastrofe;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class CatastrofeService extends EntityService<Catastrofe> {
    public CatastrofeService() { super(Catastrofe.class); }
    
    public List<Catastrofe> filter(String prefix) {
        prefix += "%";
        Query query = em.createQuery("SELECT catastrofe from Catastrofe catastrofe WHERE catastrofe.area LIKE :prefix");
        query.setParameter("prefix", prefix);
        return query.getResultList();
    }
}
