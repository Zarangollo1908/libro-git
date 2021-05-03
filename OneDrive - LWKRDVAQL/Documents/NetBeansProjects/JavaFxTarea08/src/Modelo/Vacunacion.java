
package Modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author chenc
 */
public class Vacunacion extends EventoCovid{
    private int numDosis;
    private String nombreVacuna;

    public Vacunacion(LocalDate fecha, String nombreVacuna, int numDosis) {
        this.numDosis = numDosis;
        this.nombreVacuna = nombreVacuna;
        super.fecha = fecha;
    }
    
    

    public Vacunacion() {
    }

    public int getNumDosis() {
        return numDosis;
    }

    public void setNumDosis(int numDosis) {
        this.numDosis = numDosis;
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "VACUNACIÓN:" + "\nNº de Dosis: " + numDosis + ", NOMBRE VACUNA: " + nombreVacuna + ", FECHA VACUNACIÓN: "+super.fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    
    
}
