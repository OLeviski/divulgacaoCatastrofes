package converters;

import entidade.Queimada;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@FacesConverter(forClass = Queimada.class)
@Named
public class QueimadaConverter extends EntityConverter<Queimada> {
    
    public QueimadaConverter() {
        super(Queimada.class);
    }
}
