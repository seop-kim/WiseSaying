package com.techit.wiseapp;

import com.techit.wiseapp.file.FIleConverter;
import com.techit.wiseapp.json.JsonConverter;
import com.techit.wiseapp.model.WiseModel;
import com.techit.wiseapp.repository.SayingRepository;
import com.techit.wiseapp.validator.SayingValidate;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONArray;

public class Application {


    public static void main(String[] args) throws IOException {
        System.out.println("=== 명언 앱 ===");
        Scanner sc = new Scanner(System.in);
        Application app = new Application();
        FIleConverter fc = new FIleConverter();
        SayingRepository repo = SayingRepository.getInstance();
        SayingValidate validate = new SayingValidate();

        while (true) {
            System.out.print("명령) ");
            String input = sc.nextLine();

            // add
            if (input.equals("등록")) {
                WiseModel wiseModel = new WiseModel();

                System.out.print("명언 : ");
                String addInput = sc.nextLine();
                wiseModel.setContent(addInput);

                System.out.print("작가 : ");
                addInput = sc.nextLine();
                wiseModel.setAuthor(addInput);

                WiseModel saying = repo.save(wiseModel);

                System.out.println(saying.getId() + "번 명언이 등록되었습니다.");
            }

            // list
            if (input.equals("목록")) {
                System.out.println("번호\t\t작가\t\t명언");
                System.out.println("---------------------------");

                for (WiseModel model :  repo.findAll()) {
                    System.out.printf("%d \t\t %s \t\t %s\n", model.getId(), model.getAuthor(),
                            model.getContent());
                }
            }

            // delete
            if (input.contains("삭제?id=")) {
                String inputSplit = input.split("=")[1];
                Long id = Long.parseLong(inputSplit);

                if (validate.findValidate(id)) {
                    repo.delete(id);
                    System.out.println(id + "번 명언이 삭제되었습니다.");
                }
            }

            // update
            if (input.contains("수정?id=")) {
                String inputSplit = input.split("=")[1];
                Long id = Long.parseLong(inputSplit);

                if (!validate.findValidate(id)) {
                    continue;
                }

                WiseModel findOne = repo.findOne(id);
                // 명언 수정 부
                System.out.println("명언(기존) : " + findOne.getContent());
                System.out.print("명언 : ");
                input = sc.nextLine();
                findOne.setContent(input);

                // 작가 수정 부
                System.out.println("작가(기존) : " + findOne.getAuthor());
                System.out.print("작가 : ");
                input = sc.nextLine();
                findOne.setAuthor(input);
            }

            // build
            if (input.equals("빌드")) {
                JsonConverter jc = new JsonConverter();
                JSONArray jsonArray = jc.javaArrToJsonArr(repo.findAll());
                System.out.println("jsonArray = " + jsonArray.toJSONString());
            }

            // end
            if (input.equals("종료")) {
                fc.save(repo.findAll());
                break;
            }
        }

    }
}