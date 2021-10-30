package co.edu.usa.fincaapp.Ciclo3.repositorios;
import org.springframework.data.repository.CrudRepository;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

import co.edu.usa.fincaapp.Ciclo3.entidades.Reservations;



public interface ReservationsCrudRepository extends CrudRepository<Reservations,Long>{

    
    public List<Reservations> findAllByStatus(String status);
    
    public List<Reservations> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);
    
    // select clientId, count(*) as "total" from reservacion group by cliendId order by total desc;
    @Query ("SELECT c.client, COUNT(c.client) from Reservations AS c group by c.client order by COUNT(c.client)DESC")
    public List<Object[]> countTotalReservationsByClient();
    
}
