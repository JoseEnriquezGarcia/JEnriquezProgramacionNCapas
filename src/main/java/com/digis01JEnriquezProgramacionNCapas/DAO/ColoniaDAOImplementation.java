package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.ML.Colonia;
import com.digis01JEnriquezProgramacionNCapas.ML.Estado;
import com.digis01JEnriquezProgramacionNCapas.ML.Municipio;
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
public class ColoniaDAOImplementation implements IColoniaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result ColoniaGetAllById(int IdMunicipio) {
        Result result = new Result();
        try {
            jdbcTemplate.execute("{CALL ColoniaGetAllById(?,?)}", (CallableStatementCallback<Integer>) callableStatement -> {
                callableStatement.setInt(1, IdMunicipio);
                callableStatement.registerOutParameter(2, Types.REF_CURSOR);
                callableStatement.execute();
                
                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
                
                result.objects = new ArrayList<>();
                
                while(resultSet.next()){
                    Colonia colonia = new Colonia();
                    
                    colonia.setIdColonia(resultSet.getInt("IdColonia"));
                    colonia.setNombre(resultSet.getString("Nombre"));
                    colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                    
                    result.objects.add(colonia);
                }
                result.correct = true;
                return 1;
            });
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result ColoniaGetAllByCP(String CodigoPostal) {
        Result result = new Result();
        try{
            jdbcTemplate.execute("{CALL ColoniaGetAllByCP(?,?)}", (CallableStatementCallback<Integer>) callableStatement ->{
               callableStatement.setString(1, CodigoPostal);
               callableStatement.registerOutParameter(2, Types.REF_CURSOR);
               callableStatement.execute();
               
               ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
               
               result.objects = new ArrayList<>();
               while(resultSet.next()){
                   Colonia colonia =  new Colonia();
                   
                   colonia.setIdColonia(resultSet.getInt("IdColonia"));
                   colonia.setNombre(resultSet.getString("NombreColonia"));
                   colonia.setCodigoPostal(CodigoPostal);
                   colonia.Municipio = new Municipio();
                   
                   colonia.Municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                   colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));
                   colonia.Municipio.Estado = new Estado();
                   
                   colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
                   colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));
                   colonia.Municipio.Estado.Pais = new Pais();
                   
                   colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));
                   colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));
                   
                   result.objects.add(colonia);
               }
                result.correct = true;
                return 1;
            });        
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.objects = null;
        }
        return result;
    }
}
