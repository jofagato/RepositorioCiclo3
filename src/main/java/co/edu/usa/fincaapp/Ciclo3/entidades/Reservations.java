package co.edu.usa.fincaapp.Ciclo3.entidades;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="Reservations")
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long idReservation;
     @Column(name="startDate")
     private Date startDate;
     @Column(name="devolutionDate")
     private Date devolutionDate;
     @Column(name="status")
     private String status="created";

     @ManyToOne
     @JoinColumn(name="Farm_id")
     @JsonIgnoreProperties({"reservations","farm","message","client"})
     private Farm farm;
 
     @ManyToOne
     @JoinColumn(name="idClient")
     @JsonIgnoreProperties({"reservations","client","messages"})
     private Client client;

    @OneToOne(cascade = {CascadeType.REMOVE},mappedBy="reservation")
    @JsonIgnoreProperties("Reservations")
    private Score Score;

    public Long getidReservation() {
        return idReservation;
    }

    public void setidReservation(Long id) {
        this.idReservation = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDevolutionDate() {
        return devolutionDate;
    }
    
    public void setDevolutionDate(Date devolutionDate) {
        this.devolutionDate = devolutionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        status = Status;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farms) {
        farm = farms;
    }

    public Score getScore() {
        return Score;
    }

    public void setScore(Score Score) {
        this.Score= Score;
    }

    public Client getclient() {
        return client;
    }

    public void setclient(Client clients) {
        this.client = clients;
    }
    
     

     
     
}
