package com.digis01JEnriquezProgramacionNCapas;

import com.digis01JEnriquezProgramacionNCapas.DAO.ColoniaDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitTestColonia {
    @Autowired
    private ColoniaDAOImplementation coloniaDAOImplementation;
    
    @Test
    public void testGetAllById(){
        Result result = coloniaDAOImplementation.ColoniaGetAllByIdJPA(5);
        
        Assertions.assertEquals(result, result);
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct es false");
        Assertions.assertNull(result.object, "result.object contiene datos");
        Assertions.assertNotNull(result.objects, "result.objects viene null");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción " + result.ex);
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error " + result.errorMessage);
    }
    
    @Test
    public void testGetAllByCP(){
        Result result = coloniaDAOImplementation.ColoniaGetAllByCPJPA("55748");
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct es false");
        Assertions.assertNull(result.object, "result.object contiene datos");
        Assertions.assertNotNull(result.objects, "result.objects viene null");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción" + result.ex);
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error " + result.errorMessage);
    }
}
