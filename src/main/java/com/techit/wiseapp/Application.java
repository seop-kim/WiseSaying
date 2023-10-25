package com.techit.wiseapp;

import com.techit.wiseapp.file.FIleConverter;
import com.techit.wiseapp.json.JsonConverter;
import com.techit.wiseapp.model.WiseModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONArray;

public class Application {

    public static List<WiseModel> wiseArr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println("=== 명언 앱 ===");
        Scanner sc = new Scanner(System.in);
        Application app = new Application();
        FIleConverter fc = new FIleConverter();
        wiseArr = fc.load();

        while (true) {
            System.out.print("명령) ");
            String input = sc.nextLine();

            // add
            if (input.equals("등록")) {
                WiseModel wiseModel = new WiseModel();

                int postNum = app.getPostNum();
                wiseModel.setId(postNum);

                System.out.print("명언 : ");
                String addInput = sc.nextLine();
                wiseModel.setContent(addInput);

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
                    System.out.printf("%d \t\t %s \t\t %s\n", model.getId(), model.getAuthor(),
                            model.getContent());
                }
            }

            // delete
            if (input.contains("삭제?id=")) {
                String inputSplit = input.split("=")[1];
                int deleteNum = Integer.parseInt(inputSplit);
                int index = -1;

                for (int i = 0; i < wiseArr.size(); i++) {
                    if (wiseArr.get(i).getId() == deleteNum) {
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

            // update
            if (input.contains("수정?id=")) {
                String inputSplit = input.split("=")[1];
                int updateNum = Integer.parseInt(inputSplit);
                int index = -1;

                for (int i = 0; i < wiseArr.size(); i++) {
                    if (wiseArr.get(i).getId() == updateNum) {
                        index = i;
                        break;
                    }
                }

                // 수정 부 예외 : 존재하지 않는 명언 번호 입력 시
                if (index == -1) {
                    System.out.println(updateNum + "번 명언은 존재하지 않습니다.");
                    continue;
                }

                // 명언 수정 부
                System.out.println("명언(기존) : " + wiseArr.get(index).getContent());
                System.out.print("명언 : ");
                input = sc.nextLine();
                wiseArr.get(index).setContent(input);

                // 작가 수정 부
                System.out.println("작가(기존) : " + wiseArr.get(index).getAuthor());
                System.out.print("작가 : ");
                input = sc.nextLine();
                wiseArr.get(index).setAuthor(input);
            }

            // build
            if (input.equals("빌드")) {
                JsonConverter jc = new JsonConverter();
                JSONArray jsonArray = jc.javaArrToJsonArr(wiseArr);
                System.out.println("jsonArray = " + jsonArray.toJSONString());
            }

            // end
            if (input.equals("종료")) {
                fc.save(wiseArr);
                break;
            }
        }
    }

    public int getPostNum() {
        if (wiseArr.size() == 0) {
            return 1;
        }
        return wiseArr.get(wiseArr.size() - 1).getId() + 1;
    }

    public void testDataAdd() {
        WiseModel model = new WiseModel();
        model.setId(getPostNum());
        model.setAuthor("김태섭");
        model.setContent("김김자라김김자라");
        wiseArr.add(model);

        model = new WiseModel();
        model.setId(getPostNum());
        model.setAuthor("김태섭1");
        model.setContent("김김자라김김자라");
        wiseArr.add(model);

        model = new WiseModel();
        model.setId(getPostNum());
        model.setAuthor("김태섭2");
        model.setContent("김김자라김김자라");
        wiseArr.add(model);

        model = new WiseModel();
        model.setId(getPostNum());
        model.setAuthor("김태섭3");
        model.setContent("김김자라김김자라");
        wiseArr.add(model);

        model = new WiseModel();
        model.setId(getPostNum());
        model.setAuthor("김태섭4");
        model.setContent("김김자라김김자라");
        wiseArr.add(model);

        model = new WiseModel();
        model.setId(getPostNum());
        model.setAuthor("김태섭5");
        model.setContent("김김자라김김자라");
        wiseArr.add(model);
    }
}