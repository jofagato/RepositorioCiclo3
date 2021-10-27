package co.edu.usa.fincaapp.Ciclo3.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.usa.fincaapp.Ciclo3.entidades.Farm;
@Repository
public class FarmRepository {
 
    @Autowired 
    private FarmCrudRepository fincaRepositorio;

    public List<Farm> getTodasFinca(){
        return (List<Farm>)fincaRepositorio.findAll();
    }

    public Optional<Farm> getFincaPorId(Long idFinca){
        return  fincaRepositorio.findById(idFinca);
   }
   
    public Farm guardar(Farm f){
        return fincaRepositorio.save(f);
    }
    public void borrar(Farm f){
        fincaRepositorio.delete(f);
    }
}
