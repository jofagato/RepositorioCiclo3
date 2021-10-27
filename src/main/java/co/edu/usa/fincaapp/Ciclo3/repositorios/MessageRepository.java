package co.edu.usa.fincaapp.Ciclo3.repositorios;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.usa.fincaapp.Ciclo3.entidades.*;



@Repository
public class MessageRepository {
    @Autowired
    private MessageCrudRepository messageRespositorio;

    public List<Message> getTodosMensajes(){
        return (List<Message>) messageRespositorio.findAll();
    }
    public Optional<Message> getMessagePorId(Long id){
        return messageRespositorio.findById(id);
    }
    public Message guardar(Message message){
        return messageRespositorio.save(message);
    }
    public void borrar(Message message){
        messageRespositorio.delete(message);
    }
    public void borrarPorIdMessage(Long id){
        messageRespositorio.deleteById(id);
        
    }
}
