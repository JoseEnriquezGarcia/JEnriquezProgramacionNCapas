package com.digis01JEnriquezProgramacionNCapas.DAO;


import com.digis01JEnriquezProgramacionNCapas.JPA.Usuario;
import com.digis01JEnriquezProgramacionNCapas.ML.Colonia;
import com.digis01JEnriquezProgramacionNCapas.ML.Direccion;
import com.digis01JEnriquezProgramacionNCapas.ML.Estado;
import com.digis01JEnriquezProgramacionNCapas.ML.Municipio;
import com.digis01JEnriquezProgramacionNCapas.ML.Pais;
import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import com.digis01JEnriquezProgramacionNCapas.ML.UsuarioDireccion;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DireccionDAOImplementation implements IDireccionDAO{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result GetByIdDireccion(int IdDireccion) {
        Result result = new Result();
        
        try{
            jdbcTemplate.execute("{CALL GetDireccionById(?,?)}", (CallableStatementCallback<Integer>) callableStatement ->{
                callableStatement.setInt(1, IdDireccion);
                callableStatement.registerOutParameter(2, Types.REF_CURSOR);
                
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
                
                if(resultSet.next()){
                  Direccion direccion = new Direccion();
                  
                  direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                  direccion.setCalle(resultSet.getString("Calle"));
                  direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                  direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));
                  
                  direccion.Colonia = new Colonia();
                  direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
                  direccion.Colonia.setNombre(resultSet.getString("NombreColonia"));
                  direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                  
                  direccion.Colonia.Municipio = new Municipio();
                  direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                  direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));
                  
                  direccion.Colonia.Municipio.Estado = new Estado();
                  direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
                  direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));
                  
                  direccion.Colonia.Municipio.Estado.Pais = new Pais();
                  direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));
                  direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));
                  
                  result.object = direccion;
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

    @Override
    public Result DireccionUpdate(Direccion direccion) {
        Result result = new Result();
        
        try{
            jdbcTemplate.execute("{CALL UpdateDireccionById(?,?,?,?,?)}", (CallableStatementCallback<Integer>) callableStatement ->{
                
                callableStatement.setInt(1,direccion.getIdDireccion());
                callableStatement.setString(2,direccion.getCalle());
                callableStatement.setString(3,direccion.getNumeroInterior());
                callableStatement.setString(4,direccion.getNumeroExterior());
                callableStatement.setInt(5,direccion.Colonia.getIdColonia());
                
                int rowsAffected = callableStatement.executeUpdate();
                
                if(rowsAffected > 0){
                    result.correct = true;
                }else{
                    result.correct = false;
                    result.errorMessage = "Error al actualizar direccion";
                }
                
                return 1;
            });
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result DireccionAdd(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();
        try{
            jdbcTemplate.execute("{CALL DireccionAdd(?,?,?,?,?)}", (CallableStatementCallback<Integer>) callableStatement->{
                callableStatement.setString(1, usuarioDireccion.Direccion.getCalle());
                callableStatement.setString(2, usuarioDireccion.Direccion.getNumeroInterior());
                callableStatement.setString(3, usuarioDireccion.Direccion.getNumeroExterior());
                callableStatement.setInt(4, usuarioDireccion.Direccion.Colonia.getIdColonia());
                callableStatement.setInt(5, usuarioDireccion.Usuario.getIdUsuario());
                
                int rowsAffected = callableStatement.executeUpdate();
                
                if (rowsAffected > 0) {
                    result.correct = true;
                }else{
                    result.correct = false;
                    result.errorMessage = "Error al Insertar";
                }
                
            return 1;
        });
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }

    @Override
    public Result DireccionDelete(int IdDireccion) {
        Result result = new Result();
        try{
        jdbcTemplate.execute("{CALL DeleteDireccion(?)}", (CallableStatementCallback<Integer>) callableStatement ->{
            callableStatement.setInt(1, IdDireccion);
            int rowAffected = callableStatement.executeUpdate();
            
            if(rowAffected > 0){
                result.correct = true;
            }else{
                result.correct = false;
                result.errorMessage = "Error al eliminar";
            }
            
            return 1;
        });
        }catch(Exception ex){
            result.correct = true;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetByIdDireccionJPA(int IdDireccion) {
        Result result = new Result();
        
        try {
            com.digis01JEnriquezProgramacionNCapas.JPA.Direccion direccionJPA = new com.digis01JEnriquezProgramacionNCapas.JPA.Direccion();
            
            direccionJPA = entityManager.find(com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class, IdDireccion);
            
            Direccion direccion = new Direccion();
            
            direccion.setIdDireccion(direccionJPA.getIdDireccion());
            direccion.setCalle(direccionJPA.getCalle());
            direccion.setNumeroInterior(direccionJPA.getNumeroInterior());
            direccion.setNumeroExterior(direccionJPA.getNumeroExterior());
            
            direccion.Colonia = new Colonia();
            direccion.Colonia.setIdColonia(direccionJPA.Colonia.getIdColonia());
            direccion.Colonia.setNombre(direccionJPA.Colonia.getNombre());
            direccion.Colonia.setCodigoPostal(direccionJPA.Colonia.getCodigoPostal());
            
            direccion.Colonia.Municipio = new Municipio();
            direccion.Colonia.Municipio.setIdMunicipio(direccionJPA.Colonia.Municipio.getIdMunicipio());
            direccion.Colonia.Municipio.setNombre(direccionJPA.Colonia.Municipio.getNombre());
            
            direccion.Colonia.Municipio.Estado = new Estado();
            direccion.Colonia.Municipio.Estado.setIdEstado(direccionJPA.Colonia.Municipio.Estado.getIdEstado());
            direccion.Colonia.Municipio.Estado.setNombre(direccionJPA.Colonia.Municipio.Estado.getNombre());
            
            direccion.Colonia.Municipio.Estado.Pais = new Pais();
            direccion.Colonia.Municipio.Estado.Pais.setIdPais(direccionJPA.Colonia.Municipio.Estado.Pais.getIdPais());
            direccion.Colonia.Municipio.Estado.Pais.setNombre(direccionJPA.Colonia.Municipio.Estado.Pais.getNombre());
            
            result.object = direccion;
            
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.object = null;
        }
        
        return result;
    }
    
    @Transactional
    @Override
    public Result DireccionAddJPA(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();
        
        try {
            com.digis01JEnriquezProgramacionNCapas.JPA.Direccion direccionJPA = new com.digis01JEnriquezProgramacionNCapas.JPA.Direccion();
            
            direccionJPA.setCalle(usuarioDireccion.Direccion.getCalle());
            direccionJPA.setNumeroInterior(usuarioDireccion.Direccion.getNumeroInterior());
            direccionJPA.setNumeroExterior(usuarioDireccion.Direccion.getNumeroExterior());
            direccionJPA.Colonia = new com.digis01JEnriquezProgramacionNCapas.JPA.Colonia();
            direccionJPA.Colonia.setIdColonia(usuarioDireccion.Direccion.Colonia.getIdColonia());
            direccionJPA.Usuario = new Usuario();
            direccionJPA.Usuario.setIdUsuario(usuarioDireccion.Usuario.getIdUsuario());
            
            entityManager.persist(direccionJPA);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
    @Transactional
    @Override
    public Result DireccionUpdateJPA(Direccion direccion) {
        Result result  = new Result ();
        
        try {
            com.digis01JEnriquezProgramacionNCapas.JPA.Direccion direccionJPA = new com.digis01JEnriquezProgramacionNCapas.JPA.Direccion();
            
            direccionJPA = entityManager.find(com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class, direccion.getIdDireccion());
            
            direccionJPA.setIdDireccion(direccion.getIdDireccion());
            direccionJPA.setCalle(direccion.getCalle());
            direccionJPA.setNumeroInterior(direccion.getNumeroInterior());
            direccionJPA.setNumeroExterior(direccion.getNumeroExterior());
            
            direccionJPA.Colonia = new com.digis01JEnriquezProgramacionNCapas.JPA.Colonia();
            direccionJPA.Colonia.setIdColonia(direccion.Colonia.getIdColonia());
            
            direccionJPA.Usuario.getIdUsuario();
            
            entityManager.merge(direccionJPA);
            
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
    @Transactional
    @Override
    public Result DireccionDeleteJPA(int IdDireccion) {
        Result result = new Result();
        
        try {
            com.digis01JEnriquezProgramacionNCapas.JPA.Direccion direccionJPA = new com.digis01JEnriquezProgramacionNCapas.JPA.Direccion();
            
            direccionJPA = entityManager.find(com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class, IdDireccion);
            
            entityManager.remove(direccionJPA);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
}
