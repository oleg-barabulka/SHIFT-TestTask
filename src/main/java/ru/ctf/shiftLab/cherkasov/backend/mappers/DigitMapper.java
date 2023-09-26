package ru.ctf.shiftLab.cherkasov.backend.mappers;

import org.springframework.stereotype.Component;
import ru.ctf.shiftLab.cherkasov.backend.entities.DigitInterval;
import ru.ctf.shiftLab.cherkasov.backend.models.IntervalDTO;

@Component
public class DigitMapper implements IntervalMapper<DigitInterval, Integer>{
    @Override
    public DigitInterval toEntity(IntervalDTO<Integer> intervalDTO) {
        return new DigitInterval(intervalDTO.getStart(), intervalDTO.getEnd());
    }

    @Override
    public IntervalDTO<Integer> toDTO(DigitInterval entity) {
        return new IntervalDTO<>(entity.getStart(), entity.getEnd());
    }
}
