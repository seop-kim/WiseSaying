package com.techit.wiseapp.file;

import com.techit.wiseapp.model.WiseModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FIleConverter {
    private static final String FILE_PATH = "/Users/gimtaeseob/workspace/saveWiseData.txt";


    public void save(List<WiseModel> wiseArr) {
        try {
            // 파일 생성 및 텍스트 작성을 위한 버퍼드 객체 생성
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));

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

    public List<WiseModel> load() {
        List<WiseModel> wiseArr = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
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
            e.printStackTrace();
        }
        return wiseArr;
    }

}
