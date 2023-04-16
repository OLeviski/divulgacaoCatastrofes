package beans;

import entities.Catastrofe;
import java.io.Serializable;
import util.RequestParameters;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.FlowEvent;
import services.CatastrofeService;

@Named(value = "catastrofeBean")
@ViewScoped
public class CatastrofeBean implements Serializable {

    @EJB
    private CatastrofeService catastrofesService;
    @Inject
    private RequestParameters parameters;
    private Catastrofe value;
    private boolean consultar;
    List<Catastrofe> catastrofesFiltrados;

    @PostConstruct
    public void init() {
        String id = parameters.get("id");
        if (id == null) {
            value = new Catastrofe();
        } else {
            value = catastrofesService.find(Long.valueOf(id));
        }
    }

    public List<Catastrofe> getCatastrofesFiltrados() {
        return catastrofesFiltrados;
    }

    public void setCatastrofesFiltrados(List<Catastrofe> catastrofesFiltrados) {
        this.catastrofesFiltrados = catastrofesFiltrados;
    }

    public CatastrofeService getAmigoService() {
        return catastrofesService;
    }

    public void setAmigoService(CatastrofeService catastrofesService) {
        this.catastrofesService = catastrofesService;
    }

    public RequestParameters getParameters() {
        return parameters;
    }

    public void setParameters(RequestParameters parameters) {
        this.parameters = parameters;
    }

    public boolean isConsultar() {
        boolean consultar = this.consultar;
        return consultar;
    }

    public void setConsultar(boolean consultar) {
        this.consultar = consultar;
    }

    public void consultar(Catastrofe value) {
        setValue(value);
        this.consultar = true;
    }

    public Catastrofe getValue() {
        return value;
    }

    public void setValue(Catastrofe value) {
        this.value = value;
    }

    public void reset() {
        value = new Catastrofe();
    }

    public void inserir() {
        reset();
    }

    public List<Catastrofe> getAll() {
        return catastrofesService.getAll();
    }

    public String save() {
        catastrofesService.create(value);
        reset();
        return null;
    }

    public String update() {
        catastrofesService.edit(value);
        return null;
    }

    public String delete() {
        catastrofesService.remove(value);
        return null;
    }

    public SelectItem[] getCatastrofes(boolean filtrar) {
        SelectItem[] items;
        int length = Catastrofe.Catastrofes.values().length;
        int n = 0;
        if (filtrar) {
            items = new SelectItem[length + 1];
            items[0] = new SelectItem("", "");
            n++;
        } else {
            items = new SelectItem[length];
        }
        for (Catastrofe.Catastrofes catastrofe : Catastrofe.Catastrofes.values()) {
            items[n++] = new SelectItem(catastrofe, catastrofe.getLabel());
        }
        return items;
    }

    public List<Catastrofe> completaCatastrofe(String prefixo) {
        return catastrofesService.filter(prefixo);
    }
}
