package com.digis01JEnriquezProgramacionNCapas;

import com.digis01JEnriquezProgramacionNCapas.DAO.DireccionDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.ML.Colonia;
import com.digis01JEnriquezProgramacionNCapas.ML.Direccion;
import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import com.digis01JEnriquezProgramacionNCapas.ML.Rol;
import com.digis01JEnriquezProgramacionNCapas.ML.Usuario;
import com.digis01JEnriquezProgramacionNCapas.ML.UsuarioDireccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class JUnitTestMockitoUsuario {

    @Mock
    EntityManager entityManager;

    @InjectMocks
    UsuarioDAOImplementation usuarioDAOImplementation;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    public void testAdd() throws ParseException {
        UsuarioDireccion usuarioDireccion = new UsuarioDireccion();

        usuarioDireccion.Usuario = new Usuario();
        usuarioDireccion.Usuario.setUserName("alejandro123");
        usuarioDireccion.Usuario.setNombre("Alejandro");
        usuarioDireccion.Usuario.setApellidoPaterno("Garcia");
        usuarioDireccion.Usuario.setApellidoMaterno("Torres");
        usuarioDireccion.Usuario.setEmail("alejandro@gmail.com");
        usuarioDireccion.Usuario.setPassword(passwordEncoder.encode("12345"));
        String fecha = "10/05/1999";
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
        Mockito.verify(entityManager, Mockito.times(1)).persist(Mockito.any(com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class));
        Mockito.verify(entityManager, Mockito.times(1)).persist(Mockito.any(com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class));

        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object viene null");
        Assertions.assertNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
    }

    @Test
    public void testUpdate() throws ParseException {
        Usuario usuario = new Usuario();

        usuario.setIdUsuario(365);
        usuario.setUserName("alejandro456");
        usuario.setNombre("Alejandro");
        usuario.setApellidoPaterno("García");
        usuario.setApellidoMaterno("Torres");
        usuario.setEmail("alejandro@gmail.com");
        usuario.setPassword("12345");
        String fecha = "10/05/1999";
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
        Mockito.verify(entityManager, Mockito.times(1)).merge(Mockito.any(com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class));

        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object viene null");
        Assertions.assertNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
    }

    @Test
    public void testDireccionUsuarioDelete() {
        com.digis01JEnriquezProgramacionNCapas.JPA.Direccion direccion = new com.digis01JEnriquezProgramacionNCapas.JPA.Direccion();
        
        direccion.setIdDireccion(5);
        direccion.setCalle("Lirios");
        direccion.setNumeroInterior("5");
        direccion.setNumeroExterior("8");
        direccion.Colonia = new com.digis01JEnriquezProgramacionNCapas.JPA.Colonia();
        direccion.Colonia.setIdColonia(5);
        direccion.Usuario = new com.digis01JEnriquezProgramacionNCapas.JPA.Usuario();
        direccion.Usuario.setIdUsuario(24);
        
        com.digis01JEnriquezProgramacionNCapas.JPA.Usuario usuario = new com.digis01JEnriquezProgramacionNCapas.JPA.Usuario();
        usuario.setIdUsuario(24);
        
        
        TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Direccion> queryDireccion = Mockito.mock(TypedQuery.class);

        Mockito.when(entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario = :idusuario", com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class)).thenReturn(queryDireccion);
        Mockito.when(queryDireccion.setParameter(Mockito.eq("idusuario"), Mockito.anyInt())).thenReturn(queryDireccion);
        List<com.digis01JEnriquezProgramacionNCapas.JPA.Direccion> direcciones = new ArrayList<>();

        direcciones.add(direccion);

        Mockito.when(queryDireccion.getResultList()).thenReturn(direcciones);

        Mockito.when(entityManager.find(com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class, 24)).thenReturn(usuario);

        Result result = usuarioDAOImplementation.DireccionUsuarioDeleteJPA(24);

        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object contiene datos");
        Assertions.assertNull(result.objects, "result.objects viene null");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
        
        Mockito.verify(entityManager, Mockito.atLeast(1)).remove(Mockito.any(com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class));
        Mockito.verify(entityManager, Mockito.atLeast(1)).remove(Mockito.any(com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class));
        Mockito.verify(entityManager, Mockito.atLeast(1)).find(Mockito.eq(com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class), Mockito.anyInt());
    }

    @Test
    public void testGetAll() throws ParseException {

        com.digis01JEnriquezProgramacionNCapas.JPA.Usuario usuario = new com.digis01JEnriquezProgramacionNCapas.JPA.Usuario();

        usuario.setIdUsuario(365);
        usuario.setUserName("alejandro456");
        usuario.setNombre("Alejandro");
        usuario.setApellidoPaterno("García");
        usuario.setApellidoMaterno("Torres");
        usuario.setEmail("alejandro@gmail.com");
        usuario.setPassword("12345");
        String fecha = "10/05/1999";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dateUtil = formato.parse(fecha);
        Date fechaSql = new Date(dateUtil.getTime());
        usuario.setFechaNacimiento(fechaSql);
        usuario.setSexo("M".charAt(0));
        usuario.setTelefono("5487567889");
        usuario.setCelular("5487895623");
        usuario.setCURP("AGJ548745MNC1240");
        usuario.Rol = new com.digis01JEnriquezProgramacionNCapas.JPA.Rol();
        usuario.Rol.setIdRol(3);
        usuario.setImagen(null);
        usuario.setStatus(1);

        com.digis01JEnriquezProgramacionNCapas.JPA.Direccion direccion = new com.digis01JEnriquezProgramacionNCapas.JPA.Direccion();

        direccion.setCalle("Lirios");
        direccion.setNumeroInterior("5");
        direccion.setNumeroExterior("8");
        direccion.Colonia = new com.digis01JEnriquezProgramacionNCapas.JPA.Colonia();
        direccion.Colonia.setIdColonia(5);

        TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Usuario> queryUsuarios = Mockito.mock(TypedQuery.class);

        Mockito.when(entityManager.createQuery("SELECT u FROM Usuario u ORDER BY u.IdUsuario ASC", com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class)).thenReturn(queryUsuarios);

        List<com.digis01JEnriquezProgramacionNCapas.JPA.Usuario> usuarios = new ArrayList<>();

        usuarios.add(usuario);

        Mockito.when(queryUsuarios.getResultList()).thenReturn(usuarios);

        //Direccion
        TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Direccion> queryDireccion = Mockito.mock(TypedQuery.class);

        Mockito.when(entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario = :idusuario", com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class)).thenReturn(queryDireccion);
        Mockito.when(queryDireccion.setParameter(Mockito.eq("idusuario"), Mockito.anyInt())).thenReturn(queryDireccion);

        List<com.digis01JEnriquezProgramacionNCapas.JPA.Direccion> direcciones = new ArrayList<>();

        direcciones.add(direccion);

        Mockito.when(queryDireccion.getResultList()).thenReturn(direcciones);

        Result result = usuarioDAOImplementation.GetAllJPA();
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object contiene datos");
        Assertions.assertNotNull(result.objects, "result.objects viene null");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
        
        //Verificar
        Mockito.verify(entityManager, Mockito.times(1)).createQuery("SELECT u FROM Usuario u ORDER BY u.IdUsuario ASC", com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class);

        Mockito.verify(entityManager, Mockito.times(1)).createQuery("FROM Direccion WHERE Usuario.IdUsuario = :idusuario", com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class);
    }
    
    @Test
    public void testGetAllById() throws ParseException{
        com.digis01JEnriquezProgramacionNCapas.JPA.Usuario usuario = new com.digis01JEnriquezProgramacionNCapas.JPA.Usuario();

        usuario.setIdUsuario(365);
        usuario.setUserName("alejandro456");
        usuario.setNombre("Alejandro");
        usuario.setApellidoPaterno("García");
        usuario.setApellidoMaterno("Torres");
        usuario.setEmail("alejandro@gmail.com");
        usuario.setPassword("12345");
        String fecha = "10/05/1999";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dateUtil = formato.parse(fecha);
        Date fechaSql = new Date(dateUtil.getTime());
        usuario.setFechaNacimiento(fechaSql);
        usuario.setSexo("M".charAt(0));
        usuario.setTelefono("5487567889");
        usuario.setCelular("5487895623");
        usuario.setCURP("AGJ548745MNC1240");
        usuario.Rol = new com.digis01JEnriquezProgramacionNCapas.JPA.Rol();
        usuario.Rol.setIdRol(3);
        usuario.setImagen(null);
        usuario.setStatus(1);
        
        com.digis01JEnriquezProgramacionNCapas.JPA.Direccion direccion = new com.digis01JEnriquezProgramacionNCapas.JPA.Direccion();

        direccion.setCalle("Lirios");
        direccion.setNumeroInterior("5");
        direccion.setNumeroExterior("8");
        direccion.Colonia = new com.digis01JEnriquezProgramacionNCapas.JPA.Colonia();
        direccion.Colonia.setIdColonia(5);
        
        TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Usuario> queryUsuario = Mockito.mock(TypedQuery.class);

        Mockito.when(entityManager.createQuery("FROM Usuario WHERE IdUsuario = :idusuario", com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class)).thenReturn(queryUsuario);
        Mockito.when(queryUsuario.setParameter(Mockito.eq("idusuario"), Mockito.anyInt())).thenReturn(queryUsuario);
        
        List<com.digis01JEnriquezProgramacionNCapas.JPA.Usuario> usuarioJPA = new ArrayList<>();
        usuarioJPA.add(usuario);
        
        Mockito.when(queryUsuario.getSingleResult());
        
        //Direccion
        TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Direccion> queryDireccion = Mockito.mock(TypedQuery.class);

        Mockito.when(entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario = :idusuario", com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class)).thenReturn(queryDireccion);
        Mockito.when(queryDireccion.setParameter(Mockito.eq("idusuario"), Mockito.anyInt())).thenReturn(queryDireccion);
        
        Result result = usuarioDAOImplementation.GetByAllIdJPA(365);
        
        Mockito.verify(entityManager, Mockito.times(1)).createQuery("FROM Direccion WHERE Usuario.IdUsuario = :idusuario");
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object contiene datos");
        Assertions.assertNotNull(result.objects, "result.objects viene null");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
    }

    @Test
    public void testGetById() throws ParseException {
        com.digis01JEnriquezProgramacionNCapas.JPA.Usuario usuario = new com.digis01JEnriquezProgramacionNCapas.JPA.Usuario();

        usuario.setIdUsuario(365);
        usuario.setUserName("alejandro456");
        usuario.setNombre("Alejandro");
        usuario.setApellidoPaterno("García");
        usuario.setApellidoMaterno("Torres");
        usuario.setEmail("alejandro@gmail.com");
        usuario.setPassword("12345");
        String fecha = "10/05/1999";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dateUtil = formato.parse(fecha);
        Date fechaSql = new Date(dateUtil.getTime());
        usuario.setFechaNacimiento(fechaSql);
        usuario.setSexo("M".charAt(0));
        usuario.setTelefono("5487567889");
        usuario.setCelular("5487895623");
        usuario.setCURP("AGJ548745MNC1240");
        usuario.Rol = new com.digis01JEnriquezProgramacionNCapas.JPA.Rol();
        usuario.Rol.setIdRol(3);
        usuario.setImagen(null);
        usuario.setStatus(1);

        TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Usuario> queryUsuario = Mockito.mock(TypedQuery.class);

        Mockito.when(entityManager.createQuery("FROM Usuario WHERE IdUsuario = :idusuario", com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class)).thenReturn(queryUsuario);
        Mockito.when(queryUsuario.setParameter(Mockito.eq("idusuario"), Mockito.anyInt())).thenReturn(queryUsuario);

        List<com.digis01JEnriquezProgramacionNCapas.JPA.Usuario> usuarioJPA = new ArrayList<>();

        usuarioJPA.add(usuario);

        Mockito.when(queryUsuario.getSingleResult()).thenReturn(usuario);
        
        Result result = usuarioDAOImplementation.GetByIdJPA(365);
        
        Mockito.verify(entityManager, Mockito.times(1)).createQuery("FROM Usuario WHERE IdUsuario = :idusuario", com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNotNull(result.object, "result.object viene null");
        Assertions.assertNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
    }
    
    @Test
    public void testUpdateStatus() throws ParseException{
        com.digis01JEnriquezProgramacionNCapas.JPA.Usuario usuario = new com.digis01JEnriquezProgramacionNCapas.JPA.Usuario();

        usuario.setIdUsuario(365);
        usuario.setUserName("alejandro456");
        usuario.setNombre("Alejandro");
        usuario.setApellidoPaterno("García");
        usuario.setApellidoMaterno("Torres");
        usuario.setEmail("alejandro@gmail.com");
        usuario.setPassword("12345");
        String fecha = "10/05/1999";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dateUtil = formato.parse(fecha);
        Date fechaSql = new Date(dateUtil.getTime());
        usuario.setFechaNacimiento(fechaSql);
        usuario.setSexo("M".charAt(0));
        usuario.setTelefono("5487567889");
        usuario.setCelular("5487895623");
        usuario.setCURP("AGJ548745MNC1240");
        usuario.Rol = new com.digis01JEnriquezProgramacionNCapas.JPA.Rol();
        usuario.Rol.setIdRol(3);
        usuario.setImagen(null);
        usuario.setStatus(1);
        
        Mockito.when(entityManager.find(com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class, 365)).thenReturn(usuario);
        
        Result result = usuarioDAOImplementation.UpdateStatusJPA(365, 0);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object contiene datos");
        Assertions.assertNull(result.objects, "result.objects contiene datos");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
        
        Mockito.verify(entityManager, Mockito.times(1)).find(com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class,365);
        Mockito.verify(entityManager, Mockito.atLeast(1)).merge(Mockito.any(com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class));
    }
    
    @Test
    public void testGetAllDinamico() throws ParseException{
        Usuario usuarioBusqueda = new Usuario();

        usuarioBusqueda.setIdUsuario(365);
        usuarioBusqueda.setNombre("Alejandro");
        usuarioBusqueda.setApellidoPaterno("García");
        usuarioBusqueda.setApellidoMaterno("Torres");
        usuarioBusqueda.Rol = new Rol();
        usuarioBusqueda.Rol.setIdRol(3);
        usuarioBusqueda.setStatus(1);
        
        com.digis01JEnriquezProgramacionNCapas.JPA.Usuario usuario = new com.digis01JEnriquezProgramacionNCapas.JPA.Usuario();

        usuario.setIdUsuario(365);
        usuario.setUserName("alejandro456");
        usuario.setNombre("Alejandro");
        usuario.setApellidoPaterno("García");
        usuario.setApellidoMaterno("Torres");
        usuario.setEmail("alejandro@gmail.com");
        usuario.setPassword("12345");
        String fecha = "10/05/1999";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dateUtil = formato.parse(fecha);
        Date fechaSql = new Date(dateUtil.getTime());
        usuario.setFechaNacimiento(fechaSql);
        usuario.setSexo("M".charAt(0));
        usuario.setTelefono("5487567889");
        usuario.setCelular("5487895623");
        usuario.setCURP("AGJ548745MNC1240");
        usuario.Rol = new com.digis01JEnriquezProgramacionNCapas.JPA.Rol();
        usuario.Rol.setIdRol(3);
        usuario.setImagen(null);
        usuario.setStatus(1);
        
        com.digis01JEnriquezProgramacionNCapas.JPA.Direccion direccion = new com.digis01JEnriquezProgramacionNCapas.JPA.Direccion();

        direccion.setCalle("Lirios");
        direccion.setNumeroInterior("5");
        direccion.setNumeroExterior("8");
        direccion.Colonia = new com.digis01JEnriquezProgramacionNCapas.JPA.Colonia();
        direccion.Colonia.setIdColonia(5);
        
        String queryDinamico = "FROM Usuario";
            queryDinamico = queryDinamico + " WHERE UPPER(Nombre) LIKE UPPER(CONCAT('%', :Nombre ,'%'))";
            queryDinamico = queryDinamico + " AND UPPER(ApellidoPaterno) LIKE UPPER(CONCAT('%', :Apaterno ,'%'))";
            queryDinamico = queryDinamico + " AND UPPER(ApellidoMaterno) LIKE UPPER(CONCAT('%', :Amaterno ,'%'))";

            queryDinamico = usuarioBusqueda.getStatus() != null ? queryDinamico + " AND CAST(Status AS STRING) LIKE CONCAT('%', :Status ,'%')" : queryDinamico;

            queryDinamico = usuarioBusqueda.Rol.getIdRol() != 0 ? queryDinamico + " AND CAST(Rol.IdRol AS String) LIKE CONCAT('%', :IdRol ,'%')" : queryDinamico;
            
        TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Usuario> queryBusqueda = Mockito.mock(TypedQuery.class);
        
        Mockito.when(entityManager.createQuery(queryDinamico, com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class)).thenReturn(queryBusqueda);
        
        List<com.digis01JEnriquezProgramacionNCapas.JPA.Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(usuario);
        
        Mockito.when(queryBusqueda.getResultList()).thenReturn(listaUsuarios);
        
        TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Direccion> queryDirecciones = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario = :IdUsuario", com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class)).thenReturn(queryDirecciones);
        Mockito.when(queryDirecciones.setParameter(Mockito.eq("idusuario"), Mockito.anyInt())).thenReturn(queryDirecciones);
        
        List<com.digis01JEnriquezProgramacionNCapas.JPA.Direccion> listaDirecciones = new ArrayList<>();
        listaDirecciones.add(direccion);
        
        Mockito.when(queryDirecciones.getResultList()).thenReturn(listaDirecciones);
        
        Result result = usuarioDAOImplementation.GetAllDinamicoJPA(usuarioBusqueda);
        
        Assertions.assertNotNull(result, "Result viene null");
        Assertions.assertTrue(result.correct, "result.correct viene false");
        Assertions.assertNull(result.object, "result.object contiene datos");
        Assertions.assertNotNull(result.objects, "result.objects viene null");
        Assertions.assertNull(result.ex, "result.ex contiene una excepción");
        Assertions.assertNull(result.errorMessage, "result.errorMessage contiene un mensaje de error");
        
        Mockito.verify(entityManager, Mockito.atLeast(1)).createQuery(queryDinamico, com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class);
        Mockito.verify(entityManager, Mockito.atLeast(1)).createQuery("FROM Direccion WHERE Usuario.IdUsuario = :IdUsuario", com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class);
    }
}
