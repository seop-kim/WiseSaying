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

            // add
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

            // list
            if (input.equals("목록")) {
                System.out.println("번호\t\t작가\t\t명언");
                System.out.println("---------------------------");

                for (WiseModel model : wiseArr) {
                    System.out.printf("%d \t\t %s \t\t %s\n", model.getPostNum(), model.getAuthor(),
                            model.getSentence());
                }
            }

            // delete
            if (input.contains("삭제?id=")) {
                String inputSplit = input.split("=")[1];
                int deleteNum = Integer.parseInt(inputSplit);
                int index = -1;

                for (int i = 0; i < wiseArr.size(); i++) {
                    if (wiseArr.get(i).getPostNum() == deleteNum) {
                        index = i;
                        wiseArr.remove(index);
                        System.out.println(deleteNum + "번 명언이 삭제되었습니다.");
                        break;
                    }
                }
                if (index == -1) {
                    System.out.println(deleteNum + "번 명언은 존재하지 않습니다.");
                }
            }

            // end
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