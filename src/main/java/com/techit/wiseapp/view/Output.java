package com.techit.wiseapp.view;

import com.techit.wiseapp.model.Saying;

public class Output {
    public void printAddSaying(Saying saying) {
        System.out.println(saying.getId() + "번 명언이 저장되었습니다.");
    }
}
