package co.edu.usa.fincaapp.Ciclo3.api;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usa.fincaapp.Ciclo3.entidades.Administrators;
import co.edu.usa.fincaapp.Ciclo3.servicios.AdministratorServicio;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/Administrators")
@CrossOrigin(origins = "*")

public class AdministratorsController {
    @Autowired
     private AdministratorServicio administratorServicio;

     @GetMapping("/all")
     public List<Administrators> getTodosAdminiistrators(){
         return administratorServicio.getAdminisrators();
     }

     @GetMapping("/{id}")
     public Optional<Administrators> getCategoria(@PathVariable("id") Long id) {
         return administratorServicio.getAdministratorsid(id);
     }


    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Administrators update(@RequestBody Administrators administrators) {
        return administratorServicio.update(administrators);
    }


    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Administrators guardar(@RequestBody Administrators administrators){
        Administrators g = administratorServicio.guardarAdministrators(administrators);
        return g;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Long id) {
        return administratorServicio.deleteAdministrators(id);
    }

}
