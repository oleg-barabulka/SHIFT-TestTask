package ru.ctf.shiftLab.cherkasov.backend.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ctf.shiftLab.cherkasov.backend.entities.DigitInterval;
import ru.ctf.shiftLab.cherkasov.backend.entities.LetterInterval;
import ru.ctf.shiftLab.cherkasov.backend.exception.MyParserException;
import ru.ctf.shiftLab.cherkasov.backend.interval.IntervalWork;
import ru.ctf.shiftLab.cherkasov.backend.mappers.DigitMapper;
import ru.ctf.shiftLab.cherkasov.backend.mappers.LetterMapper;
import ru.ctf.shiftLab.cherkasov.backend.models.IntervalDTO;
import ru.ctf.shiftLab.cherkasov.backend.parser.MyParser;
import ru.ctf.shiftLab.cherkasov.backend.services.DigitIntervalService;
import ru.ctf.shiftLab.cherkasov.backend.services.LetterIntervalService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/intervals")
public class IntervalController {

    private final DigitIntervalService digitIntervalService;
    private final LetterIntervalService letterIntervalService;
    private final DigitMapper digitMapper;
    private final LetterMapper letterMapper;

    @GetMapping("/min")
    public Object getInterval(@RequestParam("kind") String kind){
        if ("digits".equals(kind)) {
            if (digitIntervalService.getByMin() != null) {
                IntervalDTO<Integer> dto = digitMapper.toDTO(digitIntervalService.getByMin());
                return ResponseEntity.ok(new int[]{dto.getStart(), dto.getEnd()});
            }else {
                return ResponseEntity.badRequest().body("No digit intervals");
            }
        } else if ("letters".equals(kind)){
            if (letterIntervalService.getByMin() != null) {
                IntervalDTO<Character> dto = letterMapper.toDTO(letterIntervalService.getByMin());
                return ResponseEntity.ok(new Character[]{dto.getStart(), dto.getEnd()});
            }
            else{
                return ResponseEntity.badRequest().body("No letter intervals");
            }
        }
        return ResponseEntity.badRequest().body("Bad param");
    }

    @PostMapping("/merge")
    public Object postInterval(@RequestParam("kind") String kind, @RequestBody String str){
        if ("digits".equals(kind)){
            TypeReference<List<List<Integer>>> typeReference = new TypeReference<>() {};
            List<IntervalDTO<Integer>> dtoDigitList;
            try {
                dtoDigitList = new MyParser<Integer>().parse(str, typeReference);
            } catch (MyParserException ex) {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
            IntervalWork.checkIncluding(dtoDigitList);
            List<DigitInterval> digitIntervalList = dtoDigitList.stream().map(digitMapper::toEntity).toList();
            digitIntervalService.add(digitIntervalList);
            return ResponseEntity.ok().build();
        } else if ("letters".equals(kind)) {
            TypeReference<List<List<Character>>> typeReference = new TypeReference<>() {};
            List<IntervalDTO<Character>> dtoLetterList;
            try {
                dtoLetterList = new MyParser<Character>().parse(str, typeReference);
            } catch (MyParserException ex){
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
            IntervalWork.checkIncluding(dtoLetterList);
            List<LetterInterval> letterIntervalList = dtoLetterList.stream().map(letterMapper::toEntity).toList();
            letterIntervalService.add(letterIntervalList);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Bad param");
    }
}
