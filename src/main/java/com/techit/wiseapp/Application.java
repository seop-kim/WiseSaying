package com.techit.wiseapp;

import com.techit.wiseapp.function.saying.Function;
import com.techit.wiseapp.input.Input;
import java.util.Scanner;

public class Application {
    private Function function = new Function();

    public void run() {
        System.out.println("=== 명언 앱 ===");

        while (true) {
            System.out.print("명령) ");
            String input = Input.read();
            // register
            if (input.equals("등록")) {
                function.register();
            }

            // list
            if (input.equals("목록")) {
                function.list();
            }

            // delete
            if (input.startsWith("삭제?id=")) {
                function.delete();
            }

            // update
            if (input.startsWith("수정?id=")) {
                function.update();
            }

            // build
            if (input.equals("빌드")) {
                function.build();
            }

            // end
            if (input.equals("종료")) {
                function.save();
                break;
            }
        }
    }
}