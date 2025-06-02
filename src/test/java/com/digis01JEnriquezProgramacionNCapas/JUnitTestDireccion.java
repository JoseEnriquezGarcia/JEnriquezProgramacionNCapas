package com.digis01JEnriquezProgramacionNCapas;

import com.digis01JEnriquezProgramacionNCapas.DAO.DireccionDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.ML.Colonia;
import com.digis01JEnriquezProgramacionNCapas.ML.Direccion;
import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import com.digis01JEnriquezProgramacionNCapas.ML.Usuario;
import com.digis01JEnriquezProgramacionNCapas.ML.UsuarioDireccion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitTestDireccion {
    @Autowired
    private DireccionDAOImplementation direccionDAOImplementation;
    
    @Test
    public void testGetById(){
        Result result = direccionDAOImplementation.GetByIdDireccionJPA(1);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct es false");
        Assertions.assertNotNull(result.object, "result.object viene null");
        Assertions.assertNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción" + result.ex);
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error" + result.errorMessage);
    }
    
    @Test
    public void testAdd(){
        UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
        usuarioDireccion.Usuario = new Usuario();
        
        usuarioDireccion.Usuario.setIdUsuario(365);
        usuarioDireccion.Direccion = new Direccion();
        usuarioDireccion.Direccion.setCalle("Manzanos");
        usuarioDireccion.Direccion.setNumeroInterior("10");
        usuarioDireccion.Direccion.setNumeroExterior("8");
        usuarioDireccion.Direccion.Colonia = new Colonia();
        usuarioDireccion.Direccion.Colonia.setIdColonia(8);
        
        Result result = direccionDAOImplementation.DireccionAddJPA(usuarioDireccion);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct es false");
        Assertions.assertNull(result.object, "result.object viene null");
        Assertions.assertNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción" + result.ex);
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error" + result.errorMessage);
    }
    
    @Test
    public void testUpdate(){
        Direccion direccion = new Direccion();
        
        direccion.setIdDireccion(305);
        direccion.setCalle("Manzanas");
        direccion.setNumeroInterior("15");
        direccion.setNumeroExterior("20");
        direccion.Colonia = new Colonia();
        direccion.Colonia.setIdColonia(5);
        
        Result result = direccionDAOImplementation.DireccionUpdateJPA(direccion);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct es false");
        Assertions.assertNull(result.object, "result.object viene null");
        Assertions.assertNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción" + result.ex);
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error" + result.errorMessage);
    }
    
    @Test
    public void testDelete(){
        Result result = direccionDAOImplementation.DireccionDeleteJPA(305);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct es false");
        Assertions.assertNull(result.object, "result.object viene null");
        Assertions.assertNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción" + result.ex);
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error" + result.errorMessage);
    }
}
