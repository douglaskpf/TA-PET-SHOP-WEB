package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Servico;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;


@Stateful
public class ServicoDAO<T> extends DAOGenerico<Servico> implements Serializable {

    public ServicoDAO() {
        super();
        super.setClassePersistente(Servico.class);
        super.setOrdem("nome");
    }
    
   public T getObjectById(String nome) throws Exception {
        return (T) super.getEm().find(Servico.class,nome);
    }  
   
    @Override
    public List<Servico> getListaObjetos() {
        String jpql = "from " + super.getClassePersistente().getSimpleName();
        String where = "";
        if (super.getFiltro().length() > 0) {
            if (super.getOrdem().equals("id")) {
                try {
                    Integer.parseInt(super.getFiltro());
                    where += " where " + super.getOrdem() + " = '" + super.getFiltro() + "' ";
                } catch (Exception e) {

                }
            } else {
                where += " where upper(" + super.getOrdem() + ") like '"
                        + super.getFiltro().toUpperCase() + "%' ";
            }
        }
        jpql += where;
        jpql += " order by " + super.getOrdem();
        super.setTotalObjetos(super.getEm().createQuery("select nome from "+super.getClassePersistente().getSimpleName()+
                where+ " order by "+super.getOrdem()).getResultList().size());
        return super.getEm().createQuery(jpql).setFirstResult(super.getPosicaoAtual()).
                setMaxResults(super.getMaximoObjetos()).getResultList();
    }   
    
     @Override
    public Servico getObjectById(Integer id) throws Exception {
        Servico obj = (Servico) super.getEm().find(super.getClassePersistente(), id);
        obj.getFuncionarios().size();
        return obj;
    }       
}
