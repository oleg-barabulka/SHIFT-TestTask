package ru.ctf.shiftLab.cherkasov.backend.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.ctf.shiftLab.cherkasov.backend.entities.LetterInterval;
import ru.ctf.shiftLab.cherkasov.backend.repositories.LetterIntervalRepo;

import java.util.List;

@AllArgsConstructor
@Getter
@Service
public class LetterIntervalService implements IntervalService<LetterInterval> {
    private final LetterIntervalRepo repo;
    @Override
    public void add(List<LetterInterval> intervals) {
        repo.saveAll(intervals);
    }

    @Override
    public LetterInterval getByMin() {
        return repo.findLetterIntervalWithMinEndValue();
    }
}
