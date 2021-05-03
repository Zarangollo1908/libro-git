package Modelo;

import Modelo.EventoCovid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;


public class Usuario implements Cloneable{
    
    private String nombre;
    private String apellidos;
    private String dni;
    private LocalDate fechaNacimiento;
    private String grupo;
    private String sexo;
    private LinkedList <EventoCovid> listaEventos = new LinkedList();

    public Usuario() {
    }

    public Usuario(String nombre, String apellidos, String dni, LocalDate fechaNacimiento, String grupo, String sexo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.grupo = grupo;
        this.sexo = sexo;
    }


  public LinkedList<EventoCovid> getListaEventos() {
        return (LinkedList<EventoCovid>) listaEventos.clone();
    }

    public String getNombre() {
        return nombre;
    }

    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
     public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

  
 

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellidos, other.apellidos)) {
            return false;
        }
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento)) {
            return false;
        }
        if (!Objects.equals(this.grupo, other.grupo)) {
            return false;
        }
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        if (!Objects.equals(this.listaEventos, other.listaEventos)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formateString = fechaNacimiento.format(formatter);
        return "Usuario{" + "Nombre=" + nombre + 
                ", Apellidos=" + apellidos + 
                ", \nDNI=" + dni + 
                ", Fecha de Nacimiento=" + fechaNacimiento.format(DateTimeFormatter.ofPattern("dd/mm/yyyy")) + 
                ", \nGrupo=" + grupo + 
                ", Sexo=" + sexo + '}';
    }

   public void añadirEventoCovid (EventoCovid evento){
       listaEventos.add(evento);
       
   }
   public void eliminarEventoCovid (EventoCovid evento){
       listaEventos.remove(evento);
       
   }
    
    
}

