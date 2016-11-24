
package br.edu.ifsul.util;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * Instituto Federal Sul-Rio-Grandense Campus Passo Fundo
 */
public class Util {

    public static void mensagemErro(String mensagem) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public static void mensagemInformacao(String mensagem) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public static String geMensagemErro(Exception e) {
        while (e.getCause() != null) {
            e = (Exception) e.getCause();
        }
        String retorno = e.getMessage();
        return retorno;
    }

}	