package com.digis01JEnriquezProgramacionNCapas;

import com.digis01JEnriquezProgramacionNCapas.DAO.EstadoDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JunitTestEstado {
    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;
    
    @Test
    public void testGetAll(){
        Result result = estadoDAOImplementation.EstadoGetAllByIdJPA(2);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct es false");
        Assertions.assertNull(result.object, "result.object contiene datos");
        Assertions.assertNotNull(result.objects, "result.objects viene null");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción" + result.ex);
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error" + result.errorMessage);
    }
    
}
