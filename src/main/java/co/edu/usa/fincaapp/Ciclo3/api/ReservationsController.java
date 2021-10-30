package co.edu.usa.fincaapp.Ciclo3.api;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import co.edu.usa.fincaapp.Ciclo3.entidades.Reservations;
import co.edu.usa.fincaapp.Ciclo3.reportes.ContadorClientes;
import co.edu.usa.fincaapp.Ciclo3.reportes.StatusReservas;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import co.edu.usa.fincaapp.Ciclo3.servicios.ReservationServicio;


@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*")

public class ReservationsController {
    @Autowired private ReservationServicio ReservationsServicio;

    @GetMapping("/all")
    public List<Reservations> getTodosReservations(){
        return ReservationsServicio.getReservations();
    }

    @GetMapping("/{id}")
    public Optional<Reservations> getReservations(@PathVariable("id") Long id) {
        return ReservationsServicio.getReservationsporid(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservations update(@RequestBody Reservations Reservation) {
        return ReservationsServicio.update(Reservation);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservations guardar(@RequestBody Reservations Reservations){
        Reservations r = ReservationsServicio.guardarReservations(Reservations);
        return r;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Long id) {
        return ReservationsServicio.deleteReservation(id);
    }

    @GetMapping("/report-status")
    public StatusReservas getReservas(){
        return ReservationsServicio.getReporteStatusReservaciones();
    }
    
    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservations> getReservasTiempo (@PathVariable("dateOne")String dateOne, @PathVariable("dateTwo")String dateTwo){
        return ReservationsServicio.getReportesTiempoReservaciones(dateOne, dateTwo);
    }
    
    @GetMapping("/report-clients")
    public List<ContadorClientes> getClientes(){
        return ReservationsServicio.servicioTopClientes();
    
    }

}
