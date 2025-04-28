package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.ML.Municipio;
import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipioDAOImplementation implements IMunicipioDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result MunicipioGetAllById(int IdEstado) {
        Result result = new Result();
        try{
            jdbcTemplate.execute("{CALL MunicipioGetAllById(?,?)}", (CallableStatementCallback<Integer>) callableStatement ->{
                callableStatement.setInt(1, IdEstado);
                callableStatement.registerOutParameter(2, Types.REF_CURSOR);
                callableStatement.execute();
                
                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
                
                result.objects = new ArrayList<>();
                
                while(resultSet.next()){
                    Municipio municipio = new Municipio();
                    
                    municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                    municipio.setNombre(resultSet.getString("Nombre"));
                    
                    result.objects.add(municipio);
                }
                result.correct = true;
                return 1;
            });
            result.correct = true;
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result MunicipioGetAllByIdJPA(int IdEstado) {
        Result result = new Result();
        
        try {
            TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Municipio> queryMunicipio = entityManager.createQuery("FROM Municipio WHERE Estado.IdEstado = :idestado", com.digis01JEnriquezProgramacionNCapas.JPA.Municipio.class);
            queryMunicipio.setParameter("idestado", IdEstado);
            List<com.digis01JEnriquezProgramacionNCapas.JPA.Municipio> listaMunicipios = queryMunicipio.getResultList();
            
            result.objects = new ArrayList<>();
            
            for (com.digis01JEnriquezProgramacionNCapas.JPA.Municipio municipioJPA : listaMunicipios) {
                Municipio municipio = new Municipio();
                
                municipio.setIdMunicipio(municipioJPA.getIdMunicipio());
                municipio.setNombre(municipioJPA.getNombre());
                
                result.objects.add(municipio);
            }
            
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
}
