package converters;

import entities.AgenciaNoticia;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@FacesConverter(forClass = AgenciaNoticia.class)
@Named
public class AgenciaNoticiaConverter extends EntityConverter<AgenciaNoticia> {
    
    public AgenciaNoticiaConverter() {
        super(AgenciaNoticia.class);
    }
}