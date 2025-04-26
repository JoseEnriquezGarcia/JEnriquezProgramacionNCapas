
package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import com.digis01JEnriquezProgramacionNCapas.ML.Usuario;
import com.digis01JEnriquezProgramacionNCapas.ML.UsuarioDireccion;

public interface IUsuarioDAO {
    Result GetAll();
    Result GetAllById(int IdUsuario);
    Result Add(UsuarioDireccion usuarioDireccion);
    Result UsuarioUpdate(Usuario usuario);
    Result DireccionUsuarioDelete(int IdUsuario);
    Result GetById(int IdUsuario);
    Result UpdateStatus(int IdUsuario, int Status);
    Result GetAllDinamico(Usuario usuario);
    
    Result GetAllJPA();
    Result GetByAllIdJPA(int IdUsuario);
    Result GetByIdJPA(int IdUsuario);
    Result AddJPA(UsuarioDireccion usuarioDireccion);
    Result UsuarioUpdateJPA(Usuario usuario);
    Result DireccionUsuarioDeleteJPA(int IdUsuario);
    Result UpdateStatusJPA(int IdUsuario, int Status);
}
