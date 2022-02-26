package uz.pdp.homework2.entity.abstractClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public abstract class EntityHelper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


//    public EntityHelper(Integer id, String name) {
//        this.id = id;
//        this.name = name;
//    }
}
