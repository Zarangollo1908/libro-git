
package Modelo;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author chenc
 */
public abstract class EventoCovid {
    protected LocalDate fecha;

    public EventoCovid() {
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "EventoCovid{" + "fecha=" + fecha + '}';
    }
    
    
    
}
