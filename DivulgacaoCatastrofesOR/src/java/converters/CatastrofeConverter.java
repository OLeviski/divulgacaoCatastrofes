package converters;

import entities.Catastrofe;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@FacesConverter(forClass = Catastrofe.class)
@Named
public class CatastrofeConverter extends EntityConverter<Catastrofe> {
    
    public CatastrofeConverter() {
        super(Catastrofe.class);
    }
}
