package com.digis01JEnriquezProgramacionNCapas.ML;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class Usuario {
    
    private int IdUsuario;
    
    @NotNull(message = "No debe ser nulo")
    @NotEmpty(message = "No debe estar vacio")
    @NotBlank(message = "El campo es requerido")
    @Size(min = 3, max = 50, message = "Debe contener entre 3 y 50 carateres")
    private String UserName;
    
    @NotNull(message = "No debe ser nulo")
    @NotEmpty(message = "No debe estar vacio")
    @Size(min = 3, max = 50, message = "Debe contener entre 3 y 50 carateres")
    private String Nombre;
    
    @NotNull(message = "No debe ser nulo")
    @NotEmpty(message = "No debe estar vacio")
    @Size(min = 3, max = 50, message = "Debe contener entre 3 y 50 caracteres")
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    
    @NotNull(message = "No debe ser nulo")
    @NotEmpty(message = "No debe estar vacio")
    @NotBlank(message = "El campo es requerido")
    @Size(max = 50, message = "El limite es de 50 caracteres")
    @Email(message = "Debe ser un correo electronico")
    @Pattern(regexp = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$", message = "Ingresa un email Valido")
    private String Email;
    
    @NotNull(message = "No debe ser nulo")
    @NotEmpty(message = "No debe estar vacio")
    @Size(min = 8, max = 50, message = "al menos debe ser de 8 caracteres")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "Debe contener al menos una letra en mayuscula, un caracter especial y un numero")
    private String Password;
    
    @NotNull (message = "No debe ser nulo")
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date FechaNacimiento;
    
    @NotNull (message = "No debe ser nulo")
    private char Sexo;
    
    @NotNull(message = "No debe ser nulo")
    @NotEmpty(message = "No debe estar vacio")
    @NotBlank(message = "El campo es requerido")
    @Size(min = 10, max = 20, message = "Debe contener la menos 10 digitos")
    @Pattern(regexp = "^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$", message = "Formato invalido")
    private String Telefono;
    
    @Size(min = 10, max = 20, message = "Debe contener la menos 10 digitos")
    @Pattern(regexp = "^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$", message = "Formato invalido")
    private String Celular;
    
    @Pattern(regexp = "^([A-Z][AEIOUX][A-Z]{2}\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01])[HM](?:AS|B[CS]|C[CLMSH]|D[FG]|G[TR]|HG|JC|M[CNS]|N[ETL]|OC|PL|Q[TR]|S[PLR]|T[CSL]|VZ|YN|ZS)[B-DF-HJ-NP-TV-Z]{3}[A-Z\\d])(\\d)$", message="Ingresa una CURP valida")
    private String CURP;

    private String Imagen;
    
    private Integer Status;
    
    @Valid
    public Rol Rol;
    
    //Usuario
    public int getIdUsuario(){
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario){
        this.IdUsuario = IdUsuario;
    }
    
    public String getUserName(){
        return UserName;
    }
    
    public void setUserName(String UserName){
        this.UserName = UserName;
    }
    
    public String getNombre(){
        return Nombre;
    }
    
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }
    
    public String getApellidoPaterno(){
        return ApellidoPaterno;
    }
    
    public void setApellidoPaterno(String ApellidoPaterno){
        this.ApellidoPaterno = ApellidoPaterno;
    }
    
    public String getApellidoMaterno(){
        return ApellidoMaterno;
    }
    
    public void setApellidoMaterno(String ApellidoMaterno){
        this.ApellidoMaterno = ApellidoMaterno;
    }
    
    public String getEmail(){
        return Email;
    }
    
    public void setEmail(String Email){
        this.Email = Email;
    }
    
    public String getPassword(){
        return Password;
    }
    
    public void setPassword(String Password){
        this.Password = Password;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }
     
    
    public char getSexo(){
        return Sexo;
    }
    
    public void setSexo(char Sexo){
        this.Sexo = Sexo;
    }
    
    public String getTelefono(){
        return Telefono;
    }
    
    public void setTelefono (String Telefono){
        this.Telefono = Telefono;
    }
    
    public String getCelular(){
        return Celular;
    }
    
    public void setCelular(String Celular){
        this.Celular = Celular;
    }
    
    public String getCURP() {
        return CURP;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public Rol getRol() {
        return Rol;
    }

    public void setRol(Rol Rol) {
        this.Rol = Rol;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }
   
    public Integer getStatus(){
        return Status;
    }
    
    public void setStatus(Integer Status){
        this.Status = Status;
    }
}
