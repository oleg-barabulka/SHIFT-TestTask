package ru.ctf.shiftLab.cherkasov.backend.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ctf.shiftLab.cherkasov.backend.exception.MyParserException;
import ru.ctf.shiftLab.cherkasov.backend.models.IntervalDTO;

import java.util.ArrayList;
import java.util.List;

public class MyParser<T extends Comparable<T>>{
    public List<IntervalDTO<T>> parse(String jsonStr, TypeReference<List<List<T>>> typeReference) throws MyParserException {
        List<IntervalDTO<T>> listDTO = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        List<List<T>> listOfLists = null;
        try {
            listOfLists = objectMapper.readValue(jsonStr, typeReference);
        } catch (Exception e) {
            throw new MyParserException("Can not parse", e);

        }
        for (List<T> list : listOfLists) {
            listDTO.add(new IntervalDTO<>(list.get(0), list.get(1)));
        }
        return listDTO;
    }
}
