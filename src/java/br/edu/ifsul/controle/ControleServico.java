package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ServicoDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Funcionario;
import br.edu.ifsul.modelo.Servico;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;


@Named(value = "controleServico")
@SessionScoped
public class ControleServico implements Serializable {

    @EJB
    private ServicoDAO<Servico> dao;
    private Servico objeto;
    private Boolean editando;
    
    @EJB
    private PessoaDAO<Pessoa> daoPessoa;
    private Boolean editandoFuncionario;
    private Funcionario funcionario;
  
    public ControleServico() {
        editando = false;
        editandoFuncionario = false;
      
    }

    public String listar() {
        editando = false;
        return "/privado/servico/listar?faces-redirect=true";
    }

    public void novo() {
        editando = true;
        editandoFuncionario = false;
        objeto = new Servico();
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoFuncionario = false;
          
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
            editandoFuncionario = false;
            
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir: " + Util.geMensagemErro(e));
        }
    }

    public void novoFuncionario() {
        funcionario = new Funcionario();
        editandoFuncionario = true;
    }

    public void salvarFuncionario() {
        if (funcionario.getId() == null) {
            objeto.adicionarFuncionario(funcionario);
        }
        editandoFuncionario = false;
        Util.mensagemInformacao("Funcionario persistido com sucesso!");
    }

    public void alterarFuncionario(int index) {
        funcionario = objeto.getFuncionarios().get(index);
        editandoFuncionario = true;
    }

    public void excluirFuncionario(int index) {
        objeto.removerFuncionario(index);
        Util.mensagemInformacao("Funcionario removido com sucesso!");
    }
       

    public Servico getObjeto() {
        return objeto;
    }

    public void setObjeto(Servico objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public ServicoDAO<Servico> getDao() {
        return dao;
    }

    public void setDao(ServicoDAO<Servico> dao) {
        this.dao = dao;
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

   
    public Boolean getEditandoFuncionario() {
        return editandoFuncionario;
    }

    public void setEditandoFuncionario(Boolean editandoFuncionario) {
        this.editandoFuncionario = editandoFuncionario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
