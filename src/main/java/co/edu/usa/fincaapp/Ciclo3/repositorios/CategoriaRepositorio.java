package co.edu.usa.fincaapp.Ciclo3.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.usa.fincaapp.Ciclo3.entidades.*;


@Repository
public class  CategoriaRepositorio {
    @Autowired
    private CategoriaCrudRepository CategoriaRepositorio;

    public List<Category> getTodasCategoria(){
        return (List<Category>) CategoriaRepositorio.findAll();
    }
    
    public Optional<Category> getCategoriaPorId(Long idCategoria){
         return  CategoriaRepositorio.findById(idCategoria);
    }
    

    public Category guardar(Category g){
        return CategoriaRepositorio.save(g);
    }
    
    public void borrar(Category g){
        CategoriaRepositorio.delete(g);
    }
    
}
    

