package ru.ctf.shiftLab.cherkasov.backend.entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="letter_interval")
@Getter
@Setter
public class LetterInterval{
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="start")
    private Character start;

    @Column(name="end")
    private Character end;

    public LetterInterval(Character start, Character end){
        this.start = start;
        this.end = end;
    }

    public LetterInterval() {

    }
}
