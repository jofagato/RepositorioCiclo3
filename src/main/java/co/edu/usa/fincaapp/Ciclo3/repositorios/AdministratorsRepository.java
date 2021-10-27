package co.edu.usa.fincaapp.Ciclo3.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.usa.fincaapp.Ciclo3.entidades.*;




@Repository
public class AdministratorsRepository {
    @Autowired
    private AdministratorsCrudRepository AdministratorRepositorio;

    public List<Administrators> getTodosAdministrators(){
        return (List<Administrators>) AdministratorRepositorio.findAll();
    }
    
    public Optional<Administrators> getAdministratorsPorId(Long id){
         return  AdministratorRepositorio.findById(id);
    }

    public Administrators guardar(Administrators g){
        return AdministratorRepositorio.save(g);
    }
    
    public void borrar(Administrators g){
        AdministratorRepositorio.delete(g);
    }
    
}

