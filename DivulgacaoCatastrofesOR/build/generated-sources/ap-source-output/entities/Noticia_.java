package entities;

import entities.AgenciaNoticia;
import entities.Catastrofe;
import entities.Noticia.Formato;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150916-rNA", date="2023-04-14T21:35:25")
@StaticMetamodel(Noticia.class)
public class Noticia_ { 

    public static volatile SingularAttribute<Noticia, AgenciaNoticia> agenciaNoticia;
    public static volatile SingularAttribute<Noticia, Formato> formato;
    public static volatile ListAttribute<Noticia, Catastrofe> catastrofees;
    public static volatile SingularAttribute<Noticia, String> titulo;
    public static volatile SingularAttribute<Noticia, Boolean> melhorNoticia;
    public static volatile SingularAttribute<Noticia, Long> id;
    public static volatile SingularAttribute<Noticia, Date> dataHora;

}