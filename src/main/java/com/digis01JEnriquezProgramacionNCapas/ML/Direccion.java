package com.digis01JEnriquezProgramacionNCapas.ML;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Direccion {
    private int IdDireccion;
    
    @NotNull(message = "No debe ser Nulo")
    @NotEmpty(message = "No debe estar vacio")
    @NotBlank(message = "El campo es requerido")
    @Size(min = 3, max = 50, message = "Debe contener entre 3 y 50 carateres")
    private String Calle;
    @Size(max = 20, message = "Debe contener entre 2 y 20 carateres")
    private String NumeroInterior;
    
    @NotNull(message = "No debe ser Nulo")
    @NotEmpty(message = "No debe estar vacio")
    @NotBlank(message = "El campo es requerido")
    @Size(min = 2, max = 20, message = "Debe contener entre 2 y 20 carateres")
    private String NumeroExterior;
    
    @Valid
    public Colonia Colonia; //propiedad de navegacion

    public Colonia getColonia() {
        return Colonia;
    }

    public void setColonia(Colonia Colonia) {
        this.Colonia = Colonia;
    }
    
    public int getIdDireccion(){
        return IdDireccion;
    }
    
    public void setIdDireccion(int IdDireccion){
        this.IdDireccion = IdDireccion;
    }
    
    public String getCalle(){
        return Calle;
    }
    
    public void setCalle(String Calle){
        this.Calle = Calle;
    }
    
    public String getNumeroInterior(){
        return NumeroInterior;
    }
    
    public void setNumeroInterior(String NumeroInterior){
        this.NumeroInterior = NumeroInterior;
    }
    
    public String getNumeroExterior(){
        return NumeroExterior;
    }
    
    public void setNumeroExterior(String NumeroExterior){
        this.NumeroExterior = NumeroExterior;
    }
    
}
