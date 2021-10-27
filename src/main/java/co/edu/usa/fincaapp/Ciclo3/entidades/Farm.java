package co.edu.usa.fincaapp.Ciclo3.entidades;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Farm")
public class Farm implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="address")
    private String address;
    @Column(name="exension")
    private int extension;
    
    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="categoryId")
    @JsonIgnoreProperties("farms")
    public Category category;


    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "farm")
    @JsonIgnoreProperties({"farm","messages","reservations","client"})
    private List<Message> messages;

    @OneToMany(cascade={CascadeType.PERSIST},mappedBy="farm")
    @JsonIgnoreProperties({"farm","reservations"})
    private List<Reservations> reservations;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public int getExtension() {
        return extension;
    }


    public void setExtension(int extension) {
        this.extension = extension;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }   


    public List<Message> getmessages() {
        return messages;
    }


    public void setMessages(List<Message> Messages) {
        this.messages = Messages;
    }


    public List<Reservations> getReservations() {
        return reservations;
    }


    public void setReservations(List<Reservations> Reservation) {
        reservations = Reservation;
    }
    
   
  


}
