package entities;

import entities.Catastrofe.Catastrofes;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150916-rNA", date="2023-03-22T13:35:04")
@StaticMetamodel(Catastrofe.class)
public class Catastrofe_ { 

    public static volatile SingularAttribute<Catastrofe, String> area;
    public static volatile SingularAttribute<Catastrofe, Catastrofes> catastrofes;
    public static volatile SingularAttribute<Catastrofe, Boolean> criminal;
    public static volatile SingularAttribute<Catastrofe, Long> id;
    public static volatile SingularAttribute<Catastrofe, Date> dataCatastrofe;

}