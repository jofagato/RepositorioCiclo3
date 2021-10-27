package co.edu.usa.fincaapp.Ciclo3.repositorios;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.usa.fincaapp.Ciclo3.entidades.*;

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
    
}
