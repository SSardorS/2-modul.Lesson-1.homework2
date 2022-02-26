package uz.pdp.homework2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionDto {
    private Long id;

    @NotNull(message = "name  bosh bolmaslifi kerak")
    private String name;

    @NotNull(message = "parentId  bosh bolmaslifi kerak")
    private Long parentId;
}
