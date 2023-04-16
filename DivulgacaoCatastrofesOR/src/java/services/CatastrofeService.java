package services;

import entities.Catastrofe;
import javax.ejb.Stateless;

@Stateless
public class CatastrofeService extends EntityService<Catastrofe> {
    public CatastrofeService() { super(Catastrofe.class); }
}
