package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.dao.ProdutoDAO;
import br.edu.ifsul.modelo.Telefone;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

@Named(value = "controlePessoa")
@SessionScoped
public class ControlePessoa implements Serializable {

    @EJB
    private PessoaDAO<Pessoa> dao;
    private Pessoa objeto;
    private Boolean editando;
    
    private Boolean editandoTelefone;
    private Telefone telefone;

   @EJB
    private ProdutoDAO<Produto> daoProduto;
    private Boolean editandoDesejo;
    private Produto produto;
  

    public ControlePessoa() {
        editando = false;
        editandoTelefone = false;
        editandoDesejo =false;
       
    }

    public String listar() {
        editando = false;
        return "/privado/pessoa/listar?faces-redirect=true";
    }

    public void novo() {
        editando = true;
        editandoTelefone = false;
        editandoDesejo =false;
        objeto = new Pessoa();
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoTelefone = false;
            editandoDesejo =false;
            } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.geMensagemErro(e));
        }

    }
    
    
    public void excluir(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro a remover objeto: " + Util.geMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Sucesso ao persistir objeto");
            editando = false;
            editandoTelefone = false;
            editandoDesejo =false;
            } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir: " + Util.geMensagemErro(e));
        }
    }

    public void novoTelefone() {
        telefone = new Telefone();
        editandoTelefone = true;
    }

    public void salvarTelefone() {
        if (telefone.getId() == null) {
            objeto.adicionarTelefone(telefone);
        }
        editandoTelefone = false;
        Util.mensagemInformacao("Telefone persistido com sucesso!");
    }

    public void alterarTelefone(int index) {
        telefone = objeto.getTelefones().get(index);
        editandoTelefone = true;
    }

    public void excluirTelefone(int index) {
        objeto.removerTelefone(index);
        Util.mensagemInformacao("Telefone removido com sucesso!");
    }
 
    public void novoDesejo() {
        editandoDesejo = true;
    }

    public void salvarDesejo() {
        objeto.getProdutos().add(produto);
        editandoDesejo = false;
        Util.mensagemInformacao("Desejo adicionado com sucesso!");
    }

    public void excluirDesejo(Produto obj) {
        objeto.getProdutos().remove(obj);
        Util.mensagemInformacao("Desejo removido com sucesso!");
    }

    public Pessoa getObjeto() {
        return objeto;
    }

    public void setObjeto(Pessoa objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public PessoaDAO<Pessoa> getDao() {
        return dao;
    }

    public void setDao(PessoaDAO<Pessoa> dao) {
        this.dao = dao;
    }

    public Boolean getEditandoTelefone() {
        return editandoTelefone;
    }

    public void setEditandoTelefone(Boolean editandoTelefone) {
        this.editandoTelefone = editandoTelefone;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public ProdutoDAO<Produto> getDaoProduto() {
        return daoProduto;
    }

    public void setDaoProduto(ProdutoDAO<Produto> daoProduto) {
        this.daoProduto = daoProduto;
    }

    public Boolean getEditandoDesejo() {
        return editandoDesejo;
    }

    public void setEditandoDesejo(Boolean editandoDesejo) {
        this.editandoDesejo = editandoDesejo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    
}