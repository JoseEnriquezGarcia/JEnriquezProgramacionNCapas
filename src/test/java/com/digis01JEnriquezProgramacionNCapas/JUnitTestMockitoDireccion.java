package com.digis01JEnriquezProgramacionNCapas;

import com.digis01JEnriquezProgramacionNCapas.DAO.DireccionDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitTestMockitoDireccion {
    
    @Mock
    EntityManager entityManager;
    
    @InjectMocks
    DireccionDAOImplementation direccionDAOImplementation;
    
    @Test
    public void testGetById(){
        com.digis01JEnriquezProgramacionNCapas.JPA.Direccion direccion = new com.digis01JEnriquezProgramacionNCapas.JPA.Direccion();
        
        direccion.setIdDireccion(25);
        direccion.setCalle("Lirios");
        direccion.setNumeroInterior("5");
        direccion.setNumeroExterior("8");
        direccion.Colonia = new com.digis01JEnriquezProgramacionNCapas.JPA.Colonia();
        direccion.Colonia.setIdColonia(5);
        direccion.Colonia.Municipio = new com.digis01JEnriquezProgramacionNCapas.JPA.Municipio();
        
        direccion.Colonia.Municipio.Estado = new com.digis01JEnriquezProgramacionNCapas.JPA.Estado();
        
        direccion.Colonia.Municipio.Estado.Pais = new com.digis01JEnriquezProgramacionNCapas.JPA.Pais();
        
        Mockito.when(entityManager.find(com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class, 25)).thenReturn(direccion);
        
        Result result = direccionDAOImplementation.GetByIdDireccionJPA(25);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNotNull(result.object, "result.object contiene datos");
        Assertions.assertNull(result.objects, "result.objects viene null");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
    }
}
