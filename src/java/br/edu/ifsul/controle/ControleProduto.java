package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ProdutoDAO;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;


@Named(value = "controleProduto")
@SessionScoped
public class ControleProduto implements Serializable {
    @EJB
    private ProdutoDAO<Produto> dao;
    private Produto objeto;
    private Boolean editando;
    
    public ControleProduto(){        
        editando = false;
    }
    
    public String listar(){
        editando = false;
        return "/privado/produto/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        objeto = new Produto();
    }
    
    public void alterar(String nome){
      try {
            objeto = dao.getObjectById(nome); 
            editando = true;
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.geMensagemErro(e));            
        }                
        
    }
    
    public void excluir(String nome){
       try {
            objeto = dao.getObjectById(nome);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");            
        } catch(Exception e){
            Util.mensagemErro("Erro a remover objeto: "+Util.geMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {            
            dao.persist(objeto);            
            Util.mensagemInformacao("Sucesso ao persistir objeto");  
            editando = false;        
        } catch(Exception e){
            Util.mensagemErro("Erro ao persistir: "+Util.geMensagemErro(e));            
        }                
    }
    
    public Produto getObjeto() {
        return objeto;
    }

    public void setObjeto(Produto objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public ProdutoDAO<Produto> getDao() {
        return dao;
    }

    public void setDao(ProdutoDAO<Produto> dao) {
        this.dao = dao;
    }
}
