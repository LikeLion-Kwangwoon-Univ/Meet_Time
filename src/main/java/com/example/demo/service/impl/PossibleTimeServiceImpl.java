package com.example.demo.service.impl;

import com.example.demo.domain.PossibleTime;
import com.example.demo.repository.PossibleTimeRepository;
import com.example.demo.service.PossibleTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PossibleTimeServiceImpl implements PossibleTimeService {

    @Autowired
    private PossibleTimeRepository possibleTimeRepository;

    @Override
    public PossibleTime savePossibleTime(PossibleTime possibleTime) {
        return possibleTimeRepository.save(possibleTime);
    }

    @Override
    public PossibleTime updatePossibleTime(PossibleTime possibleTime) {
        return possibleTimeRepository.save(possibleTime);
    }

    @Override
    public void deletePossibleTime(Long id) {
        possibleTimeRepository.deleteById(id);
    }

    @Override
    public PossibleTime getPossibleTimeById(Long id) {
        return possibleTimeRepository.findById(id).orElse(null);
    }

    @Override
    public List<PossibleTime> getAllPossibleTimes() {
        return possibleTimeRepository.findAll();
    }
}
