package co.edu.usa.fincaapp.Ciclo3.servicios;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.usa.fincaapp.Ciclo3.repositorios.ScoreRepository;
import co.edu.usa.fincaapp.Ciclo3.entidades.Score;



@Service
public class ScoreServicio {
    @Autowired

    private ScoreRepository ScoreRepository;

    public List<Score> getScores(){
        return ScoreRepository.getTodosScores();
    }
 
    public Optional<Score> getScoreporId(Long id) {
        return ScoreRepository.getScoreporId(id);
    }



    public Score guardarScore(Score score){
        if(score.getId()==null){
            return ScoreRepository.guardar(score);
        }else{
            Optional<Score> s=ScoreRepository.getScoreporId(score.getId());
            if(s.isEmpty()){
                return ScoreRepository.guardar(score);
            }else{
                return score;
            }

        }
    }

    public Score update(Score score){
        if(score.getId()!=null){
            Optional<Score>g=ScoreRepository.getScoreporId(score.getId());
            if(!g.isEmpty()){
                if(score.getScore()!=0){
                    g.get().setScore(score.getScore());
                }
                if(score.getMensaje()!=null){
                    g.get().setMensaje(score.getMensaje());
                }
                
                return ScoreRepository.guardar(g.get());
            }
        }
              return score;
    }


    public boolean deleteScore(Long id) {
        Boolean aBoolean = getScoreporId(id).map(idScore -> {
           ScoreRepository.borrar(idScore);
            return true;
        }).orElse(false);
        return aBoolean;
    }

}
