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
import co.edu.usa.fincaapp.Ciclo3.entidades.Category;
import co.edu.usa.fincaapp.Ciclo3.servicios.CategoriaServicio;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/Category")
@CrossOrigin(origins = "*")

public class CategoriaController {
    @Autowired private CategoriaServicio CategoriaServicio;

    @GetMapping("/all")
    public List<Category> getTodasCategoria(){
        return CategoriaServicio.getCategoria();
    }

    @GetMapping("/{id}")
    public Optional<Category> getCategoria(@PathVariable("id") Long categoriaId) {
        return CategoriaServicio.getCategoriaid(categoriaId);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Category update(@RequestBody Category category) {
        return CategoriaServicio.update(category);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Category guardar(@RequestBody Category category){
        Category g = CategoriaServicio.guardarCategoria(category);
        return g;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Long categoriaId) {
        return CategoriaServicio.deleteCategoria(categoriaId);
    }



}
