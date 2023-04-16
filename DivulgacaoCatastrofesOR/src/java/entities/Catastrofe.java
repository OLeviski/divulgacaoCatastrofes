package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Catastrofe implements Serializable, PersistentEntity {

    public enum Catastrofes {
        queimada("queimada"), inundacao("inundacao"), vazamentoNuclear("vazamentoNuclear");
        private final String label;

        private Catastrofes(String label) {
            this.label = label;
        }

        public String getLabel() {
            return this.label;
        }
    };

    private String area;
    private boolean criminal;
    @Enumerated(EnumType.STRING)
    private Catastrofes catastrofes;
    @Temporal(TemporalType.DATE)
    private Date dataCatastrofe;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isCriminal() {
        return criminal;
    }

    public void setCriminal(boolean criminal) {
        this.criminal = criminal;
    }

    public Catastrofes getCatastrofes() {
        return catastrofes;
    }

    public void setCatastrofes(Catastrofes catastrofes) {
        this.catastrofes = catastrofes;
    }

    public Date getDataCatastrofe() {
        return dataCatastrofe;
    }

    public void setDataCatastrofe(Date dataCatastrofe) {
        this.dataCatastrofe = dataCatastrofe;
    }
    
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
        if (!(object instanceof Catastrofe)) {
            return false;
        }
        Catastrofe other = (Catastrofe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return area;
    }

}
