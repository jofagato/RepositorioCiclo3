package co.edu.usa.fincaapp.Ciclo3.repositorios;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.usa.fincaapp.Ciclo3.entidades.*;
import co.edu.usa.fincaapp.Ciclo3.reportes.ContadorClientes;

@Repository
public class ReservationsRepository {

    @Autowired
    private ReservationsCrudRepository ReservationsRespository;

    public List<Reservations> getTodosReservations(){
        return (List<Reservations>) ReservationsRespository.findAll();
    }
    public Optional<Reservations> getReservationPorId(Long id){
        return ReservationsRespository.findById(id);
    }
    public Reservations guardar(Reservations Reservations){
        return ReservationsRespository.save(Reservations);
    }
    public void borrar(Reservations message){
        ReservationsRespository.delete(message);
    }
    public void borrarPorIdReservation(Long id){
        ReservationsRespository.deleteById(id);
        
    }

    public List<Reservations> ReservacionStatus (String status){
        return ReservationsRespository.findAllByStatus(status);
    }
    
    public List<Reservations> ReservacionTiempo (Date a, Date b){
        return ReservationsRespository.findAllByStartDateAfterAndStartDateBefore(a, b);
    }
  
    public List<ContadorClientes> getTopClientes(){
        List<ContadorClientes> res=new ArrayList<>();
        List<Object[]>report = ReservationsRespository.countTotalReservationsByClient();
        for(int i=0; i<report.size();i++){
            res.add(new ContadorClientes((Long)report.get(i)[1],(Client) report.get(i)[0]));
        
        }
        return res;
    }
    
}
