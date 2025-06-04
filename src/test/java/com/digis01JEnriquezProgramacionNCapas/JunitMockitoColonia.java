package com.digis01JEnriquezProgramacionNCapas;

import com.digis01JEnriquezProgramacionNCapas.DAO.ColoniaDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.JPA.Estado;
import com.digis01JEnriquezProgramacionNCapas.JPA.Municipio;
import com.digis01JEnriquezProgramacionNCapas.JPA.Pais;
import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JunitMockitoColonia {
    
    @Mock
    EntityManager entityManager;
    
    @InjectMocks
    ColoniaDAOImplementation coloniaDAOImplementation;
    
    @Test
    public void testGetAll(){
        com.digis01JEnriquezProgramacionNCapas.JPA.Colonia colonia = new com.digis01JEnriquezProgramacionNCapas.JPA.Colonia();
        
        colonia.setIdColonia(1);
        colonia.Municipio = new Municipio();
        colonia.Municipio.setIdMunicipio(1);
        
        TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Colonia> queryColonias = Mockito.mock(TypedQuery.class);
        
        Mockito.when(entityManager.createQuery("FROM Colonia WHERE Municipio.IdMunicipio = :idmunicipio", com.digis01JEnriquezProgramacionNCapas.JPA.Colonia.class)).thenReturn(queryColonias);
        
        Mockito.when(queryColonias.setParameter(Mockito.eq("idmunicipio"), Mockito.anyInt())).thenReturn(queryColonias);
        
        List<com.digis01JEnriquezProgramacionNCapas.JPA.Colonia> listaColonias = new ArrayList<>();
        
        listaColonias.add(colonia);
        
        Mockito.when(queryColonias.getResultList()).thenReturn(listaColonias);
        
        Result result = coloniaDAOImplementation.ColoniaGetAllByIdJPA(1);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object contiene datos");
        Assertions.assertNotNull(result.objects, "result.objects viene null");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
        
        Mockito.verify(entityManager, Mockito.atLeast(1)).createQuery("FROM Colonia WHERE Municipio.IdMunicipio = :idmunicipio", com.digis01JEnriquezProgramacionNCapas.JPA.Colonia.class);
    }
    
    @Test
    public void testGetAllByCP(){
        com.digis01JEnriquezProgramacionNCapas.JPA.Colonia colonia = new com.digis01JEnriquezProgramacionNCapas.JPA.Colonia();
        
        colonia.setIdColonia(1);
        colonia.setCodigoPostal("55752");
        colonia.Municipio = new Municipio();
        colonia.Municipio.setIdMunicipio(1);
        colonia.Municipio.Estado = new Estado();
        colonia.Municipio.Estado.Pais = new Pais();
        
        TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Colonia> queryColonias = Mockito.mock(TypedQuery.class);
        
        Mockito.when(entityManager.createQuery("FROM Colonia WHERE CodigoPostal = :codigopostal", com.digis01JEnriquezProgramacionNCapas.JPA.Colonia.class)).thenReturn(queryColonias);
        
        Mockito.when(queryColonias.setParameter(Mockito.eq("codigopstal"), Mockito.anyString())).thenReturn(queryColonias);
        
        List<com.digis01JEnriquezProgramacionNCapas.JPA.Colonia> listaColonias = new ArrayList<>();
        
        listaColonias.add(colonia);
        
        Mockito.when(queryColonias.getResultList()).thenReturn(listaColonias);
        
        Result result = coloniaDAOImplementation.ColoniaGetAllByCPJPA("55752");
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object contiene datos");
        Assertions.assertNotNull(result.objects, "result.objects viene null");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
        
        Mockito.verify(entityManager, Mockito.times(1)).createQuery("FROM Colonia WHERE CodigoPostal = :codigopostal", com.digis01JEnriquezProgramacionNCapas.JPA.Colonia.class);
    }
}
