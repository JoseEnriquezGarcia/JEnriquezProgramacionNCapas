package com.digis01JEnriquezProgramacionNCapas;

import com.digis01JEnriquezProgramacionNCapas.DAO.DireccionDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.ML.Colonia;
import com.digis01JEnriquezProgramacionNCapas.ML.Direccion;
import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import com.digis01JEnriquezProgramacionNCapas.ML.Usuario;
import com.digis01JEnriquezProgramacionNCapas.ML.UsuarioDireccion;
import jakarta.persistence.EntityManager;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class JUnitTestMockitoDireccion {
    
    @Mock
    EntityManager entityManager;
    
    @InjectMocks
    DireccionDAOImplementation direccionDAOImplementation;
    
    @Mock
    PasswordEncoder passwordEncoder;
    
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
        Assertions.assertNotNull(result.object, "result.object viene null");
        Assertions.assertNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
    }
    
    @Test
    public void testAdd() throws ParseException{
        UsuarioDireccion usuarioDireccion = new UsuarioDireccion();

        usuarioDireccion.Usuario = new Usuario();
        usuarioDireccion.Usuario.setIdUsuario(5);
        usuarioDireccion.Direccion = new Direccion();
        usuarioDireccion.Direccion.setCalle("Lirios");
        usuarioDireccion.Direccion.setNumeroInterior("5");
        usuarioDireccion.Direccion.setNumeroExterior("8");
        usuarioDireccion.Direccion.Colonia = new Colonia();
        usuarioDireccion.Direccion.Colonia.setIdColonia(5);
        
        Result result = direccionDAOImplementation.DireccionAddJPA(usuarioDireccion);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object contiene datos");
        Assertions.assertNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
        
        Mockito.verify(entityManager, Mockito.times(1)).persist(Mockito.any(com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class));
    }
    
    @Test
    public void testUpdate(){
        Direccion direccion = new Direccion();
        
        direccion.setIdDireccion(1);
        direccion.setCalle("Lirios");
        direccion.setNumeroInterior("5");
        direccion.setNumeroExterior("10");
        direccion.Colonia = new Colonia();
        direccion.Colonia.setIdColonia(5);
        
        com.digis01JEnriquezProgramacionNCapas.JPA.Direccion direccionJPA = new com.digis01JEnriquezProgramacionNCapas.JPA.Direccion();
        
        direccion.setIdDireccion(1);
        direccionJPA.setCalle("Lirios");
        direccionJPA.setNumeroInterior("5");
        direccionJPA.setNumeroExterior("8");
        direccionJPA.Colonia = new com.digis01JEnriquezProgramacionNCapas.JPA.Colonia();
        direccionJPA.Colonia.setIdColonia(5);
        direccionJPA.Usuario = new com.digis01JEnriquezProgramacionNCapas.JPA.Usuario();
        direccionJPA.Usuario.setIdUsuario(5);
        
        Mockito.when(entityManager.find(com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class, 1)).thenReturn(direccionJPA);
        
        Result result = direccionDAOImplementation.DireccionUpdateJPA(direccion);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object contiene datos");
        Assertions.assertNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
        
        Mockito.verify(entityManager, Mockito.times(1)).merge(Mockito.any(com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class));
    }
    
    @Test
    public void testDelete(){
        com.digis01JEnriquezProgramacionNCapas.JPA.Direccion direccionJPA = new com.digis01JEnriquezProgramacionNCapas.JPA.Direccion();
        direccionJPA.setIdDireccion(5);
        
        Mockito.when(entityManager.find(com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class, 5)).thenReturn(direccionJPA);
        
        Result result = direccionDAOImplementation.DireccionDeleteJPA(5);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object contiene datos");
        Assertions.assertNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
        
        Mockito.verify(entityManager, Mockito.times(1)).remove(Mockito.any(com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class));
    }
}
