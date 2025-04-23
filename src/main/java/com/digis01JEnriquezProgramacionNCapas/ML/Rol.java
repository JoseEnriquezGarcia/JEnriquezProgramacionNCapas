package com.digis01JEnriquezProgramacionNCapas.ML;

import jakarta.validation.constraints.NotNull;

public class Rol {
    
    @NotNull(message = "No debe ser Nulo")
    private int IdRol;
    private String Nombre;
    
    public int getIdRol(){
        return IdRol;
    }
    
    public void setIdRol(int IdRol){
        this.IdRol = IdRol;
    }
    
    public String getNombre(){
        return Nombre;
    }
    
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }
}
