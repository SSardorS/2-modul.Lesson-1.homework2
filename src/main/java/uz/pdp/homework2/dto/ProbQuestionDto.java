package uz.pdp.homework2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProbQuestionDto {
    private Long id;

    @NotNull(message = "name  bosh bolmaslifi kerak")
    private String name;

    @NotNull(message = "question  bosh bolmaslifi kerak")
    private String question;


    @NotNull(message = "sectionId  bosh bolmaslifi kerak")
    private Long sectionId;
}
