package com.digis01JEnriquezProgramacionNCapas;

import com.digis01JEnriquezProgramacionNCapas.DAO.MunicipioDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.JPA.Estado;
import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitMockitoMunicipio {
    
    @Mock
    EntityManager entityManager;
    
    @InjectMocks
    MunicipioDAOImplementation municipioDAOImplementation;
    
    @Test
    public void testGetAll(){
        com.digis01JEnriquezProgramacionNCapas.JPA.Municipio municipio = new com.digis01JEnriquezProgramacionNCapas.JPA.Municipio();
        
        municipio.setIdMunicipio(1);
        municipio.Estado = new Estado();
        municipio.setIdMunicipio(1);
        municipio.Estado.setIdEstado(1);
        
        TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Municipio> queryMunicipios = Mockito.mock(TypedQuery.class);
        
        Mockito.when(entityManager.createQuery("FROM Municipio WHERE Estado.IdEstado = :IdEstado", com.digis01JEnriquezProgramacionNCapas.JPA.Municipio.class)).thenReturn(queryMunicipios);
        
        Mockito.when(queryMunicipios.setParameter(Mockito.eq("IdEstado"), Mockito.anyInt())).thenReturn(queryMunicipios);
        
        Result result = municipioDAOImplementation.MunicipioGetAllByIdJPA(1);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object contiene datos");
        Assertions.assertNotNull(result.objects, "result.objects viene null");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
        
        Mockito.verify(entityManager, Mockito.atLeast(1)).createQuery("FROM Municipio WHERE Estado.IdEstado = :IdEstado", com.digis01JEnriquezProgramacionNCapas.JPA.Municipio.class);
    }
}
