package co.edu.usa.fincaapp.Ciclo3.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import co.edu.usa.fincaapp.Ciclo3.entidades.Message;
import co.edu.usa.fincaapp.Ciclo3.servicios.MessageServicio;


@RestController
@RequestMapping("/api/Message")
@CrossOrigin(origins = "*")

public class MessageController {

@Autowired private MessageServicio MessageServicio;

@GetMapping("/all")
public List<Message> getTodosMensajes() {
    return MessageServicio.getMessage();
    }

@GetMapping("/{id}")
public Optional<Message> getMessage(@PathVariable("id") Long idMessage) {
return MessageServicio.getMessagePorId(idMessage);
    }


@PutMapping("/update")
@ResponseStatus(HttpStatus.CREATED)
public Message update(@RequestBody Message message) {
return MessageServicio.update(message);
    }

@PostMapping("/save")
@ResponseStatus(HttpStatus.CREATED)
public Message guardar(@RequestBody Message Message){
    Message m = MessageServicio.guardarMessage(Message);
    return m;
}

@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public boolean delete(@PathVariable("id") Long idMessage) {
    return MessageServicio.deleteMessage(idMessage);
}



}
