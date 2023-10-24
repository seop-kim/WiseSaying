package com.techit.wiseapp;

import com.techit.wiseapp.model.WiseModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<WiseModel> wiseArr = new ArrayList<>();

        while (true) {
            System.out.print("명령) ");
            String input = sc.nextLine();

            if (input.equals("등록")) {
                WiseModel wiseModel = new WiseModel();

                System.out.print("명언 : ");
                String addInput = sc.nextLine();
                wiseModel.setSentence(addInput);

                System.out.print("작가 : ");
                addInput = sc.nextLine();
                wiseModel.setAuthor(addInput);

                wiseArr.add(wiseModel);
            }

            if (input.equals("종료")) {
                break;
            }
        }
    }
}