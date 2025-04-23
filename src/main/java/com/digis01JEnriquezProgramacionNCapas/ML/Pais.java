package com.digis01JEnriquezProgramacionNCapas.ML;

import jakarta.validation.constraints.NotNull;

public class Pais {
    @NotNull(message = "No debe estar vacio")
    private int IdPais;
    private String Nombre;

    public int getIdPais() {
        return IdPais;
    }

    public void setIdPais(int IdPais) {
        this.IdPais = IdPais;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    
}
