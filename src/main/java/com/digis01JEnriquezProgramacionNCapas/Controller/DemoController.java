package com.digis01JEnriquezProgramacionNCapas.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoController {
    
    @GetMapping("operacion/{op}/{numeroUno}/{numeroDos}")
    public String Operacion(@PathVariable int op, @PathVariable String numeroUno, @PathVariable String numeroDos){
        int numeroOne = Integer.parseInt(numeroUno);
        int numeroTwo = Integer.parseInt(numeroDos);
        int result;
        switch (op) {
            case 1: 
                System.out.println("Suma: ");
                result = numeroOne + numeroTwo;
                System.out.println("El resultado es: " + result);
                break;
            case 2:
                System.out.println("Resta");
                result = numeroOne - numeroTwo;
                System.out.println("El resultado es: " + result);
                break;
            case 3:
                System.out.println("Multiplicacion");
                result = numeroOne * numeroTwo;
                System.out.println("El resultado es: " + result);
                break;
            case 4:
                System.out.println("Division");
                result = numeroOne / numeroTwo;
                System.out.println("El resultado es: " + result);
                break;
            default: 
                throw new AssertionError();
        }
        return "Resultado";
    }
    
    @GetMapping()
    public String UsuarioIndex(){
        return "Index";
    }
    
    @GetMapping("formulario")
    public String Formulario(){
        return "Formulario";
    }
}
