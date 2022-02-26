package uz.pdp.homework2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.homework2.entity.abstractClass.EntityHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ProbQuestion extends EntityHelper {
    @Column(nullable = false)
    private String question;

    @ManyToOne
    private Section section;

    public ProbQuestion(Long id, String name, String question, Section section) {
        super(id, name);
        this.question = question;
        this.section = section;
    }
}
