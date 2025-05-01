package com.digis01JEnriquezProgramacionNCapas.RestController;

import com.digis01JEnriquezProgramacionNCapas.ML.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demoapi")
public class DemoRestController {

    @GetMapping("saludo")
    public String Saludo(@RequestParam String nombre) {
        return "Hola mundo " + nombre;
    }

    @GetMapping("saludo2")
    public ResponseEntity Saludo2() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Jose");
        usuario.setApellidoPaterno("Enriquez");
        usuario.setApellidoMaterno("Garcia");

        return ResponseEntity.accepted().body(usuario);
    }

    @GetMapping("suma/{numero1}/{numero2}")
    public ResponseEntity Suma(@PathVariable int numero1, @PathVariable int numero2) {
        int resultado = numero1 + numero2;
        
        return ResponseEntity.ok("El resultado es: " + String.valueOf(resultado));
    }

    @GetMapping("resta/{numero1}/{numero2}")
    public ResponseEntity Resta(@PathVariable int numero1, @PathVariable int numero2) {
        int resultado = numero1 - numero2;
        
        return ResponseEntity.ok("El resultado de la Resta es: " + String.valueOf(resultado));
    }

    @GetMapping("multiplicacion/{numero1}/{numero2}")
    public ResponseEntity Multiplicacion(@PathVariable int numero1, @PathVariable int numero2) {
        int resultado = numero1 * numero2;
        
        return ResponseEntity.ok("El resultado de la Multiplicacion es: " + String.valueOf(resultado));
    }

    @GetMapping("division/{numero1}/{numero2}")
    public ResponseEntity Division(@PathVariable int numero1, @PathVariable int numero2) {
        if (numero1 == 0 || numero2 == 0) {
            return ResponseEntity.badRequest().body("No se puede dividir en 0");
        }else{
            int resultado = numero1 + numero2;
            return  ResponseEntity.ok().body("El resultado es:" + String.valueOf(resultado));
        }
    }
    
    @PostMapping("suma2")
    public ResponseEntity Suma2(@RequestBody List<String> numeros){
        String resultado = "";
        return ResponseEntity.ok().body(resultado);
    }
}
