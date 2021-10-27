package co.edu.usa.fincaapp.Ciclo3.repositorios;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.usa.fincaapp.Ciclo3.entidades.Client;

@Repository
public class ClienteRepository {
    @Autowired
    private ClienteCrudRepository clienteRespositorio;

    public List<Client> getTodosClientes(){
        return (List<Client>) clienteRespositorio.findAll();
    }
    public Optional<Client> getClientePorId(Long id){
        return clienteRespositorio.findById(id);
    }
    public Client guardar(Client cliente){
        return clienteRespositorio.save(cliente);
    }
    public void borrar(Client cliente){
        clienteRespositorio.delete(cliente);
    }
    public void borrarPorIdCliente(Long id){
        clienteRespositorio.deleteById(id);
        
    }
}
