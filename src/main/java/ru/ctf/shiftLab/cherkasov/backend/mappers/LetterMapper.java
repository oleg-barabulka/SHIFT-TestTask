package ru.ctf.shiftLab.cherkasov.backend.mappers;

import org.springframework.stereotype.Component;
import ru.ctf.shiftLab.cherkasov.backend.entities.LetterInterval;
import ru.ctf.shiftLab.cherkasov.backend.models.IntervalDTO;

@Component
public class LetterMapper implements IntervalMapper<LetterInterval, Character> {
    @Override
    public LetterInterval toEntity(IntervalDTO<Character> intervalDTO) {
        return new LetterInterval(intervalDTO.getStart(), intervalDTO.getEnd());
    }

    @Override
    public IntervalDTO<Character> toDTO(LetterInterval entity) {
        return new IntervalDTO<>(entity.getStart(), entity.getEnd());
    }
}
