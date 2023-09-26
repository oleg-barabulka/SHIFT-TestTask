package ru.ctf.shiftLab.cherkasov.backend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ctf.shiftLab.cherkasov.backend.entities.DigitInterval;
@Repository
public interface DigitIntervalRepo extends CrudRepository<DigitInterval, Long> {
    @Query("SELECT d " +
            "FROM DigitInterval d " +
            "WHERE d.start = (" +
            "  SELECT MIN(d2.start)" +
            "  FROM DigitInterval d2" +
            "  WHERE d2.end = (" +
            "    SELECT MIN(d3.end)" +
            "    FROM DigitInterval d3" +
            "  )" +
            ")")
    DigitInterval findDigitIntervalWithMinEndValue();
}
