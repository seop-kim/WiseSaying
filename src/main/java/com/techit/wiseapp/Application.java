package com.techit.wiseapp;

import com.techit.wiseapp.model.Saying;
import com.techit.wiseapp.repository.SayingRepository;
import com.techit.wiseapp.view.Input;
import com.techit.wiseapp.view.InputView;
import com.techit.wiseapp.view.Output;

public class Application {
    public static void main(String[] args) {
        SayingRepository repo = SayingRepository.getInstance();
        Input input = new Input();
        Output output = new Output();

        while (true) {
            System.out.print("명령) ");
            String request = InputView.read();

            if (request.equals("등록")) {
                Saying saying = input.addSaying(new Saying());
                repo.save(saying);
                output.printAddSaying(saying);
            }

            if (request.equals("목록")) {
                output.printSayings(repo.findAll());
            }

            if (request.equals("종료")) {
                break;
            }
        }
    }
}