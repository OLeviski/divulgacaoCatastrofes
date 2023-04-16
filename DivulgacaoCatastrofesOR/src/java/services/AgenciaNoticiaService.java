package services;

import entities.AgenciaNoticia;
import javax.ejb.Stateless;

@Stateless
public class AgenciaNoticiaService extends EntityService<AgenciaNoticia> {
    public AgenciaNoticiaService() { super(AgenciaNoticia.class); }
}