package co.edu.usa.fincaapp.Ciclo3.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="Message")
public class Message {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMessage;
    @Column(name="messagetext")
    private String messageText;
 

    @ManyToOne
    @JoinColumn(name="idFarm")
    @JsonIgnoreProperties({"farm","messages","reservations","client"})
    public Farm farm;

  
    @ManyToOne
    @JoinColumn(name="idClient")
    @JsonIgnoreProperties({"messages","reservations","client"})
    private Client client;

    public Long getidMessage() {
        return idMessage;
    }

    public void setidMessage(Long id) {
        this.idMessage = id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messagetext) {
        this.messageText = messagetext;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farms) {
        farm= farms;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client Client) {
        client = Client;
    }

    


}
