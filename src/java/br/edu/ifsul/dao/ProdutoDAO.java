package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Produto;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;


@Stateful
public class ProdutoDAO<T> extends DAOGenerico<Produto> implements Serializable {

    public ProdutoDAO() {
        super();
        super.setClassePersistente(Produto.class);
        super.setOrdem("nome");
    }
    
   public T getObjectById(String nome) throws Exception {
        return (T) super.getEm().find(Produto.class,nome);
    }  
   
    @Override
    public List<Produto> getListaObjetos() {
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
}
