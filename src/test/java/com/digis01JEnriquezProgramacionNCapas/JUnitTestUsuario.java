package com.digis01JEnriquezProgramacionNCapas;

import com.digis01JEnriquezProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.ML.Colonia;
import com.digis01JEnriquezProgramacionNCapas.ML.Direccion;
import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import com.digis01JEnriquezProgramacionNCapas.ML.Rol;
import com.digis01JEnriquezProgramacionNCapas.ML.Usuario;
import com.digis01JEnriquezProgramacionNCapas.ML.UsuarioDireccion;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitTestUsuario {
    
    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;
    
    @Test
    public void testGetAll(){
        Result result = usuarioDAOImplementation.GetAllJPA();
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct es false");
        Assertions.assertNull(result.object, "result.object contiene datos");
        Assertions.assertNotNull(result.objects, "result.objects viene null");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción" + result.ex);
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error" + result.errorMessage);
    }
    
    @Test
    public void testGetAllById(){
        Result result = usuarioDAOImplementation.GetByAllIdJPA(1);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNotNull(result.object, "result.object viene null");
        Assertions.assertNull(result.objects, "result.objects viene con datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensjae de error" + result.errorMessage);
    }
    
    @Test
    public void testGetById(){
        Result result = usuarioDAOImplementation.GetByIdJPA(1);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNotNull(result.object, "result.object viene null");
        Assertions.assertNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
    }
    
    @Test
    public void testGetallDinamico(){
        Usuario usuario = new Usuario();
        usuario.setNombre("");
        usuario.setApellidoPaterno("");
        usuario.setApellidoMaterno("");
        usuario.Rol = new Rol();
        usuario.Rol.setIdRol(1);
        usuario.setStatus(1);
        
        Result result = usuarioDAOImplementation.GetAllDinamicoJPA(usuario);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object viene null");
        Assertions.assertNotNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
    }
    
    @Test
    public void testAdd() throws ParseException{
        UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
        usuarioDireccion.Usuario = new Usuario();
        
        usuarioDireccion.Usuario.setUserName("alejandro123");
        usuarioDireccion.Usuario.setNombre("Alejandro");
        usuarioDireccion.Usuario.setApellidoPaterno("Garcia");
        usuarioDireccion.Usuario.setApellidoMaterno("Torres");
        usuarioDireccion.Usuario.setEmail("alejandro@gmail.com");
        usuarioDireccion.Usuario.setPassword("12345");
        String fecha ="10/05/1999";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dateUtil = formato.parse(fecha);
        Date fechaSql = new Date(dateUtil.getTime());
        usuarioDireccion.Usuario.setFechaNacimiento(fechaSql);
        usuarioDireccion.Usuario.setSexo("M".charAt(0));
        usuarioDireccion.Usuario.setTelefono("5487567889");
        usuarioDireccion.Usuario.setCelular("5487895623");
        usuarioDireccion.Usuario.setCURP("AGJ548745MNC1240");
        usuarioDireccion.Usuario.Rol = new Rol();
        usuarioDireccion.Usuario.Rol.setIdRol(3);
        usuarioDireccion.Usuario.setImagen(null);
        usuarioDireccion.Usuario.setStatus(1);
        usuarioDireccion.Direccion = new Direccion();
        usuarioDireccion.Direccion.setCalle("Lirios");
        usuarioDireccion.Direccion.setNumeroInterior("5");
        usuarioDireccion.Direccion.setNumeroExterior("8");
        usuarioDireccion.Direccion.Colonia = new Colonia();
        usuarioDireccion.Direccion.Colonia.setIdColonia(5);
        
        Result result = usuarioDAOImplementation.AddJPA(usuarioDireccion);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object viene null");
        Assertions.assertNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
    }
    
    @Test
    public void testUpdate() throws ParseException{
        Usuario usuario = new Usuario();
        
        usuario.setIdUsuario(365);
        usuario.setUserName("alejandro456");
        usuario.setNombre("Alejandro");
        usuario.setApellidoPaterno("García");
        usuario.setApellidoMaterno("Torres");
        usuario.setEmail("alejandro@gmail.com");
        usuario.setPassword("12345");
        String fecha ="10/05/1999";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dateUtil = formato.parse(fecha);
        Date fechaSql = new Date(dateUtil.getTime());
        usuario.setFechaNacimiento(fechaSql);
        usuario.setSexo("M".charAt(0));
        usuario.setTelefono("5487567889");
        usuario.setCelular("5487895623");
        usuario.setCURP("AGJ548745MNC1240");
        usuario.Rol = new Rol();
        usuario.Rol.setIdRol(3);
        usuario.setImagen(null);
        usuario.setStatus(1);
        
        Result result = usuarioDAOImplementation.UsuarioUpdateJPA(usuario);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object viene null");
        Assertions.assertNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
    }
    
    @Test
    public void testDelete(){
        Result result = usuarioDAOImplementation.DireccionUsuarioDeleteJPA(364);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object viene null");
        Assertions.assertNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
    }
    
    @Test
    public void testUpdateStatus(){
        Result result = usuarioDAOImplementation.UpdateStatusJPA(110, 0);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object viene null");
        Assertions.assertNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
    }
}
