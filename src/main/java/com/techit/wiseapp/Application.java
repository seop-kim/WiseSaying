package com.techit.wiseapp;

import com.techit.wiseapp.model.WiseModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Application {

    public static List<WiseModel> wiseArr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println("=== 명언 앱 ===");
        Scanner sc = new Scanner(System.in);
        Application app = new Application();
        app.load();

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
                app.jsonConvert();
            }

            // end
            if (input.equals("종료")) {
                app.save();
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

    // 데이터 세이브
    public void save() {
        // 저장할 경로를 선택해야한다. 추후에 시스템 폴더로 지정하여 누구든지 쓸 수 있게 변경 예정
        String saveFilePath = "/Users/gimtaeseob/workspace/saveWiseData.txt";

        try {
            // 파일 생성 및 텍스트 작성을 위한 버퍼드 객체 생성
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveFilePath));

            // 반복문을 통해 wiseArr에 들어있는 데이터를 txt 파일에 순차적 작성
            for (WiseModel wiseModel : wiseArr) {
                String objectText =
                        String.format("%d,%s,%s", wiseModel.getId(), wiseModel.getAuthor(),
                                wiseModel.getContent());
                writer.write(objectText);
                writer.newLine();
            }

            // 버퍼드 객체 close
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 데이터 로드
    public void load() {
        String userHome = System.getProperty("user.home");
        String filePath = userHome + "/workspace/saveWiseData.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = "";

            // txt 내 텍스트 전체를 읽을때까지 반복문을 통해 데이터 로드하여 wiseArr에 데이터 저장
            while ((line = reader.readLine()) != null) {
                String[] len = line.split(",");

                if (len.length == 3) {
                    int loadPostNum = Integer.parseInt(len[0]);
                    String loadAuthor = len[1];
                    String loadSentence = len[2];

                    WiseModel wiseModel = new WiseModel();
                    wiseModel.setId(loadPostNum);
                    wiseModel.setAuthor(loadAuthor);
                    wiseModel.setContent(loadSentence);

                    wiseArr.add(wiseModel);
                }
            }

        } catch (IOException e) {
            System.out.println("로드될 데이터가 없으므로 더미 데이터를 삽입합니다.");
            testDataAdd();
        }
    }

    public void jsonConvert() {
        JSONArray jsonArr = new JSONArray();

        for (WiseModel model : wiseArr) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", model.getId());
            jsonObject.put("content", model.getContent());
            jsonObject.put("author", model.getAuthor());

            jsonArr.add(jsonObject);
        }

        System.out.println(jsonArr.toJSONString());
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