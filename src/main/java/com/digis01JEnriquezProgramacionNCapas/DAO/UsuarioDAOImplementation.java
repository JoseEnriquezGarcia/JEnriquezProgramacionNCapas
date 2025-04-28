package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.ML.Colonia;
import com.digis01JEnriquezProgramacionNCapas.ML.Direccion;
import com.digis01JEnriquezProgramacionNCapas.ML.Estado;
import com.digis01JEnriquezProgramacionNCapas.ML.Municipio;
import com.digis01JEnriquezProgramacionNCapas.ML.Pais;
import com.digis01JEnriquezProgramacionNCapas.ML.Usuario;
import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import com.digis01JEnriquezProgramacionNCapas.ML.Rol;
import com.digis01JEnriquezProgramacionNCapas.ML.UsuarioDireccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImplementation implements IUsuarioDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();
        try {
            jdbcTemplate.execute("{CALL GetAll(?)}",
                    (CallableStatementCallback<Integer>) callableStatement -> {
                        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
                        callableStatement.execute();
                        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

                        result.objects = new ArrayList<>();

                        while (resultSet.next()) {

                            int idUsuario = resultSet.getInt("IdUsuario");
                            if (!result.objects.isEmpty() && idUsuario == ((UsuarioDireccion) (result.objects.get(result.objects.size() - 1))).Usuario.getIdUsuario()) {

                                //Solo Agregamos direccion
                                Direccion direccion = new Direccion();
                                direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                                direccion.setCalle(resultSet.getString("Calle"));
                                direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                                direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));

                                direccion.Colonia = new Colonia();
                                direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
                                direccion.Colonia.setNombre(resultSet.getString("NombreColonia"));
                                direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));

                                direccion.Colonia.Municipio = new Municipio();
                                direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                                direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));

                                direccion.Colonia.Municipio.Estado = new Estado();
                                direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
                                direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));

                                direccion.Colonia.Municipio.Estado.Pais = new Pais();
                                direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));
                                direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));

                                ((UsuarioDireccion) (result.objects.get(result.objects.size() - 1))).Direcciones.add(direccion);

                            } else { //El id no es el mismo o es nueva lista
                                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();

                                usuarioDireccion.Usuario = new Usuario();
                                usuarioDireccion.Usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                                usuarioDireccion.Usuario.setUserName(resultSet.getString("UserName"));
                                usuarioDireccion.Usuario.setNombre(resultSet.getString("NombreUsuario"));
                                usuarioDireccion.Usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                                usuarioDireccion.Usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                                usuarioDireccion.Usuario.setEmail(resultSet.getString("Email"));
                                usuarioDireccion.Usuario.setPassword(resultSet.getString("Password"));
                                usuarioDireccion.Usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                                usuarioDireccion.Usuario.setSexo(resultSet.getString("Sexo").charAt(0));
                                usuarioDireccion.Usuario.setTelefono(resultSet.getString("Telefono"));
                                usuarioDireccion.Usuario.setCelular(resultSet.getString("Celular"));
                                usuarioDireccion.Usuario.setCURP(resultSet.getString("CURP"));
                                usuarioDireccion.Usuario.setImagen(resultSet.getString("Imagen"));
                                usuarioDireccion.Usuario.setStatus(resultSet.getInt("Status"));
                                usuarioDireccion.Usuario.Rol = new Rol();
                                usuarioDireccion.Usuario.Rol.setIdRol(resultSet.getInt("IdRol"));
                                usuarioDireccion.Usuario.Rol.setNombre(resultSet.getString("NombreRol"));

                                usuarioDireccion.Direcciones = new ArrayList<>();
                                Direccion direccion = new Direccion();
                                direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                                direccion.setCalle(resultSet.getString("Calle"));
                                direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                                direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));

                                direccion.Colonia = new Colonia();
                                direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
                                direccion.Colonia.setNombre(resultSet.getString("NombreColonia"));
                                direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));

                                direccion.Colonia.Municipio = new Municipio();
                                direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                                direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));

                                direccion.Colonia.Municipio.Estado = new Estado();
                                direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
                                direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));

                                direccion.Colonia.Municipio.Estado.Pais = new Pais();
                                direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));
                                direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));

                                usuarioDireccion.Direcciones.add(direccion);
                                result.objects.add(usuarioDireccion);
                            }
                        }
                        result.correct = true;
                        return 1;
                    });
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.objects = null;
        }
        return result;
    }

    @Override
    public Result GetAllById(int IdUsuario) {
        Result result = new Result();
        try {
            jdbcTemplate.execute("CALL UsuarioDireccionAllById(?,?,?)",
                    (CallableStatementCallback<Integer>) callableStatement -> {

                        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
                        callableStatement.registerOutParameter(2, Types.REF_CURSOR);
                        callableStatement.setInt(3, IdUsuario);
                        callableStatement.execute();

                        UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                        ResultSet resultSetUsuario = (ResultSet) callableStatement.getObject(1);

                        if (resultSetUsuario.next()) {
                            usuarioDireccion.Usuario = new Usuario();

                            usuarioDireccion.Usuario.setIdUsuario(resultSetUsuario.getInt("IdUsuario"));
                            usuarioDireccion.Usuario.setUserName(resultSetUsuario.getString("UserName"));
                            usuarioDireccion.Usuario.setNombre(resultSetUsuario.getString("NombreUsuario"));
                            usuarioDireccion.Usuario.setApellidoPaterno(resultSetUsuario.getString("ApellidoPaterno"));
                            usuarioDireccion.Usuario.setApellidoMaterno(resultSetUsuario.getString("ApellidoMaterno"));
                            usuarioDireccion.Usuario.setEmail(resultSetUsuario.getString("Email"));
                            usuarioDireccion.Usuario.setPassword(resultSetUsuario.getString("Password"));
                            usuarioDireccion.Usuario.setFechaNacimiento(resultSetUsuario.getDate("FechaNacimiento"));
                            usuarioDireccion.Usuario.setSexo(resultSetUsuario.getString("Sexo").charAt(0));
                            usuarioDireccion.Usuario.setTelefono(resultSetUsuario.getString("Telefono"));
                            usuarioDireccion.Usuario.setCelular(resultSetUsuario.getString("Celular"));
                            usuarioDireccion.Usuario.setCURP(resultSetUsuario.getString("CURP"));
                            usuarioDireccion.Usuario.setImagen(resultSetUsuario.getString("Imagen"));
                            usuarioDireccion.Usuario.Rol = new Rol();
                            usuarioDireccion.Usuario.Rol.setIdRol(resultSetUsuario.getInt("IdRol"));
                            usuarioDireccion.Usuario.Rol.setNombre(resultSetUsuario.getString("NombreRol"));
                        }

                        ResultSet resultSetDireccion = (ResultSet) callableStatement.getObject(2);

                        usuarioDireccion.Direcciones = new ArrayList<>();
                        while (resultSetDireccion.next()) {

                            Direccion direccion = new Direccion();
                            direccion.setIdDireccion(resultSetDireccion.getInt("IdDireccion"));
                            direccion.setCalle(resultSetDireccion.getString("Calle"));
                            direccion.setNumeroInterior(resultSetDireccion.getString("NumeroInterior"));
                            direccion.setNumeroExterior(resultSetDireccion.getString("NumeroExterior"));

                            direccion.Colonia = new Colonia();
                            direccion.Colonia.setIdColonia(resultSetDireccion.getInt("IdColonia"));
                            direccion.Colonia.setNombre(resultSetDireccion.getString("NombreColonia"));
                            direccion.Colonia.setCodigoPostal(resultSetDireccion.getString("CodigoPostal"));

                            direccion.Colonia.Municipio = new Municipio();
                            direccion.Colonia.Municipio.setIdMunicipio(resultSetDireccion.getInt("IdMunicipio"));
                            direccion.Colonia.Municipio.setNombre(resultSetDireccion.getString("NombreMunicipio"));

                            direccion.Colonia.Municipio.Estado = new Estado();
                            direccion.Colonia.Municipio.Estado.setIdEstado(resultSetDireccion.getInt("IdEstado"));
                            direccion.Colonia.Municipio.Estado.setNombre(resultSetDireccion.getString("NombreEstado"));

                            direccion.Colonia.Municipio.Estado.Pais = new Pais();
                            direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSetDireccion.getInt("IdPais"));
                            direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSetDireccion.getString("NombrePais"));

                            usuarioDireccion.Direcciones.add(direccion);
                        }

                        result.object = usuarioDireccion;
                        result.correct = true;
                        return 1;
                    }
            );
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.object = null;
        }
        return result;
    }

    @Override
    public Result Add(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();
        try {
            jdbcTemplate.execute("{CALL UsuarioDireccionAdd(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                    (CallableStatementCallback<Integer>) callableStatement -> {

                        callableStatement.setString(1, usuarioDireccion.Usuario.getUserName());
                        callableStatement.setString(2, usuarioDireccion.Usuario.getNombre());
                        callableStatement.setString(3, usuarioDireccion.Usuario.getApellidoPaterno());
                        callableStatement.setString(4, usuarioDireccion.Usuario.getApellidoMaterno());
                        callableStatement.setString(5, usuarioDireccion.Usuario.getEmail());
                        callableStatement.setString(6, usuarioDireccion.Usuario.getPassword());
                        Date fechaSql = new Date(usuarioDireccion.Usuario.getFechaNacimiento().getTime());
                        callableStatement.setDate(7, fechaSql);
                        callableStatement.setString(8, String.valueOf(usuarioDireccion.Usuario.getSexo()));
                        callableStatement.setString(9, usuarioDireccion.Usuario.getTelefono());
                        callableStatement.setString(10, usuarioDireccion.Usuario.getCelular());
                        callableStatement.setString(11, usuarioDireccion.Usuario.getCURP());
                        callableStatement.setInt(12, usuarioDireccion.Usuario.Rol.getIdRol());
                        callableStatement.setString(13, usuarioDireccion.Usuario.getImagen());
                        callableStatement.setInt(14, usuarioDireccion.Usuario.getStatus());
                        callableStatement.setString(15, usuarioDireccion.Direccion.getCalle());
                        callableStatement.setString(16, usuarioDireccion.Direccion.getNumeroInterior());
                        callableStatement.setString(17, usuarioDireccion.Direccion.getNumeroExterior());
                        callableStatement.setInt(18, usuarioDireccion.Direccion.Colonia.getIdColonia());

                        callableStatement.execute();

                        result.correct = true;
                        return 1;
                    });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetById(int IdUsuario) {
        Result result = new Result();

        try {
            jdbcTemplate.execute("{CALL GetUsuarioById(?,?)}", (CallableStatementCallback<Integer>) callableStatement -> {
                callableStatement.setInt(1, IdUsuario);
                callableStatement.registerOutParameter(2, Types.REF_CURSOR);
                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

                if (resultSet.next()) {
                    UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                    usuarioDireccion.Usuario = new Usuario();

                    usuarioDireccion.Usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                    usuarioDireccion.Usuario.setUserName(resultSet.getString("UserName"));
                    usuarioDireccion.Usuario.setNombre(resultSet.getString("NombreUsuario"));
                    usuarioDireccion.Usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                    usuarioDireccion.Usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                    usuarioDireccion.Usuario.setEmail(resultSet.getString("Email"));
                    usuarioDireccion.Usuario.setPassword(resultSet.getString("Password"));
                    usuarioDireccion.Usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                    usuarioDireccion.Usuario.setSexo(resultSet.getString("Sexo").charAt(0));
                    usuarioDireccion.Usuario.setTelefono(resultSet.getString("Telefono"));
                    usuarioDireccion.Usuario.setCelular(resultSet.getString("Celular"));
                    usuarioDireccion.Usuario.setCURP(resultSet.getString("CURP"));
                    usuarioDireccion.Usuario.Rol = new Rol();

                    usuarioDireccion.Usuario.Rol.setIdRol(resultSet.getInt("IdRol"));
                    usuarioDireccion.Usuario.Rol.setNombre(resultSet.getString("NombreRol"));

                    result.object = usuarioDireccion;
                }
                result.correct = true;
                return 1;
            });
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.object = null;
        }

        return result;
    }

    @Override
    public Result UsuarioUpdate(Usuario usuario) {
        Result result = new Result();

        try {
            jdbcTemplate.execute("{CALL UsuarioUpdate(?,?,?,?,?,?,?, TO_DATE(?, 'DD/MM/YYYY'),?,?,?,?,?,?,?)}", (CallableStatementCallback<Integer>) callableStatement -> {

                callableStatement.setInt(1, usuario.getIdUsuario());
                callableStatement.setString(2, usuario.getUserName());
                callableStatement.setString(3, usuario.getNombre());
                callableStatement.setString(4, usuario.getApellidoPaterno());
                callableStatement.setString(5, usuario.getApellidoMaterno());
                callableStatement.setString(6, usuario.getEmail());
                callableStatement.setString(7, usuario.getPassword());
                Date fechaSql = new Date(usuario.getFechaNacimiento().getTime());
                callableStatement.setDate(8, fechaSql);
                callableStatement.setString(9, String.valueOf(usuario.getSexo()));
                callableStatement.setString(10, usuario.getTelefono());
                callableStatement.setString(11, usuario.getCelular());
                callableStatement.setString(12, usuario.getCURP());
                callableStatement.setString(13, usuario.getImagen());
                callableStatement.setInt(14, usuario.getStatus());
                callableStatement.setInt(15, usuario.Rol.getIdRol());

                int rowsAffected = callableStatement.executeUpdate();

                if (rowsAffected > 0) {
                    result.correct = true;
                } else {
                    result.correct = false;
                    result.errorMessage = "Error al actualizar";
                }

                return 1;
            });
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result DireccionUsuarioDelete(int IdUsuario) {
        Result result = new Result();
        try {
            jdbcTemplate.execute("{CALL DireccionUsuarioDelete(?)}", (CallableStatementCallback<Integer>) callableStatement -> {
                callableStatement.setInt(1, IdUsuario);
                int rowsAffected = callableStatement.executeUpdate();

                if (rowsAffected > 0) {
                    result.correct = true;
                } else {
                    result.correct = false;
                    result.errorMessage = "Error al eliminar al usuario";
                }

                return 1;
            });
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result UpdateStatus(int IdUsuario, int Status) {
        Result result = new Result();
        try {
            jdbcTemplate.execute("{CALL UpdateStatus(?,?)}", (CallableStatementCallback<Integer>) callableStatement -> {
                callableStatement.setInt(1, IdUsuario);
                callableStatement.setInt(2, Status);
                int rowAffected = callableStatement.executeUpdate();

                result.correct = rowAffected > 0 ? true : false;
                return 1;
            });
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetAllDinamico(Usuario usuario) {
        Result result = new Result();
        try {
            jdbcTemplate.execute("{CALL GetAllDinamicoNombreApelidosRol(?,?,?,?,?)}", (CallableStatementCallback<Integer>) callableStatement -> {
                callableStatement.setString(1, usuario.getNombre());
                callableStatement.setString(2, usuario.getApellidoPaterno());
                callableStatement.setString(3, usuario.getApellidoMaterno());
                callableStatement.setInt(4, usuario.Rol.getIdRol());
                callableStatement.registerOutParameter(5, Types.REF_CURSOR);
                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(5);

                result.objects = new ArrayList<>();

                while (resultSet.next()) {
                    int IdUsuario = resultSet.getInt("IdUsuario");
                    if (!result.objects.isEmpty() && IdUsuario == ((UsuarioDireccion) (result.objects.get(result.objects.size() - 1))).Usuario.getIdUsuario()) {
                        Direccion direccion = new Direccion();

                        direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                        direccion.setCalle(resultSet.getString("Calle"));
                        direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                        direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));

                        direccion.Colonia = new Colonia();
                        direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
                        direccion.Colonia.setNombre(resultSet.getString("NombreColonia"));
                        direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));

                        direccion.Colonia.Municipio = new Municipio();
                        direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                        direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));

                        direccion.Colonia.Municipio.Estado = new Estado();
                        direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
                        direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));

                        direccion.Colonia.Municipio.Estado.Pais = new Pais();
                        direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));
                        direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));

                        ((UsuarioDireccion) (result.objects.get(result.objects.size() - 1))).Direcciones.add(direccion);
                    } else {
                        UsuarioDireccion usuarioDireccion = new UsuarioDireccion();

                        usuarioDireccion.Usuario = new Usuario();
                        usuarioDireccion.Usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                        usuarioDireccion.Usuario.setUserName(resultSet.getString("UserName"));
                        usuarioDireccion.Usuario.setNombre(resultSet.getString("NombreUsuario"));
                        usuarioDireccion.Usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                        usuarioDireccion.Usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                        usuarioDireccion.Usuario.setEmail(resultSet.getString("Email"));
                        usuarioDireccion.Usuario.setPassword(resultSet.getString("Password"));
                        usuarioDireccion.Usuario.setSexo(resultSet.getString("Sexo").charAt(0));
                        usuarioDireccion.Usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                        usuarioDireccion.Usuario.setTelefono(resultSet.getString("Telefono"));
                        usuarioDireccion.Usuario.setCelular(resultSet.getString("Celular"));
                        usuarioDireccion.Usuario.setCURP(resultSet.getString("CURP"));
                        usuarioDireccion.Usuario.setImagen(resultSet.getString("Imagen"));
                        usuarioDireccion.Usuario.setStatus(resultSet.getInt("Status"));

                        usuarioDireccion.Usuario.Rol = new Rol();
                        usuarioDireccion.Usuario.Rol.setIdRol(resultSet.getInt("IdRol"));
                        usuarioDireccion.Usuario.Rol.setNombre(resultSet.getString("NombreRol"));

                        usuarioDireccion.Direcciones = new ArrayList<>();

                        Direccion direccion = new Direccion();
                        direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                        direccion.setCalle(resultSet.getString("Calle"));
                        direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                        direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));

                        direccion.Colonia = new Colonia();
                        direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
                        direccion.Colonia.setNombre(resultSet.getString("NombreColonia"));
                        direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));

                        direccion.Colonia.Municipio = new Municipio();
                        direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                        direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));

                        direccion.Colonia.Municipio.Estado = new Estado();
                        direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
                        direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));

                        direccion.Colonia.Municipio.Estado.Pais = new Pais();
                        direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));
                        direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));

                        usuarioDireccion.Direcciones.add(direccion);
                        result.objects.add(usuarioDireccion);
                    }
                }
                return 1;
            });
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.objects = null;
        }
        return result;
    }

    @Override
    public Result GetAllJPA() {
        Result result = new Result();
        try {
            TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Usuario> queryUsuarios = entityManager.createQuery("SELECT u FROM Usuario u ORDER BY u.IdUsuario ASC", com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class);
            List<com.digis01JEnriquezProgramacionNCapas.JPA.Usuario> usuarios = queryUsuarios.getResultList();

            result.objects = new ArrayList<>();

            for (com.digis01JEnriquezProgramacionNCapas.JPA.Usuario usuario : usuarios) {
                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                usuarioDireccion.Usuario = new Usuario();

                usuarioDireccion.Usuario.setIdUsuario(usuario.getIdUsuario());
                usuarioDireccion.Usuario.setNombre(usuario.getNombre());
                usuarioDireccion.Usuario.setUserName(usuario.getUserName());
                usuarioDireccion.Usuario.setApellidoPaterno(usuario.getApellidoPaterno());
                usuarioDireccion.Usuario.setApellidoMaterno(usuario.getApellidoMaterno());
                usuarioDireccion.Usuario.setEmail(usuario.getEmail());
                usuarioDireccion.Usuario.setPassword(usuario.getPassword());
                usuarioDireccion.Usuario.setSexo(usuario.getSexo());
                usuarioDireccion.Usuario.setFechaNacimiento(usuario.getFechaNacimiento());
                usuarioDireccion.Usuario.setTelefono(usuario.getTelefono());
                usuarioDireccion.Usuario.setCelular(usuario.getCelular());
                usuarioDireccion.Usuario.setCURP(usuario.getCURP());
                usuarioDireccion.Usuario.setImagen(usuario.getImagen());
                usuarioDireccion.Usuario.setStatus(usuario.getStatus());

                TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario = :idusuario", com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class);
                queryDireccion.setParameter("idusuario", usuario.getIdUsuario());
                List<com.digis01JEnriquezProgramacionNCapas.JPA.Direccion> direcciones = queryDireccion.getResultList();
                usuarioDireccion.Direcciones = new ArrayList<>();
                for (com.digis01JEnriquezProgramacionNCapas.JPA.Direccion direccionJPA : direcciones) {
                    Direccion direccion = new Direccion();

                    direccion.setIdDireccion(direccionJPA.getIdDireccion());
                    direccion.setCalle(direccionJPA.getCalle());
                    direccion.setNumeroInterior(direccionJPA.getNumeroInterior());
                    direccion.setNumeroExterior(direccionJPA.getNumeroExterior());
                    direccion.Colonia = new Colonia();
                    direccion.Colonia.setIdColonia(direccionJPA.Colonia.getIdColonia());
                    direccion.Colonia.setNombre(direccionJPA.Colonia.getNombre());

                    usuarioDireccion.Direcciones.add(direccion);
                }

                result.objects.add(usuarioDireccion);
            }
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.objects = null;
        }
        return result;
    }

    @Override
    public Result GetByAllIdJPA(int IdUsuario) {
        Result result = new Result();
        try {
            TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Usuario> queryUsuario = entityManager.createQuery("FROM Usuario WHERE IdUsuario = :idusuario", com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class);
            queryUsuario.setParameter("idusuario", IdUsuario);
            com.digis01JEnriquezProgramacionNCapas.JPA.Usuario usuario = queryUsuario.getSingleResult();

            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            usuarioDireccion.Usuario = new Usuario();

            usuarioDireccion.Usuario.setIdUsuario(usuario.getIdUsuario());
            usuarioDireccion.Usuario.setUserName(usuario.getUserName());
            usuarioDireccion.Usuario.setNombre(usuario.getNombre());
            usuarioDireccion.Usuario.setApellidoPaterno(usuario.getApellidoPaterno());
            usuarioDireccion.Usuario.setApellidoMaterno(usuario.getApellidoMaterno());
            usuarioDireccion.Usuario.setEmail(usuario.getEmail());
            usuarioDireccion.Usuario.setPassword(usuario.getPassword());
            usuarioDireccion.Usuario.setSexo(usuario.getSexo());
            usuarioDireccion.Usuario.setFechaNacimiento(usuario.getFechaNacimiento());
            usuarioDireccion.Usuario.setTelefono(usuario.getTelefono());
            usuarioDireccion.Usuario.setCelular(usuario.getCelular());
            usuarioDireccion.Usuario.setCURP(usuario.getCURP());
            usuarioDireccion.Usuario.Rol = new Rol();
            usuarioDireccion.Usuario.Rol.setIdRol(usuario.Rol.getIdRol());
            usuarioDireccion.Usuario.Rol.setNombre(usuario.Rol.getNombre());
            usuarioDireccion.Usuario.setImagen(usuario.getImagen());
            usuarioDireccion.Usuario.setStatus(usuario.getStatus());

            TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario = :idusuario", com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class);
            queryDireccion.setParameter("idusuario", usuario.getIdUsuario());
            List<com.digis01JEnriquezProgramacionNCapas.JPA.Direccion> direcciones = queryDireccion.getResultList();
            usuarioDireccion.Direcciones = new ArrayList<>();

            for (com.digis01JEnriquezProgramacionNCapas.JPA.Direccion direccionJPA : direcciones) {
                Direccion direccion = new Direccion();

                direccion.setIdDireccion(direccionJPA.getIdDireccion());
                direccion.setCalle(direccionJPA.getCalle());
                direccion.setNumeroInterior(direccionJPA.getNumeroInterior());
                direccion.setNumeroExterior(direccionJPA.getNumeroExterior());

                direccion.Colonia = new Colonia();
                direccion.Colonia.setIdColonia(direccionJPA.Colonia.getIdColonia());
                direccion.Colonia.setNombre(direccionJPA.Colonia.getNombre());
                direccion.Colonia.setCodigoPostal(direccionJPA.Colonia.getCodigoPostal());

                direccion.Colonia.Municipio = new Municipio();
                direccion.Colonia.Municipio.setIdMunicipio(direccionJPA.Colonia.Municipio.getIdMunicipio());
                direccion.Colonia.Municipio.setNombre(direccionJPA.Colonia.Municipio.getNombre());

                direccion.Colonia.Municipio.Estado = new Estado();
                direccion.Colonia.Municipio.Estado.setIdEstado(direccionJPA.Colonia.Municipio.Estado.getIdEstado());
                direccion.Colonia.Municipio.Estado.setNombre(direccionJPA.Colonia.Municipio.Estado.getNombre());

                direccion.Colonia.Municipio.Estado.Pais = new Pais();
                direccion.Colonia.Municipio.Estado.Pais.setIdPais(direccionJPA.Colonia.Municipio.Estado.Pais.getIdPais());
                direccion.Colonia.Municipio.Estado.Pais.setNombre(direccionJPA.Colonia.Municipio.Estado.Pais.getNombre());

                usuarioDireccion.Direcciones.add(direccion);
            }

            result.object = usuarioDireccion;
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.object = null;
        }
        return result;
    }

    @Override
    public Result GetByIdJPA(int IdUsuario) {
        Result result = new Result();
        try {
            TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Usuario> queryUsuario = entityManager.createQuery("FROM Usuario WHERE IdUsuario = :idusuario", com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class);
            queryUsuario.setParameter("idusuario", IdUsuario);
            com.digis01JEnriquezProgramacionNCapas.JPA.Usuario usuarioJPA = queryUsuario.getSingleResult();

            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            usuarioDireccion.Usuario = new Usuario();

            usuarioDireccion.Usuario.setIdUsuario(IdUsuario);
            usuarioDireccion.Usuario.setUserName(usuarioJPA.getUserName());
            usuarioDireccion.Usuario.setNombre(usuarioJPA.getNombre());
            usuarioDireccion.Usuario.setApellidoPaterno(usuarioJPA.getApellidoPaterno());
            usuarioDireccion.Usuario.setApellidoMaterno(usuarioJPA.getApellidoMaterno());
            usuarioDireccion.Usuario.setEmail(usuarioJPA.getEmail());
            usuarioDireccion.Usuario.setPassword(usuarioJPA.getPassword());
            usuarioDireccion.Usuario.setSexo(usuarioJPA.getSexo());
            usuarioDireccion.Usuario.setFechaNacimiento(usuarioJPA.getFechaNacimiento());
            usuarioDireccion.Usuario.setTelefono(usuarioJPA.getTelefono());
            usuarioDireccion.Usuario.setCelular(usuarioJPA.getCelular());
            usuarioDireccion.Usuario.setCURP(usuarioJPA.getCURP());
            usuarioDireccion.Usuario.Rol = new Rol();
            usuarioDireccion.Usuario.Rol.setIdRol(usuarioJPA.Rol.getIdRol());
            usuarioDireccion.Usuario.setImagen(usuarioJPA.getImagen());
            usuarioDireccion.Usuario.setStatus(usuarioJPA.getStatus());

            result.object = usuarioDireccion;
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Transactional
    @Override
    public Result AddJPA(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();
        try {
            //Inicializa las entidades de JPA
            com.digis01JEnriquezProgramacionNCapas.JPA.Usuario usuario = new com.digis01JEnriquezProgramacionNCapas.JPA.Usuario();

            usuario.setUserName(usuarioDireccion.Usuario.getUserName());
            usuario.setNombre(usuarioDireccion.Usuario.getNombre());
            usuario.setApellidoPaterno(usuarioDireccion.Usuario.getApellidoPaterno());
            usuario.setApellidoMaterno(usuarioDireccion.Usuario.getApellidoMaterno());
            usuario.setEmail(usuarioDireccion.Usuario.getEmail());
            usuario.setPassword(usuarioDireccion.Usuario.getPassword());
            usuario.setSexo(usuarioDireccion.Usuario.getSexo());
            usuario.setFechaNacimiento(usuarioDireccion.Usuario.getFechaNacimiento());
            usuario.setTelefono(usuarioDireccion.Usuario.getTelefono());
            usuario.setCelular(usuarioDireccion.Usuario.getCelular());
            usuario.setCURP(usuarioDireccion.Usuario.getCURP());

            usuario.Rol = new com.digis01JEnriquezProgramacionNCapas.JPA.Rol();
            usuario.Rol.setIdRol(usuarioDireccion.Usuario.Rol.getIdRol());

            usuario.setImagen(usuarioDireccion.Usuario.getImagen());
            usuario.setStatus(usuarioDireccion.Usuario.getStatus());

            entityManager.persist(usuario);

            com.digis01JEnriquezProgramacionNCapas.JPA.Direccion direccion = new com.digis01JEnriquezProgramacionNCapas.JPA.Direccion();
            direccion.setIdDireccion(usuarioDireccion.Direccion.getIdDireccion());
            direccion.setCalle(usuarioDireccion.Direccion.getCalle());
            direccion.setNumeroInterior(usuarioDireccion.Direccion.getNumeroInterior());
            direccion.setNumeroExterior(usuarioDireccion.Direccion.getNumeroExterior());

            direccion.Colonia = new com.digis01JEnriquezProgramacionNCapas.JPA.Colonia();
            direccion.Colonia.setIdColonia(usuarioDireccion.Direccion.Colonia.getIdColonia());

            direccion.Usuario = new com.digis01JEnriquezProgramacionNCapas.JPA.Usuario();
            direccion.Usuario.setIdUsuario(usuario.getIdUsuario());

            entityManager.persist(direccion);

            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;

    }

    @Transactional
    @Override
    public Result UsuarioUpdateJPA(Usuario usuario) {
        Result result = new Result();
        try {

            com.digis01JEnriquezProgramacionNCapas.JPA.Usuario usuarioJPA = new com.digis01JEnriquezProgramacionNCapas.JPA.Usuario();

            usuarioJPA.setIdUsuario(usuario.getIdUsuario());
            usuarioJPA.setUserName(usuario.getUserName());
            usuarioJPA.setNombre(usuario.getNombre());
            usuarioJPA.setApellidoPaterno(usuario.getApellidoPaterno());
            usuarioJPA.setApellidoMaterno(usuario.getApellidoMaterno());
            usuarioJPA.setEmail(usuario.getEmail());
            usuarioJPA.setPassword(usuario.getPassword());
            usuarioJPA.setSexo(usuario.getSexo());
            usuarioJPA.setFechaNacimiento(usuario.getFechaNacimiento());
            usuarioJPA.setTelefono(usuario.getTelefono());
            usuarioJPA.setCelular(usuario.getCelular());
            usuarioJPA.setCURP(usuario.getCURP());
            usuarioJPA.setStatus(usuario.getStatus());

            usuarioJPA.Rol = new com.digis01JEnriquezProgramacionNCapas.JPA.Rol();
            usuarioJPA.Rol.setIdRol(usuario.Rol.getIdRol());
            usuarioJPA.setImagen(usuario.getImagen());

            entityManager.merge(usuarioJPA);

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Transactional
    @Override
    public Result DireccionUsuarioDeleteJPA(int IdUsuario) {
        Result result = new Result();

        try {
            com.digis01JEnriquezProgramacionNCapas.JPA.Usuario usuarioJPA = new com.digis01JEnriquezProgramacionNCapas.JPA.Usuario();
            com.digis01JEnriquezProgramacionNCapas.JPA.Direccion direccion = new com.digis01JEnriquezProgramacionNCapas.JPA.Direccion();

            TypedQuery<com.digis01JEnriquezProgramacionNCapas.JPA.Direccion> queryDirecciones = entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario = :idusuario", com.digis01JEnriquezProgramacionNCapas.JPA.Direccion.class);
            queryDirecciones.setParameter("idusuario", IdUsuario);
            List<com.digis01JEnriquezProgramacionNCapas.JPA.Direccion> listaDirecciones = queryDirecciones.getResultList();

            for (com.digis01JEnriquezProgramacionNCapas.JPA.Direccion direccionJPA : listaDirecciones) {
                entityManager.remove(direccionJPA);
            }

            usuarioJPA = entityManager.find(com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class, IdUsuario);

            entityManager.remove(usuarioJPA);

            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Transactional
    @Override
    public Result UpdateStatusJPA(int IdUsuario, int Status) {
        Result result = new Result();

        try {
            com.digis01JEnriquezProgramacionNCapas.JPA.Usuario usuarioJPA = new com.digis01JEnriquezProgramacionNCapas.JPA.Usuario();

            usuarioJPA = entityManager.find(com.digis01JEnriquezProgramacionNCapas.JPA.Usuario.class, IdUsuario);

            usuarioJPA.setStatus(Status);

            entityManager.merge(usuarioJPA);

            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result GetAllDinamicoJPA(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

}
