package ru.ctf.shiftLab.cherkasov.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="digit_interval")
@Getter
@Setter
public class DigitInterval {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="start")
    private Integer start;

    @Column(name="end")
    private Integer end;

    public DigitInterval(Integer start, Integer end){
        this.start = start;
        this.end = end;
    }

    public DigitInterval() {

    }
}
