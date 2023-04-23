package dev.coffeecult.Names.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
/*
* The "Name" to be persisted. Used "firstName" field to avoid confusion
* and because is a proper representation of the value persisted.
* */
public class Name {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String firstName;

    public Name(String firstName){
        this.firstName = firstName;
    }

}
