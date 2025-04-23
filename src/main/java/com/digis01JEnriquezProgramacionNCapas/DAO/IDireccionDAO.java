package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.ML.Direccion;
import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import com.digis01JEnriquezProgramacionNCapas.ML.UsuarioDireccion;

public interface IDireccionDAO {
    Result GetByIdDireccion(int IdDireccion);
    Result DireccionUpdate (Direccion direccion);
    Result DireccionAdd(UsuarioDireccion usuarioDireccion);
    Result DireccionDelete(int IdDireccion);
}
