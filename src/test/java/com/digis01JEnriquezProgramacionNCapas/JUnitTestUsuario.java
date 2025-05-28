package com.digis01JEnriquezProgramacionNCapas;

import com.digis01JEnriquezProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import com.digis01JEnriquezProgramacionNCapas.ML.Usuario;
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
        
        Assertions.assertTrue(result.correct);
        Assertions.assertNotNull(result.objects);
        Assertions.assertNull(result.ex);
    }
    
    @Test
    public void testGetAllById(){
        Result result = usuarioDAOImplementation.GetByAllIdJPA(1);
        
        Assertions.assertTrue(result.correct);
        Assertions.assertNotNull(result.object);
        Assertions.assertNull(result.ex);
    }
    
    @Test
    public void testGetById(){
        Result result = usuarioDAOImplementation.GetByIdJPA(1);
        
        Assertions.assertTrue(result.correct);
        Assertions.assertNotNull(result.object);
        Assertions.assertNull(result.ex);
    }
    
    @Test
    public void testGetallDinamico(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Jose");
        usuario.setApellidoPaterno("Eriquez");
        usuario.setApellidoMaterno("Garcia");
        
        Result result = usuarioDAOImplementation.GetAllDinamicoJPA(usuario);
        
        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.ex, result.errorMessage);
    }
}
