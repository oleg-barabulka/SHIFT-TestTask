package ru.ctf.shiftLab.cherkasov.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@AllArgsConstructor
@Setter
public class IntervalDTO<T extends Comparable<T>> {

    @NotEmpty
    private T start;
    @NotEmpty
    private T end;

    public IntervalDTO() {

    }
}
