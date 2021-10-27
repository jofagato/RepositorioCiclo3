package co.edu.usa.fincaapp.Ciclo3.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usa.fincaapp.Ciclo3.entidades.Farm;
import java.util.Optional;
import co.edu.usa.fincaapp.Ciclo3.repositorios.FarmRepository;

@Service
public class FarmServicio {
    @Autowired
    private FarmRepository fincaRepository;

    public List<Farm> getFincas() {
        return fincaRepository.getTodasFinca();
    }

    public Optional<Farm> getFincaPorId(Long id) {
        return fincaRepository.getFincaPorId(id);
    }
    

    public Farm guardarFinca(Farm finca){
        if(finca.getId()==null){
            return fincaRepository.guardar(finca);
        }else{
            Optional<Farm> e=fincaRepository.getFincaPorId(finca.getId());
            if(e.isEmpty()){
                return fincaRepository.guardar(finca);
            }else{
                return finca;
            }
        }
    }


    public Farm update(Farm finca){
        if(finca.getId()!=null){
            Optional<Farm>g=fincaRepository.getFincaPorId(finca.getId());
            if(!g.isEmpty()){
                if(finca.getDescription()!=null){
                    g.get().setDescription(finca.getDescription());
                }
                if(finca.getName()!=null){
                    g.get().setName(finca.getName());
                }
                if (finca.getAddress()!=null){
                    g.get().setAddress(finca.getAddress());

                }

                if(finca.getExtension()!=0){
                    g.get().setExtension(finca.getExtension());
                }
                return fincaRepository.guardar(g.get());
            }
        }
              return finca;
    }



   public boolean deleteFarm(Long idFarm) {
        Boolean aBoolean = getFincaPorId(idFarm).map(idFarms -> {
           fincaRepository.borrar(idFarms);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
}


