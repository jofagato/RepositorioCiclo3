package co.edu.usa.fincaapp.Ciclo3.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usa.fincaapp.Ciclo3.entidades.Category;
import co.edu.usa.fincaapp.Ciclo3.repositorios.CategoriaRepositorio;
@Service
public class CategoriaServicio {
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    public List<Category> getCategoria(){
        return categoriaRepositorio.getTodasCategoria();
    }

    public Category guardarCategoria(Category category){
        if(category.getId()==null){
            return categoriaRepositorio.guardar(category);
        }else{
         Optional<Category> e= categoriaRepositorio.getCategoriaPorId(category.getId());
         if (e.isEmpty()){
            return categoriaRepositorio.guardar(category);

         }else{

            return category;
         }


        }
        
    }

    public Optional<Category> getCategoriaid(Long categoriaId) {
        return categoriaRepositorio.getCategoriaPorId(categoriaId);
    }
    
    public boolean deleteCategoria(Long categoriaId){
        Boolean d=getCategoriaid(categoriaId).map(category -> {
        categoriaRepositorio.borrar(category);
            return true;
        }).orElse(false);
        return d;
    }

    public Category update(Category category){
        if(category.getId()!=null){
            Optional<Category>g=categoriaRepositorio.getCategoriaPorId(category.getId());
            if(!g.isEmpty()){
                if(category.getDescription()!=null){
                    g.get().setDescription(category.getDescription());
                }
                if(category.getName()!=null){
                    g.get().setName(category.getName());
                }
                return categoriaRepositorio.guardar(g.get());
            }
        }
        return category;
    }

}
