package com.techit.wiseapp;

import com.techit.wiseapp.model.WiseModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static List<WiseModel> wiseArr = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Application app = new Application();

        while (true) {
            System.out.print("명령) ");
            String input = sc.nextLine();

            if (input.equals("등록")) {
                WiseModel wiseModel = new WiseModel();

                int postNum = app.getPostNum();
                wiseModel.setPostNum(postNum);

                System.out.print("명언 : ");
                String addInput = sc.nextLine();
                wiseModel.setSentence(addInput);

                System.out.print("작가 : ");
                addInput = sc.nextLine();
                wiseModel.setAuthor(addInput);

                wiseArr.add(wiseModel);

                System.out.println(postNum + "번 명언이 등록되었습니다.");
            }

            if (input.equals("종료")) {
                break;
            }
        }
    }

    public int getPostNum() {
        if (wiseArr.size() == 0) {
            return 1;
        }
        return wiseArr.get(wiseArr.size() - 1).getPostNum() + 1;
    }
}