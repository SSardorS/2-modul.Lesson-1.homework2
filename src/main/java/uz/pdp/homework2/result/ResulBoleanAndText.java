package uz.pdp.homework2.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResulBoleanAndText {
    private boolean status;
    private String responseText;
}
