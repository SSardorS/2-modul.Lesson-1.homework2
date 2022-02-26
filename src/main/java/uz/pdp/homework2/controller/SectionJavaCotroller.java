package uz.pdp.homework2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import uz.pdp.homework2.dto.SectionDto;
import uz.pdp.homework2.entity.Section;
import uz.pdp.homework2.result.ResulBoleanAndText;
import uz.pdp.homework2.service.SectionService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/java")
public class SectionJavaCotroller {
    @Autowired
    SectionService sectionService;

    @GetMapping
    public List<Section> getAll() {
        List<Section> all = sectionService.getAll();
        return all;
    }

    @GetMapping("/warmp-{id}")
    public Section getById(@PathVariable Long id) {
        return sectionService.getById(id);
    }

    @PostMapping()
    public HttpEntity<ResulBoleanAndText> post(@Valid @RequestBody SectionDto sectionDto) {
        ResulBoleanAndText post = sectionService.post(sectionDto);
        if (post.isStatus()) {
            return ResponseEntity.status(200).body(post);
        }
        return ResponseEntity.status(405).body(post);
    }

    @PutMapping("/warmp-{id}")
    public HttpEntity<ResulBoleanAndText> put(@PathVariable Long id, @Valid @RequestBody SectionDto sectionDto) {
        ResulBoleanAndText put = sectionService.put(id, sectionDto);
        if (put.isStatus()){
            return ResponseEntity.status(200).body(put);
        }
        return ResponseEntity.status(405).body(put);
    }




    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
