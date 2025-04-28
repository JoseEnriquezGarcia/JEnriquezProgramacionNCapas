package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.ML.Result;

public interface IEstadoDAO {
    Result EstadoGetAllById(int IdPais);
    
    Result EstadoGetAllByIdJPA(int IdPais);
}
