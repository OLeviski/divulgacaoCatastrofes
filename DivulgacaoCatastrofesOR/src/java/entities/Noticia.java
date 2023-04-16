package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Noticia implements Serializable, PersistentEntity {

    public enum Formato {
        revista("revista"), jornal("jornal"), site("site"), radio("radio");

        private final String label;

        private Formato(String label) {
            this.label = label;
        }

        public String getLabel() {
            return this.label;
        }
    };
    @Temporal(TemporalType.TIME)
    private Date dataHora = new Date();
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Formato formato;
    private boolean melhorNoticia;
    @ManyToOne
    private AgenciaNoticia agenciaNoticia;
    @ManyToMany
    private List<Catastrofe> catastrofees;
    @OneToMany(mappedBy = "noticia")

    private static final long serialVersionUID = 1L;

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    public boolean isMelhorNoticia() {
        return melhorNoticia;
    }

    public void setMelhorNoticia(boolean melhorNoticia) {
        this.melhorNoticia = melhorNoticia;
    }

    public AgenciaNoticia getAgenciaNoticia() {
        return agenciaNoticia;
    }

    public void setAgenciaNoticia(AgenciaNoticia agenciaNoticia) {
        this.agenciaNoticia = agenciaNoticia;
    }

    public List<Catastrofe> getCatastrofees() {
        return catastrofees;
    }

    public void setCatastrofees(List<Catastrofe> catastrofees) {
        this.catastrofees = catastrofees;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Noticia)) {
            return false;
        }
        Noticia other = (Noticia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public void addCatastrofe(Catastrofe catastrofe) {
        catastrofees.add(catastrofe);
    }
    
    @Override
    public String toString() {
        return titulo;
    } 
}
