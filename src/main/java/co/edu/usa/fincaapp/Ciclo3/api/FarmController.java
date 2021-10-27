package co.edu.usa.fincaapp.Ciclo3.api;


import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usa.fincaapp.Ciclo3.entidades.Farm;
import co.edu.usa.fincaapp.Ciclo3.servicios.FarmServicio;

@RestController
@RequestMapping("/api/Farm")
@CrossOrigin(origins = "*")

public class FarmController {
    @Autowired private FarmServicio FarmServicio;

    @GetMapping("/all")
    public List<Farm> getTodasFincas(){
        return FarmServicio.getFincas();
    }

    @GetMapping("/{id}")
    public Optional<Farm> getFincaPorId(@PathVariable("id") Long id) {
        return FarmServicio.getFincaPorId(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Farm guardar(@RequestBody Farm Farm){
        Farm f = FarmServicio.guardarFinca(Farm);
        return f;
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Farm update(@RequestBody Farm Farm) {
        return FarmServicio.update(Farm);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Long id) {
        return FarmServicio.deleteFarm(id);
    }
    
}
