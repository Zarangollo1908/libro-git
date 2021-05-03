
package Modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author chencho Marin
 */
public class PruebaContagio extends EventoCovid{
    
    private TipoPrueba prueba;
    private Boolean positivo;

    public PruebaContagio(TipoPrueba prueba, Boolean positivo, LocalDate fecha) {
        this.prueba = prueba;
        this.positivo = positivo;
        super.fecha = fecha;
    }
    
    

    public PruebaContagio() {
    }

    public TipoPrueba getPrueba() {
        return prueba;
    }

    public void setPrueba(TipoPrueba prueba) {
        this.prueba = prueba;
    }

    public Boolean getPositivo() {
        return positivo;
    }

    public void setPositivo(Boolean positivo) {
        this.positivo = positivo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "PRUEBA DE CONTAGIO" + "\nTIPO: " + prueba + ", POSITIVO: " + positivo + ", FECHA PRUEBA: "+super.fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    
    
    
    
}
