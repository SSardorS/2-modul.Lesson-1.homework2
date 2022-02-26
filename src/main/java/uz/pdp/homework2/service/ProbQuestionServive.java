package uz.pdp.homework2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.homework2.dto.ProbQuestionDto;
import uz.pdp.homework2.entity.ProbQuestion;
import uz.pdp.homework2.entity.Section;
import uz.pdp.homework2.repository.ProbQuestionReposiyoty;
import uz.pdp.homework2.repository.SectionRepository;
import uz.pdp.homework2.result.ResulBoleanAndText;

import java.util.List;
import java.util.Optional;

@Service
public class ProbQuestionServive {
    @Autowired
    ProbQuestionReposiyoty probQuestionReposiyoty;

    @Autowired
    SectionRepository sectionRepository;

    public List<ProbQuestion> getAll(){
        List<ProbQuestion> all = probQuestionReposiyoty.findAll();
        return all;
    }

    public ProbQuestion getById(Long id){
        ProbQuestion byId = probQuestionReposiyoty.getById(id);
        return byId;
    }

    public ResulBoleanAndText post(ProbQuestionDto probQuestionDto){
        Optional<Section> byId = sectionRepository.findById(probQuestionDto.getSectionId());
        if (byId.isPresent()){
            Section section = byId.get();
            ProbQuestion probQuestion = new ProbQuestion(null, probQuestionDto.getName(), probQuestionDto.getQuestion(), section);
            probQuestionReposiyoty.save(probQuestion);
            return new ResulBoleanAndText(true,"Added");
        }
        return new ResulBoleanAndText(false, "Error information");
    }

    public ResulBoleanAndText put(Long id, ProbQuestionDto probQuestionDto){
        Optional<Section> byIdSection = sectionRepository.findById(probQuestionDto.getSectionId());
        Optional<ProbQuestion> byIdQustion = probQuestionReposiyoty.findById(id);
        if (byIdQustion.isPresent() && byIdSection.isPresent()){
            ProbQuestion probQuestionEdit = byIdQustion.get();
            Section section = byIdSection.get();
            probQuestionEdit.setName(probQuestionDto.getName());
            probQuestionEdit.setQuestion(probQuestionDto.getQuestion());
            probQuestionEdit.setSection(section);
            probQuestionReposiyoty.save(probQuestionEdit);
            return new ResulBoleanAndText(true, "Edited");
        }
        return new ResulBoleanAndText(false, "Error");
    }


    public ResulBoleanAndText delete(Long id){
        Optional<ProbQuestion> byId = probQuestionReposiyoty.findById(id);
        if (byId.isPresent()){
            probQuestionReposiyoty.deleteById(id);
            sectionRepository.deleteById(byId.get().getSection().getId());
            return new ResulBoleanAndText(true, "Deleted");
        }
        return new ResulBoleanAndText(false, "this id is not found");
    }



}
