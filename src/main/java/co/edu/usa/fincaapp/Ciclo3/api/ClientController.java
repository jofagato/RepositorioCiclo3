package co.edu.usa.fincaapp.Ciclo3.api;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usa.fincaapp.Ciclo3.entidades.Client;
import co.edu.usa.fincaapp.Ciclo3.servicios.ClienteServicio;

@RestController
@RequestMapping("/api/Client")
@CrossOrigin(origins = "*")
public class ClientController {
    @Autowired private ClienteServicio ClienteServicio;

    @GetMapping("/all")
    public List<Client> getTodosClientes(){
        return ClienteServicio.obtenerTodoClientes();
    }


    @GetMapping("/{id}")
    public Optional<Client> getClient(@PathVariable("id") Long clientId) {
        return ClienteServicio.getCliente(clientId);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Client guardar(@RequestBody Client Client){
        Client c = ClienteServicio.guardar(Client);
        return c;
    }


    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Client update(@RequestBody Client client) {
        return ClienteServicio.actualizar(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Long clientId) {
        return ClienteServicio.borrarCliente(clientId);
    }
}
