package com.techit.wiseapp.validator;

import com.techit.wiseapp.model.WiseModel;
import com.techit.wiseapp.repository.SayingRepository;

public class SayingValidate {

    private static final SayingRepository repository = SayingRepository.getInstance();

    public boolean findValidate(Long id) {
        WiseModel findWise = repository.findOne(id);
        if (findWise == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return false;
        }
        return true;
    }
}
