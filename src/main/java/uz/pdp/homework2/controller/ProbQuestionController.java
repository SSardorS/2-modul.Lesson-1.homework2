package uz.pdp.homework2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.homework2.dto.ProbQuestionDto;
import uz.pdp.homework2.entity.ProbQuestion;
import uz.pdp.homework2.result.ResulBoleanAndText;
import uz.pdp.homework2.service.ProbQuestionServive;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/prob")
public class ProbQuestionController {
    @Autowired
    ProbQuestionServive probQuestionServive;

    @GetMapping()
    public List<ProbQuestion> getAll() {
        List<ProbQuestion> all = probQuestionServive.getAll();
        return all;
    }

    @GetMapping("/p{id}")
    public ProbQuestion getById(@PathVariable Long id) {
        ProbQuestion byId = probQuestionServive.getById(id);
        return byId;
    }

    @PostMapping()
    public HttpEntity<ResulBoleanAndText> post(@Valid @RequestBody ProbQuestionDto probQuestionDto){
        ResulBoleanAndText post = probQuestionServive.post(probQuestionDto);
        if (!post.isStatus()){
            return ResponseEntity.status(405).body(post);
        }
        return ResponseEntity.status(200).body(post);
    }

    @PutMapping("/{id}")
    public HttpEntity<ResulBoleanAndText> post(@PathVariable Long id, @Valid @RequestBody ProbQuestionDto probQuestionDto){
        ResulBoleanAndText put = probQuestionServive.put(id,probQuestionDto);
        if (!put.isStatus()){
            return ResponseEntity.status(405).body(put);
        }
        return ResponseEntity.status(200).body(put);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ResulBoleanAndText> delete(@PathVariable Long id){
        ResulBoleanAndText delete = probQuestionServive.delete(id);
        if (delete.isStatus()){
            return ResponseEntity.status(200).body(delete);
        }
        return ResponseEntity.status(405).body(delete);
    }




}
