package co.edu.usa.fincaapp.Ciclo3.servicios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.usa.fincaapp.Ciclo3.entidades.Reservations;
import co.edu.usa.fincaapp.Ciclo3.reportes.ContadorClientes;
import co.edu.usa.fincaapp.Ciclo3.reportes.StatusReservas;
import co.edu.usa.fincaapp.Ciclo3.repositorios.ReservationsRepository;


@Service
public class ReservationServicio {
    @Autowired

    private ReservationsRepository ReservationsRepository;
    
    public List<Reservations> getReservations(){
        return ReservationsRepository.getTodosReservations();
    }


    public Optional<Reservations> getReservationsporid(Long id) {
        return ReservationsRepository.getReservationPorId(id);
    }
    

    public Reservations guardarReservations(Reservations Reservations){
        if(Reservations.getidReservation()==null){
            return ReservationsRepository.guardar(Reservations);
        }else{
            Optional<Reservations> e=ReservationsRepository.getReservationPorId(Reservations.getidReservation());
            if(e.isEmpty()){
                return ReservationsRepository.guardar(Reservations);
            }else{
                return Reservations;
            }

        }
    }
            public Reservations update(Reservations Reservation){
                if(Reservation.getidReservation()!=null){
                    Optional<Reservations>g=ReservationsRepository.getReservationPorId(Reservation.getidReservation());
                    if(!g.isEmpty()){
                        if(Reservation.getStartDate()!=null){
                            g.get().setStartDate(Reservation.getStartDate());
                        }
                        if(Reservation.getDevolutionDate()!=null){
                            g.get().setDevolutionDate(Reservation.getDevolutionDate());
                        }
                        if (Reservation.getStatus()!=null){
                            g.get().setStatus(Reservation.getStatus());
        
                        }
        

                        return ReservationsRepository.guardar(g.get());
                    }
                }
                      return Reservation;
            }


    

 public boolean deleteReservation(Long id) {
        Boolean aBoolean = getReservationsporid(id).map(idReservation -> {
           ReservationsRepository.borrar(idReservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }


    public StatusReservas getReporteStatusReservaciones(){
        List<Reservations>completed= ReservationsRepository.ReservacionStatus("completed");
        List<Reservations>cancelled= ReservationsRepository.ReservacionStatus("cancelled");
        return new StatusReservas(completed.size(), cancelled.size());
    }
    
    public List<Reservations> getReportesTiempoReservaciones(String datoA, String datoB){
        SimpleDateFormat parser=new SimpleDateFormat ("yyyy-MM-dd");
        Date datoUno = new Date();
        Date datoDos = new Date();
        
        try{
            datoUno = parser.parse(datoA);
            datoDos = parser.parse(datoB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }if(datoUno.before(datoDos)){
            return ReservationsRepository.ReservacionTiempo(datoUno, datoDos);
        }else{
            return new ArrayList<>();
        }
    }  
    
    public List<ContadorClientes> servicioTopClientes(){
        return ReservationsRepository.getTopClientes();
    }
    
}



