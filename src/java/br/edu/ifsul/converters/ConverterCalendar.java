/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converters;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization Instituto Federal Sul-Rio-Grandense Campus Passo Fundo
 */
@FacesConverter(value="converterCalendar")
public class ConverterCalendar implements Converter, Serializable{
  
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Calendar c = Calendar.getInstance();
        try {
        c.setTime(sdf.parse(string));
        } catch (Exception e ){
            return null;
        }
        return c;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Calendar c = (Calendar) o;
        String str = sdf.format(c.getTime());
        //System.out.println("Data string gerada pelo converter: "+str);
        return str;
    }
    
}
