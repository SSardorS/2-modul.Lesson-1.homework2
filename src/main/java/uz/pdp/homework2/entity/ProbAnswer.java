package uz.pdp.homework2.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.homework2.entity.abstractClass.EntityHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ProbAnswer extends EntityHelper {

    @Column(nullable = false)
    private String answer;

    @OneToOne
    private ProbQuestion question;

    public ProbAnswer(Long id, String name, String answer, ProbQuestion question) {
        super(id, name);
        this.answer = answer;
        this.question = question;
    }
}
