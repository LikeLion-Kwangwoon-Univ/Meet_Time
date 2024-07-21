package com.example.demo.service;

import com.example.demo.domain.PossibleTime;
import java.util.List;

public interface PossibleTimeService {
    PossibleTime savePossibleTime(PossibleTime possibleTime);
    //새로운 가능한 시간을 저장
    PossibleTime updatePossibleTime(PossibleTime possibleTime);
    //기존 가능한 시간 정보를 업데이트
    void deletePossibleTime(Long id);
    //특정 가능한 시간을 삭제
    PossibleTime getPossibleTimeById(Long id);
    //특정 ID를 가진 가능한 시간을 조회
    List<PossibleTime> getAllPossibleTimes();
    //모든 가능한 시간을 조회
}
