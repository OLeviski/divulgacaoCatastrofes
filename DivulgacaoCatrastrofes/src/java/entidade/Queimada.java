package entidade;

public class Queimada {
    private String localizacao; 
    private String area;
    private String ano;
    private String causa;
    private String criminal;
    
    
    public Queimada() {
    
    } 

    public Queimada(String localizacao, String area, String ano, String causa, String criminal) {
        this.localizacao = localizacao;
        this.area = area;
        this.ano = ano;
        this.causa = causa;
        this.criminal = criminal;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getCriminal() {
        return criminal;
    }

    public void setCriminal(String criminal) {
        this.criminal = criminal;
    }
    
    
    
    public String toString(boolean sem_area){
        if(sem_area) return ano + " - " + localizacao + " - " + criminal;
        else return ano + " - " + localizacao + " - " + area + " - " + criminal;
    }
}
