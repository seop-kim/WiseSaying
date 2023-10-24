package com.techit.wiseapp;

import com.techit.wiseapp.model.WiseModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.DesktopManager;

public class Application {

    public static List<WiseModel> wiseArr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
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

            // update
            if (input.contains("수정?id=")) {
                String inputSplit = input.split("=")[1];
                int updateNum = Integer.parseInt(inputSplit);
                int index = -1;

                for (int i = 0; i < wiseArr.size(); i++) {
                    if (wiseArr.get(i).getPostNum() == updateNum) {
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
                System.out.println("명언(기존) : " + wiseArr.get(index).getSentence());
                System.out.print("명언 : ");
                input = sc.nextLine();
                wiseArr.get(index).setSentence(input);

                // 작가 수정 부
                System.out.println("작가(기존) : " + wiseArr.get(index).getAuthor());
                System.out.print("작가 : ");
                input = sc.nextLine();
                wiseArr.get(index).setAuthor(input);

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
        return wiseArr.get(wiseArr.size() - 1).getPostNum() + 1;
    }

    // 파일 처리
    public void save()  {
        // 저장할 경로를 선택해야한다. 추후에 시스템 폴더로 지정하여 누구든지 쓸 수 있게 변경 예정
        String saveFilePath = "/Users/gimtaeseob/workspace/saveWiseData.txt";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveFilePath));
            for (WiseModel wiseModel : wiseArr) {
                String objectText = String.format("%d,%s,%s", wiseModel.getPostNum(), wiseModel.getAuthor(),
                        wiseModel.getSentence());
                System.out.println("objectText = " + objectText);
                writer.write(objectText);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        String userHome = System.getProperty("user.home");
        String filePath = userHome + "/workspace/saveWiseData.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    int loadPostNum = Integer.parseInt(parts[0]);
                    String loadAuthor = parts[1];
                    String loadSentence = parts[2];

                    WiseModel wiseModel = new WiseModel();
                    wiseModel.setPostNum(loadPostNum);
                    wiseModel.setAuthor(loadAuthor);
                    wiseModel.setSentence(loadSentence);

                    wiseArr.add(wiseModel);
                }
            }

            System.out.println("데이터가 로드되었습니다.");
        } catch (IOException e) {
            System.out.println("로드될 데이터가 없습니다.");
            e.printStackTrace();
        }
    }

    public void testDataAdd() {
        WiseModel model = new WiseModel();
        model.setPostNum(getPostNum());
        model.setAuthor("김태섭");
        model.setSentence("김김자라김김자라");
        wiseArr.add(model);

        model = new WiseModel();
        model.setPostNum(getPostNum());
        model.setAuthor("김태섭1");
        model.setSentence("김김자라김김자라");
        wiseArr.add(model);

        model = new WiseModel();
        model.setPostNum(getPostNum());
        model.setAuthor("김태섭2");
        model.setSentence("김김자라김김자라");
        wiseArr.add(model);

        model = new WiseModel();
        model.setPostNum(getPostNum());
        model.setAuthor("김태섭3");
        model.setSentence("김김자라김김자라");
        wiseArr.add(model);

        model = new WiseModel();
        model.setPostNum(getPostNum());
        model.setAuthor("김태섭4");
        model.setSentence("김김자라김김자라");
        wiseArr.add(model);

        model = new WiseModel();
        model.setPostNum(getPostNum());
        model.setAuthor("김태섭5");
        model.setSentence("김김자라김김자라");
        wiseArr.add(model);


    }
}