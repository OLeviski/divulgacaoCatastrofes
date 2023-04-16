package beans;

import entidade.Queimada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.model.SelectItem;
import javax.sql.DataSource;

@Named(value = "divulgacaoCatrastrofesDBBean")
@RequestScoped

public class DivulgacaoCatrastrofesDBBeans {

    @Resource(lookup = "java:/DivulgacaoCatrastrofesDBDS")
    private DataSource queimadaDataSource;

    private ArrayList<Queimada> queimadas;
    private Queimada queimada;
    private String areaSelecionado;
    private String causaSelecionada;
    private boolean cadastrar;
    private boolean pesquisar;
    private ArrayList<String> mensagensErro;

    @PostConstruct
    public void init() {
        queimadas = new ArrayList();
        queimada = new Queimada();
        mensagensErro = new ArrayList();
        cadastrar = false;
        pesquisar = false;
    }

    public ArrayList<Queimada> getQueimadas() throws SQLException {
        getQueimadasDB();
        return queimadas;
    }

    public void setQueimadas(ArrayList<Queimada> queimadas) {
        this.queimadas = queimadas;
    }

    public Queimada getQueimada() {
        return queimada;
    }

    public void setQueimada(Queimada queimada) {
        this.queimada = queimada;
    }

    public boolean isCadastrar() {
        return cadastrar;
    }

    public void setCadastrar(boolean cadastrar) {
        this.cadastrar = cadastrar;
    }

    public boolean isPesquisar() {
        return pesquisar;
    }

    public void setPesquisar(boolean pesquisar) {
        this.pesquisar = pesquisar;
    }

    public String getAreaSelecionado() {
        return areaSelecionado;
    }

    public void setAreaSelecionado(String areaSelecionado) {
        this.areaSelecionado = areaSelecionado;
    }

    public String getCausaSelecionada() {
        return causaSelecionada;
    }

    public void setCausaSelecionada(String causaSelecionada) {
        this.causaSelecionada = causaSelecionada;
    }

    public String cadastrarQueimada() throws SQLException {
        saveQueimadaDB();
        reset();
        return "index";
    }

    public void pesquisarQueimada() {
        pesquisar = true;
        cadastrar = false;
    }

    public DataSource getQueimadaDataSource() {
        return queimadaDataSource;
    }

    public void setQueimadaDataSource(DataSource queimadaDataSource) {
        this.queimadaDataSource = queimadaDataSource;
    }

    public String getMensagensErro() {
        String mensagens_erro = "";
        for (String mensagem_erro : mensagensErro)
            mensagens_erro += mensagem_erro + " | ";
        return mensagens_erro;
    }

    public void setMensagensErro(ArrayList<String> mensagensErro) {
        this.mensagensErro = mensagensErro;
    }

    public void reset() {
        queimada = new Queimada();
        cadastrar = true;
        pesquisar = false;
    }

    public Connection getConnectionDB() {
        Connection conexao = null;
        if (queimadaDataSource == null) {
            mensagensErro.add("DataSource nao acessivel");
            return null;
        }
        try {
            conexao = queimadaDataSource.getConnection();
        } catch (SQLException exception) {
            mensagensErro.add(exception.getMessage());
        }
        return conexao;
    }

    public void saveQueimadaDB() throws SQLException {
        Connection conexao = getConnectionDB();
        if (conexao == null) {
            return;
        }
        PreparedStatement comando = null;
        try {
            comando = conexao.prepareStatement(
                    "INSERT INTO Queimadas(Localizacao, Area, Ano, Causa, Criminal)" 
                           + "VALUES(?, ?, ?, ?, ?)");
            comando.setString(1, queimada.getLocalizacao());
            comando.setString(2, queimada.getArea());
            comando.setString(3, queimada.getAno());
            comando.setString(4, queimada.getCausa());
            comando.setString(5, queimada.getCriminal());
            comando.executeUpdate();
            comando.close();
        } catch (SQLException exception) {
            if (comando != null) {
                comando.close();
            }
            mensagensErro.add(exception.getMessage());
        }
        conexao.close();
        return;
    }

    public String getQueimadasDB() throws SQLException {
        String proxima_pagina = "";
        ArrayList<Queimada> queimadas = new ArrayList();
        Connection conexao = getConnectionDB();
        if (conexao == null) {
            return "";
        }
        PreparedStatement comando = null;
        ResultSet consultas = null;
        try {
            comando = conexao.prepareStatement(
                    "SELECT Localizacao, Area, Ano, Causa, Criminal FROM Queimadas ORDER BY Ano DESC");
            consultas = comando.executeQuery();
            while (consultas.next()) {
                Queimada queimada = new Queimada(consultas.getString("Localizacao"), consultas.getString("Area"),
                        consultas.getString("Ano"), consultas.getString("Causa"), consultas.getString("Criminal"));
                queimadas.add(queimada);
            }
            consultas.close();
            comando.close();
            proxima_pagina = "index";
        } catch (SQLException exception) {
            if (consultas != null) {
                consultas.close();
            }
            if (comando != null) {
                comando.close();
            }
            mensagensErro.add(exception.getMessage());
        }
        conexao.close();
        this.queimadas = queimadas;
        return proxima_pagina;
    }

    public ArrayList<String> getAreaesDB() throws SQLException {
        ArrayList<String> areaes = new ArrayList();
        Connection conexao = getConnectionDB();
        if (conexao == null) {
            return areaes;
        }
        PreparedStatement comando = null;
        ResultSet consultas = null;
        try {
            comando = conexao.prepareStatement("SELECT Area FROM Queimadas ORDER BY Area");
            consultas = comando.executeQuery();
            while (consultas.next()) {
                String area = consultas.getString("Area");
                int total_areaes = areaes.size();
                if ((total_areaes == 0) || (!area.equals(areaes.get(total_areaes - 1)))) {
                    areaes.add(area);
                }
            }
            consultas.close();
            comando.close();
        } catch (SQLException exception) {
            if (consultas != null) {
                consultas.close();
            }
            if (comando != null) {
                comando.close();
            }
            mensagensErro.add(exception.getMessage());
        }
        conexao.close();
        return areaes;
    }
    
    public ArrayList<String> getCausasDB() throws SQLException {
        ArrayList<String> causas = new ArrayList();
        Connection conexao = getConnectionDB();
        if (conexao == null) {
            return causas;
        }
        PreparedStatement comando = null;
        ResultSet consultas = null;
        try {
            comando = conexao.prepareStatement("SELECT Causa FROM Queimadas ORDER BY Causa");
            consultas = comando.executeQuery();
            while (consultas.next()) {
                String causa = consultas.getString("Causa");
                int total_areaes = causas.size();
                if ((total_areaes == 0) || (!causa.equals(causas.get(total_areaes - 1)))) {
                    causas.add(causa);
                }
            }
            consultas.close();
            comando.close();
        } catch (SQLException exception) {
            if (consultas != null) {
                consultas.close();
            }
            if (comando != null) {
                comando.close();
            }
            mensagensErro.add(exception.getMessage());
        }
        conexao.close();
        return causas;
    }

    public ArrayList<SelectItem> getAreaesItens() throws SQLException {
        ArrayList<SelectItem> itens = new ArrayList();
        for (String area : getAreaesDB()) {
            boolean inserido = false;
            for (int n = 0; n < itens.size(); n++) {
                if (area.compareTo(itens.get(n).getLabel()) > 0) {
                    continue;
                }
                itens.add(n, new SelectItem(area, area));
                inserido = true;
                break;
            }

            if (!inserido) {
                itens.add(new SelectItem(area, area));
            }
        }

        return itens;
    }
    
    public ArrayList<SelectItem> getCausaItens() throws SQLException {
        ArrayList<SelectItem> itens = new ArrayList();
        for (String causa : getCausasDB()) {
            boolean inserido = false;
            for (int n = 0; n < itens.size(); n++) {
                if (causa.compareTo(itens.get(n).getLabel()) > 0) {
                    continue;
                }
                itens.add(n, new SelectItem(causa, causa));
                inserido = true;
                break;
            }

            if (!inserido) {
                itens.add(new SelectItem(causa, causa));
            }
        }

        return itens;
    }

    public ArrayList<String> getInfoQueimadasArea() throws SQLException {
        getInfoQueimadasDB();
        ArrayList<String> info_queimadas_area = new ArrayList();
        for (Queimada queimada : queimadas) {
            String area = queimada.getArea();
            String causa = queimada.getCausa();
            if (area.equals(areaSelecionado) && causa.equals(causaSelecionada)) {
                boolean inserido = false;
                String ano_queimada = queimada.getAno();
                for (int n = 0; n < info_queimadas_area.size(); n++) {
                    String ano = info_queimadas_area.get(n).split(" - ")[0];
                    if (ano_queimada.compareTo(ano) < 0) {
                        continue;
                    }
                    info_queimadas_area.add(n, queimada.toString(true));
                    inserido = true;
                    break;
                }
                if (!inserido) {
                    info_queimadas_area.add(queimada.toString(true));
                }
            }
        }
        return info_queimadas_area;
    }

    public void getInfoQueimadasDB() throws SQLException {
        ArrayList<Queimada> queimadas = new ArrayList();
        Connection conexao = getConnectionDB();
        if (conexao == null) {
            return;
        }
        PreparedStatement comando = null;
        ResultSet consultas = null;
        try {
            comando = conexao.prepareStatement("SELECT * FROM Queimadas WHERE Area = ? && Causa = ? ORDER BY Ano DESC");
            comando.setString(1, areaSelecionado);
            comando.setString(2, causaSelecionada);
            consultas = comando.executeQuery();
            while (consultas.next()) {
                Queimada queimada = new Queimada(consultas.getString("Localizacao"), consultas.getString("Area"),
                        consultas.getString("Ano"), consultas.getString("Causa"),
                        consultas.getString("Criminal"));
                queimadas.add(queimada);
            }
            consultas.close();
            comando.close();
            this.queimadas = queimadas;
        } catch (SQLException exception) {
            if (consultas != null) {
                consultas.close();
            }
            if (consultas != null) {
                comando.close();
            }
            mensagensErro.add(exception.getMessage());
        }
        conexao.close();
        return;

    }
}
