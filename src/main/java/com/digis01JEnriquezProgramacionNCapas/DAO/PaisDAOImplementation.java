package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.ML.Pais;
import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaisDAOImplementation implements IPaisDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Result GetAll() {
    Result result = new Result();
    
    try{
        jdbcTemplate.execute("{CALL GetAllPais(?)}", (CallableStatementCallback<Integer>) callableStatement ->{
           
            callableStatement.registerOutParameter(1, Types.REF_CURSOR);
            callableStatement.execute();
            ResultSet resulSet = (ResultSet) callableStatement.getObject(1);
            
            result.objects = new ArrayList<>();
            
            while (resulSet.next()) {
             Pais pais = new Pais();
                pais.setIdPais(resulSet.getInt("IdPais"));
                pais.setNombre(resulSet.getString("Nombre"));
                
               result.objects.add(pais);
            }
            result.correct = true;
            return 1;
        });
    }catch(Exception ex){
        result.correct = false;
        result.errorMessage = ex.getLocalizedMessage();
        result.ex = ex;
    }
    
    return result;
    }
    
}
