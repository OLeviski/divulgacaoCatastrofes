package beans;

import entities.AgenciaNoticia;
import entities.AgenciaNoticia;
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
import services.AgenciaNoticiaService;

@Named(value = "agenciaNoticiasBean")
@ViewScoped
public class AgenciaNoticiaBean implements Serializable {

    @EJB
    private AgenciaNoticiaService agenciaNoticiasService;
    @Inject
    private RequestParameters parameters;
    private AgenciaNoticia value;
    private boolean consultar;
    List<AgenciaNoticia> catastrofesFiltrados;

    @PostConstruct
    public void init() {
        String id = parameters.get("id");
        if (id == null) {
            value = new AgenciaNoticia();
        } else {
            value = agenciaNoticiasService.find(Long.valueOf(id));
        }
    }

    public List<AgenciaNoticia> getCatastrofesFiltrados() {
        return catastrofesFiltrados;
    }

    public void setCatastrofesFiltrados(List<AgenciaNoticia> catastrofesFiltrados) {
        this.catastrofesFiltrados = catastrofesFiltrados;
    }

    public AgenciaNoticiaService getAgenciaNoticiaService() {
        return agenciaNoticiasService;
    }

    public void setAgenciaNoticiaService(AgenciaNoticiaService agenciaNoticiasService) {
        this.agenciaNoticiasService = agenciaNoticiasService;
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

    public void consultar(AgenciaNoticia value) {
        setValue(value);
        this.consultar = true;
    }

    public AgenciaNoticia getValue() {
        return value;
    }

    public void setValue(AgenciaNoticia value) {
        this.value = value;
    }

    public void reset() {
        value = new AgenciaNoticia();
    }

    public void inserir() {
        reset();
    }

    public List<AgenciaNoticia> getAll() {
        return agenciaNoticiasService.getAll();
    }

    public String save() {
        agenciaNoticiasService.create(value);
        reset();
        return null;
    }

    public String update() {
        agenciaNoticiasService.edit(value);
        return null;
    }

    public String delete() {
        agenciaNoticiasService.remove(value);
        return null;
    }

    public SelectItem[] getEstadosCivis(boolean filtrar) {
        SelectItem[] items;
        int length = AgenciaNoticia.Formato.values().length;
        int n = 0;
        if (filtrar) {
            items = new SelectItem[length + 1];
            items[0] = new SelectItem("", "");
            n++;
        } else {
            items = new SelectItem[length];
        }
        for (AgenciaNoticia.Formato estado_Civil : AgenciaNoticia.Formato.values()) {
            items[n++] = new SelectItem(estado_Civil, estado_Civil.getLabel());
        }
        return items;
    }
    
    public SelectItem[] getGovernamental(boolean filtrar) {
        SelectItem[] items;

        int n = 0;
        if (filtrar) {
            items = new SelectItem[3];
            items[0] = new SelectItem("", "");
            n++;
        } else 
            items = new SelectItem[2];{
        items[n ++] = new SelectItem("sim", "Sim");
        items[n] = new SelectItem("nao", "Nao");
        }
        return items;
    }

    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }

}