package services;

import entities.Noticia;
import javax.ejb.Stateless;

@Stateless
public class NoticiaService extends EntityService<Noticia> {
    public NoticiaService() { super(Noticia.class); }
}
