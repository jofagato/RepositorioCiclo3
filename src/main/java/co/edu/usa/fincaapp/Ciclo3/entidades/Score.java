package co.edu.usa.fincaapp.Ciclo3.entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Score")
public class Score {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int Score;
    @Column
    private String message;

    @OneToOne
    @JsonIgnoreProperties("Score")
    private Reservations reservation;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public int getScore() {
        return Score;
    }


    public void setScore(int Scores) {
        Score = Scores;
    }


    public String getMensaje() {
        return message;
    }


    public void setMensaje(String messages) {
        this.message = messages;
    }




}