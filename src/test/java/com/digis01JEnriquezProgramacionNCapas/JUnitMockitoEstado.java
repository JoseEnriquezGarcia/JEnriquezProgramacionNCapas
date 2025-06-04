package com.digis01JEnriquezProgramacionNCapas;

import com.digis01JEnriquezProgramacionNCapas.DAO.EstadoDAOImplementation;
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
public class JUnitMockitoEstado {
    
    @Mock
    EntityManager entityManager;
    
    @InjectMocks
    EstadoDAOImplementation estadoDAOImplementation;
    
    @Test
    public void testGetAll(){
        com.digis01JEnriquezProgramacionNCapas.JPA.Estado estado = new com.digis01JEnriquezProgramacionNCapas.JPA.Estado();
        
        estado.setIdEstado(1);
        estado.Pais = new Pais();
        estado.Pais.setIdPais(2);
        
        TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Estado> queryEstados = Mockito.mock(TypedQuery.class);
        
        Mockito.when(entityManager.createQuery("FROM Estado WHERE Pais.IdPais = :idpais", com.digis01JEnriquezProgramacionNCapas.JPA.Estado.class)).thenReturn(queryEstados);
        Mockito.when(queryEstados.setParameter(Mockito.eq("idpais"), Mockito.anyInt())).thenReturn(queryEstados);
        List<com.digis01JEnriquezProgramacionNCapas.JPA.Estado> listaEstados = new ArrayList<>();
        
        Mockito.when(queryEstados.getResultList()).thenReturn(listaEstados);
        
        Result result = estadoDAOImplementation.EstadoGetAllByIdJPA(0);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object contiene datos");
        Assertions.assertNotNull(result.objects, "result.objects viene null");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
        
        Mockito.verify(entityManager, Mockito.times(1)).createQuery("FROM Estado WHERE Pais.IdPais = :idpais", com.digis01JEnriquezProgramacionNCapas.JPA.Estado.class);
    }
}
