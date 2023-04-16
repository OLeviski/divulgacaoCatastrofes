package beans;

import entities.Noticia;
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
import services.NoticiaService;

@Named(value = "noticiaBean")
@ViewScoped
public class NoticiaBean implements Serializable {

    @EJB
    private NoticiaService noticiaService;
    @Inject
    private RequestParameters parameters;
    private Noticia value;
    private boolean consultar;
    List<Noticia> noticiaFiltrados;
    private boolean melhorNoticia;

    @PostConstruct
    public void init() {
        String id = parameters.get("id");
        if (id == null) {
            value = new Noticia();
        } else {
            value = noticiaService.find(Long.valueOf(id));
        }
    }

    public List<Noticia> getNoticiaFiltrados() {
        return noticiaFiltrados;
    }

    public void setNoticiaFiltrados(List<Noticia> noticiaFiltrados) {
        this.noticiaFiltrados = noticiaFiltrados;
    }

    public boolean isMelhorNoticia() {
        return melhorNoticia;
    }

    public void setMelhorNoticia(boolean melhorNoticia) {
        this.melhorNoticia = melhorNoticia;
    }

    public NoticiaService getNoticiaService() {
        return noticiaService;
    }

    public void setNoticiaService(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
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

    public void consultar(Noticia value) {
        setValue(value);
        this.consultar = true;
    }

    public Noticia getValue() {
        return value;
    }

    public void setValue(Noticia value) {
        this.value = value;
    }

    public void reset() {
        value = new Noticia();
    }

    public void inserir() {
        reset();
    }

    public List<Noticia> getAll() {
        return noticiaService.getAll();
    }

    public String save() {
        noticiaService.create(value);
        reset();
        return null;
    }

    public String update() {
        noticiaService.edit(value);
        return null;
    }

    public String delete() {
        noticiaService.remove(value);
        return null;
    }

    public SelectItem[] getFormatos(boolean filtrar) {
        SelectItem[] items;
        int length = Noticia.Formato.values().length;
        int n = 0;
        if (filtrar) {
            items = new SelectItem[length + 1];
            items[0] = new SelectItem("", "");
            n++;
        } else {
            items = new SelectItem[length];
        }
        for (Noticia.Formato formato : Noticia.Formato.values()) {
            items[n++] = new SelectItem(formato, formato.getLabel());
        }
        return items;
    }

    public SelectItem[] getOptionsMelhorNoticia() {
        return new SelectItem[]{
            new SelectItem(String.valueOf(""), ""),
            new SelectItem(Boolean.TRUE.toString(), "sim"),
            new SelectItem(Boolean.FALSE.toString(), "não")
        };
    }
     public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }
}
