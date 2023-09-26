package ru.ctf.shiftLab.cherkasov.backend.mappers;

import ru.ctf.shiftLab.cherkasov.backend.models.IntervalDTO;

public interface IntervalMapper<T, E extends Comparable<E>> {
    T toEntity(IntervalDTO<E> intervalDTO);
    IntervalDTO<E> toDTO(T entity);
}
