package uz.pdp.homework2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.homework2.dto.ProbAnswerDto;
import uz.pdp.homework2.dto.ProbQuestionDto;
import uz.pdp.homework2.entity.ProbAnswer;
import uz.pdp.homework2.entity.ProbQuestion;
import uz.pdp.homework2.entity.Section;
import uz.pdp.homework2.repository.ProbAnswerRepository;
import uz.pdp.homework2.repository.ProbQuestionReposiyoty;
import uz.pdp.homework2.repository.SectionRepository;
import uz.pdp.homework2.result.ResulBoleanAndText;

import java.util.List;
import java.util.Optional;

@Service
public class ProbAnswerService {
    @Autowired
    ProbAnswerRepository probAnswerRepository;

    @Autowired
    ProbQuestionReposiyoty probQuestionReposiyoty;

    public List<ProbAnswer> getAll(){
        List<ProbAnswer> all = probAnswerRepository.findAll();
        return all;
    }

    public ProbAnswer getById(Long id){
        ProbAnswer byId = probAnswerRepository.getById(id);
        return byId;
    }

    public ResulBoleanAndText post(ProbAnswerDto probAnswerDto){
        Optional<ProbQuestion> byId = probQuestionReposiyoty.findById(probAnswerDto.getQuestioId());
        if (byId.isPresent()){
            ProbQuestion probQuestion = byId.get();
            ProbAnswer probAnswer = new ProbAnswer(null, probAnswerDto.getName(), probAnswerDto.getAnswer(), probQuestion);
            probAnswerRepository.save(probAnswer);
            return new ResulBoleanAndText(true,"Added");
        }
        return new ResulBoleanAndText(false, "Error information");
    }

    public ResulBoleanAndText put(Long id, ProbAnswerDto answerDto){
        Optional<ProbAnswer> byIdAnswer = probAnswerRepository.findById(id);
        Optional<ProbQuestion> byIdQustion = probQuestionReposiyoty.findById(answerDto.getQuestioId());
        if (byIdQustion.isPresent() && byIdAnswer.isPresent()){
            ProbAnswer probAnswerEdit = byIdAnswer.get();
            ProbQuestion probQuestion = byIdQustion.get();
            probAnswerEdit.setName(answerDto.getName());
            probAnswerEdit.setAnswer(answerDto.getAnswer());
            probAnswerEdit.setQuestion(probQuestion);
            probAnswerRepository.save(probAnswerEdit);
            return new ResulBoleanAndText(true, "Edited");
        }
        return new ResulBoleanAndText(false, "Error");
    }


    public ResulBoleanAndText delete(Long id){
        Optional<ProbAnswer> byId = probAnswerRepository.findById(id);
        if (byId.isPresent()){
            probAnswerRepository.deleteById(id);
            probQuestionReposiyoty.deleteById(byId.get().getQuestion().getId());
            return new ResulBoleanAndText(true, "Deleted");
        }
        return new ResulBoleanAndText(false, "this id is not found");
    }
}
