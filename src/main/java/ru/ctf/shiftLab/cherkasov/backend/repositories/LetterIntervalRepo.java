package ru.ctf.shiftLab.cherkasov.backend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ctf.shiftLab.cherkasov.backend.entities.LetterInterval;
@Repository
public interface LetterIntervalRepo extends CrudRepository<LetterInterval, Long> {
    @Query("SELECT d\n" +
            "FROM LetterInterval d " +
            "WHERE d.start = (" +
            "  SELECT MIN(d2.start)" +
            "  FROM LetterInterval d2" +
            "  WHERE d2.end = (" +
            "    SELECT MIN(d3.end)" +
            "    FROM LetterInterval d3" +
            "  )" +
            ")")
    LetterInterval findLetterIntervalWithMinEndValue();
}
