
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author chencho Marin
 */
public class Pasaporte {
    
    private Usuario usuario;
    private ArrayList<EventoCovid> listaEventosCovid;

    public Pasaporte() {
        this.listaEventosCovid = new ArrayList<EventoCovid>();
    }

    public Pasaporte(Usuario usuario, ArrayList<EventoCovid> listaEventosCovid) {
        this.usuario = usuario;
        this.listaEventosCovid = new ArrayList<EventoCovid>();
    }

    public  Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void añadirEvento (EventoCovid evenco){
        this.listaEventosCovid.add(evenco);
    }
    
      public void eliminarEvento(int pos) {
        this.listaEventosCovid.remove(pos);
    }
      
        public void eliminarTodasPizzas() {
        this.listaEventosCovid.removeAll(this.listaEventosCovid);
    }
        
    public ArrayList<EventoCovid> getListaEventosCovid() {
        return listaEventosCovid;
    }

    public void setListaEventosCovid(ArrayList<EventoCovid> listaEventosCovid) {
        this.listaEventosCovid = listaEventosCovid;
    }

    @Override
    public String toString() {
        return "Pasaporte{" + "usuario=" + usuario + ", listaEventosCovid=" + listaEventosCovid + '}';
    }
    
    
    
}
