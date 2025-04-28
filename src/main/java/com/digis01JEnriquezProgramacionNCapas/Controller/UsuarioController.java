package com.digis01JEnriquezProgramacionNCapas.Controller;

import com.digis01JEnriquezProgramacionNCapas.DAO.ColoniaDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.DAO.DireccionDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.DAO.EstadoDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.DAO.MunicipioDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.DAO.PaisDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.DAO.RolDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.ML.Colonia;
import com.digis01JEnriquezProgramacionNCapas.ML.Direccion;
import com.digis01JEnriquezProgramacionNCapas.ML.Result;
import com.digis01JEnriquezProgramacionNCapas.ML.ResultFile;
import com.digis01JEnriquezProgramacionNCapas.ML.Rol;
import com.digis01JEnriquezProgramacionNCapas.ML.Usuario;
import com.digis01JEnriquezProgramacionNCapas.ML.UsuarioDireccion;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;
    @Autowired
    private RolDAOImplementation rolDAOImplementation;
    @Autowired
    private PaisDAOImplementation paisDAOImplementation;
    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;
    @Autowired
    private MunicipioDAOImplementation municipioDAOImplementation;
    @Autowired
    private ColoniaDAOImplementation coloniaDAOImplementation;
    @Autowired
    private DireccionDAOImplementation direccionDAOImplementation;

    @GetMapping
    public String Index(Model model) {
        //Result result = usuarioDAOImplementation.GetAll();
        //Result resultRol = rolDAOImplementation.GetAll();
        Result result = usuarioDAOImplementation.GetAllJPA();
        Result resultRol = rolDAOImplementation.GetallJPA();
        Usuario usuarioBusqueda = new Usuario();
        usuarioBusqueda.Rol = new Rol();

        model.addAttribute("usuarioBusqueda", usuarioBusqueda);
        model.addAttribute("listaUsuarios", result.objects);
        model.addAttribute("listaRol", resultRol.objects);
        return "Index";
    }

    @PostMapping("/GetAllDinamico")
    public String BusquedaDinamica(@ModelAttribute Usuario usuario, Model model) {

        //Result result = usuarioDAOImplementation.GetAllDinamico(usuario);
        Result result = usuarioDAOImplementation.GetAllDinamicoJPA(usuario);
        //Result resultRol = rolDAOImplementation.GetAll();
        Result resultRol = rolDAOImplementation.GetallJPA();

        Usuario usuarioBusqueda = new Usuario();
        usuarioBusqueda.Rol = new Rol();

        model.addAttribute("listaUsuarios", result.objects);
        model.addAttribute("listaRol", resultRol.objects);
        model.addAttribute("usuarioBusqueda", usuarioBusqueda);

        return "Index";
    }

    @GetMapping("Form/{IdUsuario}")
    public String Form(@PathVariable int IdUsuario, Model model) {
        Result result = new Result();
        Result resultPais = new Result();
        //result = rolDAOImplementation.GetAll();
        result = rolDAOImplementation.GetallJPA();
        //resultPais = paisDAOImplementation.GetAll();
        resultPais = paisDAOImplementation.GetAllJPA();
        if (IdUsuario == 0) {
            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            usuarioDireccion.Usuario = new Usuario();
            usuarioDireccion.Usuario.Rol = new Rol();
            usuarioDireccion.Direccion = new Direccion();
            usuarioDireccion.Direccion.Colonia = new Colonia();

            model.addAttribute("listaRol", result.objects);
            model.addAttribute("listaPais", resultPais.objects);
            model.addAttribute("usuarioDireccion", usuarioDireccion);
            return "UsuarioForm";
        } else {
            //result = usuarioDAOImplementation.GetAllById(IdUsuario);
            result = usuarioDAOImplementation.GetByAllIdJPA(IdUsuario);
            model.addAttribute("listaUsuario", result.object);
            return "UsuarioView";
        }

    }

    @GetMapping("/FormView")
    public String FormView(Model model, @RequestParam int IdUsuario, @RequestParam(required = false) Integer IdDireccion) {
        Result result = new Result();
        //result = rolDAOImplementation.GetAll();
        result = rolDAOImplementation.GetallJPA();
        
        if (IdUsuario > 0 && IdDireccion == 0) {
            //Agrega una direccion
            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            usuarioDireccion.Usuario = new Usuario();
            usuarioDireccion.Usuario.setIdUsuario(IdUsuario);
            usuarioDireccion.Direccion = new Direccion();
            usuarioDireccion.Direccion.setIdDireccion(0);
            usuarioDireccion.Direccion.Colonia = new Colonia();

            //model.addAttribute("listaPais", paisDAOImplementation.GetAll().correct ? paisDAOImplementation.GetAll().objects : null);
            model.addAttribute("listaPais", paisDAOImplementation.GetAllJPA().correct ? paisDAOImplementation.GetAllJPA().objects: null);
            model.addAttribute("usuarioDireccion", usuarioDireccion);
        } else if (IdUsuario > 0 && IdDireccion > 0) {
            //Editar direccion
            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            usuarioDireccion.Usuario = new Usuario();
            usuarioDireccion.Usuario.setIdUsuario(IdUsuario);
            usuarioDireccion.Direccion = new Direccion();
            usuarioDireccion.Direccion.setIdDireccion(IdDireccion);

            //usuarioDireccion.Direccion = (Direccion) direccionDAOImplementation.GetByIdDireccion(IdDireccion).object;
            usuarioDireccion.Direccion = (Direccion) direccionDAOImplementation.GetByIdDireccionJPA(IdDireccion).object;

//            model.addAttribute("listaPais", paisDAOImplementation.GetAll().correct ? paisDAOImplementation.GetAll().objects : null);
//            model.addAttribute("listaEstados", estadoDAOImplementation.EstadoGetAllById(usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.getIdPais()).objects);
//            model.addAttribute("listaMunicipio", municipioDAOImplementation.MunicipioGetAllById(usuarioDireccion.Direccion.Colonia.Municipio.Estado.getIdEstado()).objects);
//            model.addAttribute("listaColonia", coloniaDAOImplementation.ColoniaGetAllById(usuarioDireccion.Direccion.Colonia.Municipio.getIdMunicipio()).objects);
            model.addAttribute("listaPais", paisDAOImplementation.GetAllJPA().correct ? paisDAOImplementation.GetAllJPA().objects : null);
            model.addAttribute("listaEstados", estadoDAOImplementation.EstadoGetAllByIdJPA(usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.getIdPais()).objects);
            model.addAttribute("listaMunicipio", municipioDAOImplementation.MunicipioGetAllByIdJPA(usuarioDireccion.Direccion.Colonia.Municipio.Estado.getIdEstado()).objects);
            model.addAttribute("listaColonia", coloniaDAOImplementation.ColoniaGetAllByIdJPA(usuarioDireccion.Direccion.Colonia.Municipio.getIdMunicipio()).objects);
            model.addAttribute("usuarioDireccion", usuarioDireccion);
        } else {
            //Editar un usuario
            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();

            //usuarioDireccion = (UsuarioDireccion) usuarioDAOImplementation.GetById(IdUsuario).object;
            usuarioDireccion = (UsuarioDireccion) usuarioDAOImplementation.GetByIdJPA(IdUsuario).object;
            
            usuarioDireccion.Direccion = new Direccion();
            usuarioDireccion.Direccion.setIdDireccion(IdDireccion);
            model.addAttribute("usuarioDireccion", usuarioDireccion);
            model.addAttribute("listaRol", result.objects);
        }

        return "UsuarioForm";
    }

    @GetMapping("/DeleteDireccion")
    public String DeleteDireccion(@RequestParam int IdDireccion) {
        //direccionDAOImplementation.DireccionDelete(IdDireccion);
        direccionDAOImplementation.DireccionDeleteJPA(IdDireccion);
        return "redirect:/usuario";
    }

    @GetMapping("/DeleteUsuario")
    public String DeleteUsuario(@RequestParam int IdUsuario) {
        //usuarioDAOImplementation.DireccionUsuarioDelete(IdUsuario);
        usuarioDAOImplementation.DireccionUsuarioDeleteJPA(IdUsuario);
        return "redirect:/usuario";
    }

    @GetMapping("/UpdateStatus/{IdUsuario}/{Status}")
    public String UpdateStatus(@PathVariable int IdUsuario, @PathVariable int Status) {
        //usuarioDAOImplementation.UpdateStatus(IdUsuario, Status);
        usuarioDAOImplementation.UpdateStatusJPA(IdUsuario, Status);
        return "redirect:/usuario";
    }

    @PostMapping("Form")
    public String Form(@Valid @ModelAttribute UsuarioDireccion usuarioDireccion, BindingResult BindingResult, @RequestParam(required = false) MultipartFile imagenFile, Model model) {
//        if(BindingResult.hasErrors()){
//            model.addAttribute("listaUsuario", usuarioDireccion);
//            
//            return "UsuarioForm";
//        }
        usuarioDireccion.Usuario.setStatus(1);
        try {
            if (!imagenFile.isEmpty()) {
                byte[] bytes = imagenFile.getBytes();
                String imgBase64 = Base64.getEncoder().encodeToString(bytes);
                usuarioDireccion.Usuario.setImagen(imgBase64);

            }
        } catch (Exception ex) {

        }
        if (usuarioDireccion.Usuario.getIdUsuario() > 0 && usuarioDireccion.Direccion.getIdDireccion() == 0) {
            //Agregar una direccion
            //direccionDAOImplementation.DireccionAdd(usuarioDireccion);
            direccionDAOImplementation.DireccionAddJPA(usuarioDireccion);
        } else {
            if (usuarioDireccion.Usuario.getIdUsuario() > 0 && usuarioDireccion.Direccion.getIdDireccion() > 0) {
                //Editar Direccion
                //direccionDAOImplementation.DireccionUpdate(usuarioDireccion.Direccion);
                direccionDAOImplementation.DireccionUpdateJPA(usuarioDireccion.Direccion);
            } else if (usuarioDireccion.Usuario.getIdUsuario() > 0 && usuarioDireccion.Direccion.getIdDireccion() == -1) {
                //Editar usuario
                //usuarioDAOImplementation.UsuarioUpdate(usuarioDireccion.Usuario);
                usuarioDAOImplementation.UsuarioUpdateJPA(usuarioDireccion.Usuario);
            } else {
                //Agregar Usuario y Direccion
                //usuarioDAOImplementation.Add(usuarioDireccion);
                usuarioDAOImplementation.AddJPA(usuarioDireccion);
            }
        }
        return ("redirect:/usuario");
    }

    @GetMapping("/EstadoGetAllById/{IdPais}")
    @ResponseBody
    public Result EstadoGetAllById(@PathVariable int IdPais) {
        //Result result = estadoDAOImplementation.EstadoGetAllById(IdPais);
        Result result = estadoDAOImplementation.EstadoGetAllByIdJPA(IdPais);

        return result;
    }

    @GetMapping("/MunicipioGetAllById/{IdEstado}")
    @ResponseBody
    public Result MunicipioGetAllById(@PathVariable int IdEstado) {
        //Result result = municipioDAOImplementation.MunicipioGetAllById(IdEstado);
        Result result = municipioDAOImplementation.MunicipioGetAllByIdJPA(IdEstado);
        return result;
    }

    @GetMapping("/ColoniaGetAllById/{IdMunicipio}")
    @ResponseBody
    public Result ColoniaGetAllById(@PathVariable int IdMunicipio) {
        //Result result = coloniaDAOImplementation.ColoniaGetAllById(IdMunicipio);
        Result result = coloniaDAOImplementation.ColoniaGetAllByIdJPA(IdMunicipio);
        return result;
    }

    @GetMapping("/ColoniaGetAllByCP/{CodigoPostal}")
    @ResponseBody
    public Result ColoniaGetAllByCP(@PathVariable String CodigoPostal) {
        //Result result = coloniaDAOImplementation.ColoniaGetAllByCP(CodigoPostal);
        Result result = coloniaDAOImplementation.ColoniaGetAllByCPJPA(CodigoPostal);
        return result;
    }
    
    @GetMapping("/CargaMasiva")
    public String CargaMsiva() {
        return "CargaMasiva";
    }

    @PostMapping("/CargaMasiva")
    public String CargaMasiva(@RequestParam MultipartFile archivo, Model model, HttpSession session) {
        try {
            if (archivo != null && !archivo.isEmpty()) {
                String tipoArchivo = archivo.getOriginalFilename().split("\\.")[1];
                String root = System.getProperty("user.dir"); //Obtener direccion del proyecto en el equipo
                String path = "src/main/resources/static/archivos";//Path relativo dentro del proyecto
                String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmSS"));
                String absolutePath = root + "/" + path + "/" + fecha + archivo.getOriginalFilename();
                archivo.transferTo(new File(absolutePath));

                List<UsuarioDireccion> listaUsuarios = new ArrayList();
                if (tipoArchivo.equals("txt")) {
                    listaUsuarios = LecturaArchivoTXT(new File(absolutePath));
                }else{
                    listaUsuarios = LecturaArchivoExcel(new File(absolutePath));
                }
                List<ResultFile> listaErrores = ValidarArchivo(listaUsuarios);

                if (listaErrores.isEmpty()) {
                    session.setAttribute("urlFile", absolutePath);
                    model.addAttribute("listaErrores", listaErrores);
                    model.addAttribute("success", true);
                } else {
                    model.addAttribute("listaErrores", listaErrores);
                }
            }
        } catch (Exception ex) {
            return "redirect:/usuario/CargaMasiva";
        }

        return "CargaMasiva";
    }

    @GetMapping("/Procesar")
    public String Procesar(HttpSession session) {
        String absolutePath = session.getAttribute("urlFile").toString();
        String tipoArchivo = session.getAttribute("urlFile").toString().split("\\.")[1];
        List<UsuarioDireccion> listaUsuarios = new ArrayList<>();

        if (tipoArchivo.equals("txt")) {
             listaUsuarios = LecturaArchivoTXT(new File(absolutePath));
        }else{
            listaUsuarios = LecturaArchivoExcel(new File(absolutePath));
        }        
        
        for (UsuarioDireccion usuarioDireccion : listaUsuarios) {
            //usuarioDAOImplementation.Add(usuarioDireccion);
            usuarioDAOImplementation.AddJPA(usuarioDireccion);
        }
        return "CargaMasiva";
    }

    public List<UsuarioDireccion> LecturaArchivoTXT(File archivo) {
        List<UsuarioDireccion> listaUsuarios = new ArrayList<>();

        try (FileReader fileReader = new FileReader(archivo); BufferedReader bufferedReader = new BufferedReader(fileReader);) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] campos = linea.split("\\|");
                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                usuarioDireccion.Usuario = new Usuario();
                usuarioDireccion.Usuario.setUserName(campos[0]);
                usuarioDireccion.Usuario.setNombre(campos[1]);
                usuarioDireccion.Usuario.setApellidoPaterno(campos[2]);
                usuarioDireccion.Usuario.setApellidoMaterno(campos[3]);
                usuarioDireccion.Usuario.setEmail(campos[4]);
                usuarioDireccion.Usuario.setPassword(campos[5]);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //Dar el formato a la fecha
                usuarioDireccion.Usuario.setFechaNacimiento(formatter.parse(campos[6]));
                usuarioDireccion.Usuario.setSexo(campos[7].charAt(0));
                usuarioDireccion.Usuario.setTelefono(campos[8]);
                usuarioDireccion.Usuario.setCelular(campos[9]);
                usuarioDireccion.Usuario.setCURP(campos[10]);

                usuarioDireccion.Usuario.Rol = new Rol();
                usuarioDireccion.Usuario.Rol.setIdRol(Integer.parseInt(campos[11]));
                usuarioDireccion.Usuario.setImagen(null);
                usuarioDireccion.Usuario.setStatus(Integer.parseInt(campos[12]));

                usuarioDireccion.Direccion = new Direccion();
                usuarioDireccion.Direccion.setCalle(campos[13]);
                usuarioDireccion.Direccion.setNumeroInterior(campos[14]);
                usuarioDireccion.Direccion.setNumeroExterior(campos[15]);

                usuarioDireccion.Direccion.Colonia = new Colonia();
                usuarioDireccion.Direccion.Colonia.setIdColonia(Integer.parseInt(campos[16]));

                listaUsuarios.add(usuarioDireccion);

            }
        } catch (Exception ex) {
            listaUsuarios = null;
        }
        return listaUsuarios;
    }
    
    public List<UsuarioDireccion> LecturaArchivoExcel(File archivo){
        List<UsuarioDireccion> listaUsuarios = new ArrayList<>();
        try(XSSFWorkbook woorkBook = new XSSFWorkbook(archivo)){
            for (Sheet sheet : woorkBook) {
                for (Row row : sheet) {
                    UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                    usuarioDireccion.Usuario = new Usuario();
                    
                    usuarioDireccion.Usuario.setUserName(row.getCell(0).toString());
                    usuarioDireccion.Usuario.setNombre(row.getCell(1).toString());
                    usuarioDireccion.Usuario.setApellidoPaterno(row.getCell(2).toString());
                    usuarioDireccion.Usuario.setApellidoMaterno(row.getCell(3) != null ? row.getCell(3).toString() : "X");
                    usuarioDireccion.Usuario.setEmail(row.getCell(4).toString());
                    usuarioDireccion.Usuario.setPassword(row.getCell(5).toString());
                    usuarioDireccion.Usuario.setFechaNacimiento(row.getCell(6).getDateCellValue());
                    usuarioDireccion.Usuario.setSexo(row.getCell(7).toString().charAt(0));
                    DataFormatter dataFormatter = new DataFormatter();
                    usuarioDireccion.Usuario.setTelefono(dataFormatter.formatCellValue( row.getCell(8)));
                    usuarioDireccion.Usuario.setCelular( dataFormatter.formatCellValue(row.getCell(9)));
                    usuarioDireccion.Usuario.setCURP(row.getCell(10) != null ? (row.getCell(10).getStringCellValue()) : null);
                    usuarioDireccion.Usuario.Rol = new Rol();
                    usuarioDireccion.Usuario.Rol.setIdRol(row.getCell(11) != null ? ((int) row.getCell(11).getNumericCellValue()) : 3);
                    usuarioDireccion.Usuario.setImagen(null);
                    usuarioDireccion.Usuario.setStatus(row.getCell(12) != null ? ((int) row.getCell(12).getNumericCellValue()) : 1);
                    
                    usuarioDireccion.Direccion = new Direccion();
                    usuarioDireccion.Direccion.setCalle(row.getCell(13).getStringCellValue());
                    usuarioDireccion.Direccion.setNumeroInterior(dataFormatter.formatCellValue(row.getCell(14)));
                    usuarioDireccion.Direccion.setNumeroExterior(dataFormatter.formatCellValue(row.getCell(15)));
                    
                    usuarioDireccion.Direccion.Colonia = new Colonia();
                    usuarioDireccion.Direccion.Colonia.setIdColonia(row.getCell(16) != null ? (int) row.getCell(16).getNumericCellValue() : 0);
                    
                    listaUsuarios.add(usuarioDireccion);
                }
            }
        }catch(Exception ex){
            
        }
        return listaUsuarios;
    }
    public List<ResultFile> ValidarArchivo(List<UsuarioDireccion> listaUsuarios) {
        List<ResultFile> listaErrores = new ArrayList<>();

        if (listaUsuarios == null) {
            listaErrores.add(new ResultFile(0, "La lista es nula", "La lista es nula"));
        } else if (listaUsuarios.isEmpty()) {
            listaErrores.add(new ResultFile(0, "La lista esta vacia", "La lista esta vacia"));
        } else {
            int fila = 1;
            for (UsuarioDireccion usuarioDireccion : listaUsuarios) {

                if (usuarioDireccion.Usuario.getUserName() == null || usuarioDireccion.Usuario.getUserName().equals("")) {
                    listaErrores.add(new ResultFile(fila, usuarioDireccion.Usuario.getUserName(), "El UserName es obligatorio"));
                }

                if (usuarioDireccion.Usuario.getNombre() == null || usuarioDireccion.Usuario.getNombre().equals("")) {
                    listaErrores.add(new ResultFile(fila, usuarioDireccion.Usuario.getNombre(), "El Nombre es Obligatorio"));
                }

                if (usuarioDireccion.Usuario.getApellidoPaterno() == null || usuarioDireccion.Usuario.getApellidoPaterno().equals("")) {
                    listaErrores.add(new ResultFile(fila, usuarioDireccion.Usuario.getApellidoPaterno(), "El Apellido Paterno es Obligatorio"));
                }
                
                if (usuarioDireccion.Usuario.getEmail() == null || usuarioDireccion.Usuario.getEmail().equals("")){
                    listaErrores.add(new ResultFile(fila, usuarioDireccion.Usuario.getEmail(), "El Email es obligatorio"));
                }
                
                if (usuarioDireccion.Usuario.getPassword() == null || usuarioDireccion.Usuario.getPassword().equals("")){
                    listaErrores.add(new ResultFile(fila, usuarioDireccion.Usuario.getPassword(), "La contrsaeña es obligatoria"));
                }
                
                if (usuarioDireccion.Usuario.getFechaNacimiento() == null || usuarioDireccion.Usuario.getFechaNacimiento().equals("")){
                    listaErrores.add(new ResultFile(fila, usuarioDireccion.Usuario.getFechaNacimiento().toString(), "La fecha es obligatoria"));
                }
                
                if (String.valueOf(usuarioDireccion.Usuario.getSexo()) == null || String.valueOf(usuarioDireccion.Usuario.getSexo()).equals("")){
                    listaErrores.add(new ResultFile(fila, String.valueOf(usuarioDireccion.Usuario.getSexo()), "No se le asignado el sexo, el campo es obligatorio"));
                }
                
                if (usuarioDireccion.Usuario.getTelefono() == null || usuarioDireccion.Usuario.getTelefono().equals("")) {
                    listaErrores.add(new ResultFile(fila, usuarioDireccion.Usuario.getTelefono(), "Telefono es oblogatorio"));
                }
                
                if (Integer.toString(usuarioDireccion.Usuario.Rol.getIdRol()) == null || usuarioDireccion.Usuario.Rol.getIdRol() == 0 || Integer.toString(usuarioDireccion.Usuario.Rol.getIdRol()).equals("")){
                    listaErrores.add(new ResultFile(fila, Integer.toString(usuarioDireccion.Usuario.Rol.getIdRol()), "El rol es obligatorio"));
                }
                
                if (Integer.toString(usuarioDireccion.Usuario.getStatus()) == null || Integer.toString(usuarioDireccion.Usuario.getStatus()).equals("")){
                    listaErrores.add(new ResultFile(fila, Integer.toString(usuarioDireccion.Usuario.getStatus()), "El Status es obligatorio"));
                }
                
                if (usuarioDireccion.Direccion.getCalle() == null || usuarioDireccion.Direccion.getCalle().equals("")){
                    listaErrores.add(new ResultFile(fila, usuarioDireccion.Direccion.getCalle(), "La calle es un campo obligatorio"));
                }
                
                if (usuarioDireccion.Direccion.getNumeroExterior() == null || usuarioDireccion.Direccion.getNumeroExterior().equals("")){
                    listaErrores.add(new ResultFile(fila, usuarioDireccion.Direccion.getNumeroExterior(), "El numero exterior es obligatorio"));
                }
                
                if (Integer.toString(usuarioDireccion.Direccion.Colonia.getIdColonia()) == null || Integer.toString(usuarioDireccion.Direccion.Colonia.getIdColonia()).equals("")){
                    listaErrores.add(new ResultFile(fila, Integer.toString(usuarioDireccion.Direccion.Colonia.getIdColonia()), "La colonia es obligatoria"));
                }
                fila++;
            }
        }
        return listaErrores;
    }
}
