package ru.ctf.shiftLab.cherkasov.backend.services;

import java.util.List;

public interface IntervalService<T> {
    void add(List<T> intervals);
    T getByMin();
}
