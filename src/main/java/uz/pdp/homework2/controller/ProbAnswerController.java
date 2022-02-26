package uz.pdp.homework2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.homework2.dto.ProbAnswerDto;
import uz.pdp.homework2.dto.ProbQuestionDto;
import uz.pdp.homework2.entity.ProbAnswer;
import uz.pdp.homework2.entity.ProbQuestion;
import uz.pdp.homework2.repository.ProbAnswerRepository;
import uz.pdp.homework2.result.ResulBoleanAndText;
import uz.pdp.homework2.service.ProbAnswerService;
import uz.pdp.homework2.service.ProbQuestionServive;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/probAnswer")
public class ProbAnswerController {

    @Autowired
    ProbAnswerService probAnswerR;

    @GetMapping()
    public List<ProbAnswer> getAll() {
        List<ProbAnswer> all = probAnswerR.getAll();
        return all;
    }

    @GetMapping("/p{id}")
    public ProbAnswer getById(@PathVariable Long id) {
        ProbAnswer byId = probAnswerR.getById(id);
        return byId;
    }

    @PostMapping()
    public HttpEntity<ResulBoleanAndText> post(@Valid @RequestBody ProbAnswerDto probAnswerDto){
        ResulBoleanAndText post = probAnswerR.post(probAnswerDto);
        if (!post.isStatus()){
            return ResponseEntity.status(405).body(post);
        }
        return ResponseEntity.status(200).body(post);
    }

    @PutMapping("/{id}")
    public HttpEntity<ResulBoleanAndText> post(@PathVariable Long id, @Valid @RequestBody ProbAnswerDto probAnswerDto){
        ResulBoleanAndText put = probAnswerR.put(id,probAnswerDto);
        if (!put.isStatus()){
            return ResponseEntity.status(405).body(put);
        }
        return ResponseEntity.status(200).body(put);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ResulBoleanAndText> delete(@PathVariable Long id){
        ResulBoleanAndText delete = probAnswerR.delete(id);
        if (delete.isStatus()){
            return ResponseEntity.status(200).body(delete);
        }
        return ResponseEntity.status(405).body(delete);
    }
}
