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
import co.edu.usa.fincaapp.Ciclo3.entidades.Score;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import co.edu.usa.fincaapp.Ciclo3.servicios.ScoreServicio;


@RestController
@RequestMapping("/api/Score")
@CrossOrigin(origins = "*")

public class ScoreController {
    @Autowired private ScoreServicio ScoreServicio;

    @GetMapping("/all")
    public List<Score> getTodosScores(){
        return ScoreServicio.getScores();
    }

    @GetMapping("/{id}")
    public Optional<Score> getScores(@PathVariable("id") Long id) {
        return ScoreServicio.getScoreporId(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Score update(@RequestBody Score score) {
        return ScoreServicio.update(score);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Score guardar(@RequestBody Score score){
        Score r = ScoreServicio.guardarScore(score);
        return r;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Long id) {
        return ScoreServicio.deleteScore(id);
    }


}
