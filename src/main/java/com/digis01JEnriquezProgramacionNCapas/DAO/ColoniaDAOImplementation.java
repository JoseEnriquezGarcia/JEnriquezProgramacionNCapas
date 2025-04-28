package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.ML.Colonia;
import com.digis01JEnriquezProgramacionNCapas.ML.Estado;
import com.digis01JEnriquezProgramacionNCapas.ML.Municipio;
import com.digis01JEnriquezProgramacionNCapas.ML.Pais;
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
public class ColoniaDAOImplementation implements IColoniaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

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

                while (resultSet.next()) {
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
        try {
            jdbcTemplate.execute("{CALL ColoniaGetAllByCP(?,?)}", (CallableStatementCallback<Integer>) callableStatement -> {
                callableStatement.setString(1, CodigoPostal);
                callableStatement.registerOutParameter(2, Types.REF_CURSOR);
                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

                result.objects = new ArrayList<>();
                while (resultSet.next()) {
                    Colonia colonia = new Colonia();

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
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.objects = null;
        }
        return result;
    }

    @Override
    public Result ColoniaGetAllByIdJPA(int IdMunicipio) {
        Result result = new Result();

        try {
            TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Colonia> queryColonias = entityManager.createQuery("FROM Colonia WHERE Municipio.IdMunicipio = :idmunicipio", com.digis01JEnriquezProgramacionNCapas.JPA.Colonia.class);
            queryColonias.setParameter("idmunicipio", IdMunicipio);
            List<com.digis01JEnriquezProgramacionNCapas.JPA.Colonia> listaColonias = queryColonias.getResultList();

            result.objects = new ArrayList<>();

            for (com.digis01JEnriquezProgramacionNCapas.JPA.Colonia coloniaJPA : listaColonias) {
                Colonia colonia = new Colonia();

                colonia.setIdColonia(coloniaJPA.getIdColonia());
                colonia.setNombre(coloniaJPA.getNombre());
                colonia.setCodigoPostal(coloniaJPA.getCodigoPostal());

                result.objects.add(colonia);
            }

            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result ColoniaGetAllByCPJPA(String CodigoPostal) {
        Result result = new Result();
        try {
            TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Colonia> queryColonias = entityManager.createQuery("FROM Colonia WHERE CodigoPostal = :codigopostal", com.digis01JEnriquezProgramacionNCapas.JPA.Colonia.class);
            queryColonias.setParameter("codigopostal", CodigoPostal);
            List<com.digis01JEnriquezProgramacionNCapas.JPA.Colonia> listaColonias = queryColonias.getResultList();
            
            result.objects = new ArrayList<>();
            
            for (com.digis01JEnriquezProgramacionNCapas.JPA.Colonia coloniaJPA : listaColonias) {
                Colonia colonia = new Colonia();
                
                colonia.setIdColonia(coloniaJPA.getIdColonia());
                colonia.setNombre(coloniaJPA.getNombre());
                colonia.setCodigoPostal(coloniaJPA.getCodigoPostal());
                
                colonia.Municipio = new Municipio();
                colonia.Municipio.setIdMunicipio(coloniaJPA.Municipio.getIdMunicipio());
                colonia.Municipio.setNombre(coloniaJPA.Municipio.getNombre());
                
                colonia.Municipio.Estado = new Estado();
                colonia.Municipio.Estado.setIdEstado(coloniaJPA.Municipio.Estado.getIdEstado());
                colonia.Municipio.Estado.setNombre(coloniaJPA.Municipio.Estado.getNombre());
                
                colonia.Municipio.Estado.Pais = new Pais();
                colonia.Municipio.Estado.Pais.setIdPais(coloniaJPA.Municipio.Estado.Pais.getIdPais());
                colonia.Municipio.Estado.Pais.setNombre(coloniaJPA.Municipio.Estado.Pais.getNombre());
                
                result.objects.add(colonia);
            }

            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.objects = null;
        }

        return result;
    }
}
