package uz.pdp.homework2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProbAnswerDto {
    private Long id;

    @NotNull(message = "name  bosh bolmaslifi kerak")
    private String name;

    @NotNull(message = "answer  bosh bolmaslifi kerak")
    private String answer;


    @NotNull(message = "questioId  bosh bolmaslifi kerak")
    private Long questioId;
}
