package beans;

import entidade.Queimada;
import java.io.Serializable;
import util.RequestParameters;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import services.QueimadaService;

@Named(value = "queimadaBean")
@ViewScoped
public class QueimadaBean implements Serializable {

    @EJB
    private QueimadaService queimadaService;
    @Inject
    private RequestParameters parameters;
    private Queimada value;

    @PostConstruct
    public void init() {
        String id = parameters.get("id");
        if (id == null) {
            value = new Queimada();
        } else {
            value = queimadaService.find(Long.valueOf(id));
        }
    }

    private boolean consultar;

    public QueimadaService getDiretorService() {
        return queimadaService;
    }

    public void setDiretorService(QueimadaService queimadaService) {
        this.queimadaService = queimadaService;
    }

    public RequestParameters getParameters() {
        return parameters;
    }

    public void setParameters(RequestParameters parameters) {
        this.parameters = parameters;
    }

    public void consultar(Queimada value) {
        setValue(value);
        this.consultar = true;
    }

    public boolean isConsultar() {
        boolean consultar = this.consultar;
        return consultar;
    }

    public void setConsultar(boolean consultar) {
        this.consultar = consultar;
    }

    public Queimada getValue() {
        return value;
    }

    public void setValue(Queimada value) {
        this.value = value;
    }

    public void reset() {
        value = new Queimada();
    }

    public void inserir() {
        this.consultar = false;
        reset();
    }

    public List<Queimada> getAll() {
        return queimadaService.getAll();
    }

    public String save() {
        queimadaService.create(value);
        reset();
        return null;
    }

    public String update() {
        queimadaService.edit(value);
        return null;
    }

    public String delete() {
        queimadaService.remove(value);
        return null;
    }

}
