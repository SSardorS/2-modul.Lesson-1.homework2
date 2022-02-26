package uz.pdp.homework2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.homework2.entity.abstractClass.EntityHelper;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Section extends EntityHelper {

    @ManyToOne
    private Section parent;

    public Section(Long id, String name, Section parent) {
        super(id, name);
        this.parent = parent;
    }


}
