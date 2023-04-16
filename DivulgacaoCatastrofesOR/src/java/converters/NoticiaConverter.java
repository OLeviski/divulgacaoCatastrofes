package converters;

import entities.Noticia;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@FacesConverter(forClass = Noticia.class)
@Named
public class NoticiaConverter extends EntityConverter<Noticia> {
    
    public NoticiaConverter() {
        super(Noticia.class);
    }
}
