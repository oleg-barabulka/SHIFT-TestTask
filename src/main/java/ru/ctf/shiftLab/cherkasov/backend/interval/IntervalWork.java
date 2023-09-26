package ru.ctf.shiftLab.cherkasov.backend.interval;

import ru.ctf.shiftLab.cherkasov.backend.models.IntervalDTO;

import java.util.List;

public class IntervalWork {
    public static <T extends Comparable<T>> void checkIncluding(List<IntervalDTO<T>> intervalDTO) {
        boolean checkingIncluding = false;
        boolean intervalRemoved = false;
        boolean incorrectInterval = false;
        while (!checkingIncluding) {
            for (IntervalDTO current : intervalDTO) {
                for (IntervalDTO checking : intervalDTO) {
                    if (current.getStart().compareTo(current.getEnd()) > 0){
                        intervalDTO.remove(current);
                        incorrectInterval = true;
                        break;
                    }
                    if (checking == current) {
                        continue;
                    }
                    if ((current.getStart().compareTo(checking.getStart()) >= 0) && (current.getStart().compareTo(checking.getEnd()) <= 0)) {
                        if (current.getEnd().compareTo(checking.getEnd()) < 0) {
                            current.setEnd(checking.getEnd());
                        }
                        current.setStart(checking.getStart());
                        intervalDTO.remove(checking);
                        intervalRemoved = true;
                        break;
                    }
                    if ((checking.getStart().compareTo(current.getStart()) >= 0) && (checking.getStart().compareTo(current.getEnd()) <= 0)) {
                        if (checking.getEnd().compareTo(current.getEnd()) > 0) {
                            current.setEnd(checking.getEnd());
                        }
                        intervalDTO.remove(checking);
                        intervalRemoved = true;
                        break;
                    }
                }
                if (intervalRemoved || incorrectInterval){
                    break;
                }
            }
            if (intervalRemoved){
                intervalRemoved = false;
                continue;
            }else if (incorrectInterval){
                incorrectInterval = false;
                continue;
            }
            checkingIncluding =true;
        }

    }
}
