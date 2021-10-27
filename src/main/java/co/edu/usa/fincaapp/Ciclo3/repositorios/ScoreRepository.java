package co.edu.usa.fincaapp.Ciclo3.repositorios;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.usa.fincaapp.Ciclo3.entidades.*;



@Repository
public class ScoreRepository {
    
    @Autowired

    private ScoreCrudRepository scoreRespositorio;

    public List<Score> getTodosScores(){
        return (List<Score>) scoreRespositorio.findAll();
    }
    public Optional<Score> getScoreporId(Long id){
        return scoreRespositorio.findById(id);
    }
    public Score guardar(Score score){
        return scoreRespositorio.save(score);
    }
    public void borrar(Score score){
        scoreRespositorio.delete(score);
    }
    public void borrarPorIdScore(Long id){
        scoreRespositorio.deleteById(id);
        
    }

}
