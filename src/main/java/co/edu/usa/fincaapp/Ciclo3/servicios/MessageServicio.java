package co.edu.usa.fincaapp.Ciclo3.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.usa.fincaapp.Ciclo3.entidades.Message;
import co.edu.usa.fincaapp.Ciclo3.repositorios.MessageRepository;

@Service
public class MessageServicio {
    @Autowired

    private MessageRepository messageRepository;
    
    public List<Message> getMessage(){
        return messageRepository.getTodosMensajes();
    }

    public Optional<Message> getMessagePorId(Long id) {
        return messageRepository.getMessagePorId(id);
    }
    

    public boolean deleteMessage(Long idMessage){
        Boolean d=getMessagePorId(idMessage).map(message -> {
        messageRepository.borrar(message);
            return true;
        }).orElse(false);
        return d;
    }

    public Message guardarMessage(Message message){
        if(message.getidMessage()==null){
            return messageRepository.guardar(message);
        }else{
            Optional<Message> e=messageRepository.getMessagePorId(message.getidMessage());
            if(e.isEmpty()){
                return messageRepository.guardar(message);
            }else{
                return message;
            }
        }
    }

    
    public  Message update(Message message){
        if(message.getidMessage()!=null){
            Optional<Message> g =messageRepository.getMessagePorId(message.getidMessage());
            if(!g.isEmpty()){
                if(message.getMessageText()!=null){
                    g.get().setMessageText(message.getMessageText());
                }
                
                return messageRepository.guardar(g.get());
            }
        }
              return message;
    }

    

}
