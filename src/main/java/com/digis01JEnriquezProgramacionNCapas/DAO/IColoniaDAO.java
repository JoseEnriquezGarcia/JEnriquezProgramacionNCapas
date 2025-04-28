package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.ML.Result;

public interface IColoniaDAO {
    Result ColoniaGetAllById(int IdMunicipio);
    Result ColoniaGetAllByCP(String CodigoPostal);
    
    Result ColoniaGetAllByIdJPA(int IdMunicipio);
    Result ColoniaGetAllByCPJPA(String CodigoPostal);
}
