package ru.ctf.shiftLab.cherkasov.backend.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.ctf.shiftLab.cherkasov.backend.entities.DigitInterval;
import ru.ctf.shiftLab.cherkasov.backend.repositories.DigitIntervalRepo;

import java.util.List;


@AllArgsConstructor
@Getter
@Service
public class DigitIntervalService implements IntervalService<DigitInterval>{

    private final DigitIntervalRepo repo;


    @Override
    public void add(List<DigitInterval> intervals) {
        repo.saveAll(intervals);
    }

    @Override
    public DigitInterval getByMin() {
        return repo.findDigitIntervalWithMinEndValue();
    }
}
