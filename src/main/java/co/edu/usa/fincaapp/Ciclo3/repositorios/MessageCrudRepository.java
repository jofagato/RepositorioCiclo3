package co.edu.usa.fincaapp.Ciclo3.repositorios;
import org.springframework.data.repository.CrudRepository;

import co.edu.usa.fincaapp.Ciclo3.entidades.Message;

public interface MessageCrudRepository extends CrudRepository<Message,Long>{
    
}
