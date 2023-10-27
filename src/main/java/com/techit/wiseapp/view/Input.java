package com.techit.wiseapp.view;

import com.techit.wiseapp.model.Saying;

public class Input {
    public Saying addSaying(Saying saying) {
        System.out.print("명언 : ");
        saying.setContent(InputView.read());

        System.out.print("작가 : ");

        saying.setAuthor(InputView.read());
        return saying;
    }
}
