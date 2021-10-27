package co.edu.usa.fincaapp.Ciclo3.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.usa.fincaapp.Ciclo3.entidades.*;
import co.edu.usa.fincaapp.Ciclo3.repositorios.ClienteRepository;
@Service
public class ClienteServicio {
    @Autowired
    private ClienteRepository clienteRespositorio;

    public List<Client> obtenerTodoClientes(){
        return clienteRespositorio.getTodosClientes();
    }

    public Optional<Client> getCliente(Long id){
        return clienteRespositorio.getClientePorId(id);
    }

    public Client guardar(Client cliente) {
        if(cliente != null){
            if(cliente.getIdClient() != null){
                Optional<Client> oCliente = clienteRespositorio.getClientePorId(cliente.getIdClient());
                if(oCliente.isEmpty()){
                    return clienteRespositorio.guardar(cliente);
                }else{
                    return cliente;
                }
            }else{
                return clienteRespositorio.guardar(cliente);
            }
        }
        return null;
    }  
    
    public Client actualizar(Client cliente) {
        if(cliente.getIdClient() != null){
            Optional<Client> oCliente = clienteRespositorio.getClientePorId(cliente.getIdClient());
            if(!oCliente.isEmpty()){
                Client c = oCliente.get();
                if(cliente.getName()!= null){
                    c.setName(cliente.getName());
                }
                if(cliente.getEmail()!= null){
                    c.setEmail(cliente.getEmail());
                }
                if(cliente.getAge() != null){
                    c.setAge(cliente.getAge());
                }
                if(cliente.getPassword()!= null){
                    c.setPassword(cliente.getPassword());
                }

                return clienteRespositorio.guardar(c);
            }else{
                return cliente;
            }
        }else{
            return cliente;
        }
    }

    public boolean borrarCliente(Long id){
        Boolean borrado =  getCliente(id).map(c -> {
            clienteRespositorio.borrar(c);
            return true;
        }).orElse(false);
        return borrado;
    
    }
}
