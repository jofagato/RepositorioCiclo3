
package co.edu.usa.fincaapp.Ciclo3.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usa.fincaapp.Ciclo3.entidades.*;
import co.edu.usa.fincaapp.Ciclo3.repositorios.AdministratorsRepository;


@Service
public class AdministratorServicio {
    @Autowired

    private AdministratorsRepository AdministratorsRepositorio;

    public List<Administrators>getAdminisrators(){
        return AdministratorsRepositorio.getTodosAdministrators();
    }


    public Administrators guardarAdministrators(Administrators administrators){
        if(administrators.getId()==null){
            return AdministratorsRepositorio.guardar(administrators);
        }else{
         Optional<Administrators> e= AdministratorsRepositorio.getAdministratorsPorId(administrators.getId());
         if (e.isEmpty()){
            return AdministratorsRepositorio.guardar(administrators);

         }else{

            return administrators;
         }


        }
        
    }


    public Optional<Administrators> getAdministratorsid(Long id) {
        return AdministratorsRepositorio.getAdministratorsPorId(id);
    }

    public boolean deleteAdministrators(Long id){
        Boolean d=getAdministratorsid(id).map(category -> {
            AdministratorsRepositorio.borrar(category);
            return true;
        }).orElse(false);
        return d;
    }


    public Administrators update(Administrators administrators){
        if(administrators.getId()!=null){
            Optional<Administrators>g=AdministratorsRepositorio.getAdministratorsPorId(administrators.getId());
            if(!g.isEmpty()){
                if(administrators.getName()!=null){
                    g.get().setName(administrators.getName());
                }
                if(administrators.getEmail()!=null){
                    g.get().setEmail(administrators.getEmail());
                }
                if(administrators.getPassword()!=null){
                    g.get().setPassword(administrators.getPassword());
                }

                return AdministratorsRepositorio.guardar(g.get());
            }
        }
        return administrators;
    }




}
