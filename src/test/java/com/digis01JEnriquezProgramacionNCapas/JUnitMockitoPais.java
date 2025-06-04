package com.digis01JEnriquezProgramacionNCapas;

import com.digis01JEnriquezProgramacionNCapas.DAO.DireccionDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.DAO.PaisDAOImplementation;
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
public class JUnitMockitoPais {
    
    @Mock
    EntityManager entityManager;
    
    @InjectMocks
    PaisDAOImplementation paisDAOImplementation;
    
    @Test
    public void testGetAll(){
        com.digis01JEnriquezProgramacionNCapas.JPA.Pais Pais = new com.digis01JEnriquezProgramacionNCapas.JPA.Pais();
        Pais.setIdPais(1);
        Pais.setNombre("Mexico");
        
        TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Pais> queryPais = Mockito.mock(TypedQuery.class);
        
        Mockito.when(entityManager.createQuery("FROM Pais", com.digis01JEnriquezProgramacionNCapas.JPA.Pais.class)).thenReturn(queryPais);
        
        List<com.digis01JEnriquezProgramacionNCapas.JPA.Pais> listaPaises = new ArrayList<>();
        
        Mockito.when(queryPais.getResultList()).thenReturn(listaPaises);
        
        Result result = paisDAOImplementation.GetAllJPA();
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object contiene datos");
        Assertions.assertNotNull(result.objects, "result.objects viene null");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
        
        Mockito.verify(entityManager, Mockito.atLeast(1)).createQuery("FROM Pais", com.digis01JEnriquezProgramacionNCapas.JPA.Pais.class);
    }
}
