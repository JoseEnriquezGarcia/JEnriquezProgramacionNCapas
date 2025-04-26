package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import com.digis01JEnriquezProgramacionNCapas.ML.Rol;
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
public class RolDAOImplementation implements IRolDAO{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
            
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result GetAll() {
        Result result = new Result();
        
        try{
        jdbcTemplate.execute("{CALL GetAllRol(?)}",(CallableStatementCallback<Integer>) callablaStatement ->{
           
            callablaStatement.registerOutParameter(1, Types.REF_CURSOR);
            callablaStatement.execute();
            ResultSet resultSet = (ResultSet) callablaStatement.getObject(1);
            
            result.objects = new ArrayList<>();
            
            while(resultSet.next()){
                Rol rol = new Rol();
                
                rol.setIdRol(resultSet.getInt("IdRol"));
                rol.setNombre(resultSet.getString("Nombre"));    
                result.objects.add(rol);
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
    public Result GetallJPA() {
        Result result = new Result();
        TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Rol> queryRol = entityManager.createQuery("FROM Rol", com.digis01JEnriquezProgramacionNCapas.JPA.Rol.class);
        List<com.digis01JEnriquezProgramacionNCapas.JPA.Rol> listaroles = queryRol.getResultList();
        
        result.objects = new ArrayList<>();
        
        for (com.digis01JEnriquezProgramacionNCapas.JPA.Rol rolJPA : listaroles) {
            Rol rol = new Rol();
            
            rol.setIdRol(rolJPA.getIdRol());
            rol.setNombre(rolJPA.getNombre());
            
            result.objects.add(rol);
        }
        return result;
    }
    
}
