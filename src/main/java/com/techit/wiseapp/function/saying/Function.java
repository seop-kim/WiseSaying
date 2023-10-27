package com.techit.wiseapp.function.saying;

import com.techit.wiseapp.file.FIleConverter;
import com.techit.wiseapp.input.Input;
import com.techit.wiseapp.json.JsonConverter;
import com.techit.wiseapp.model.WiseModel;
import com.techit.wiseapp.repository.SayingRepository;
import com.techit.wiseapp.validator.SayingValidate;
import java.util.Scanner;
import org.json.simple.JSONArray;

public class Function {
    private final SayingRepository repo = SayingRepository.getInstance();
    private final SayingValidate validate = new SayingValidate();
    private final FIleConverter fc = new FIleConverter();

    public void register() {
        WiseModel wiseModel = new WiseModel();

        System.out.print("명언 : ");
        String addInput = Input.read();
        wiseModel.setContent(addInput);

        System.out.print("작가 : ");
        addInput = Input.read();
        wiseModel.setAuthor(addInput);

        WiseModel saying = repo.save(wiseModel);

        System.out.println(saying.getId() + "번 명언이 등록되었습니다.");
    }

    public void list() {
        System.out.println("번호\t\t작가\t\t명언");
        System.out.println("---------------------------");
        for (WiseModel model : repo.findAll()) {
            System.out.printf("%d \t\t %s \t\t %s\n", model.getId(), model.getAuthor(),
                    model.getContent());
        }
    }

    public void delete() {
        String inputSplit = Input.read().split("=")[1];
        Long id = Long.parseLong(inputSplit);

        if (validate.findValidate(id)) {
            repo.delete(id);
            System.out.println(id + "번 명언이 삭제되었습니다.");
        }
    }

    public void update() {
        String inputSplit = Input.read().split("=")[1];
        Long id = Long.parseLong(inputSplit);

        if (!validate.findValidate(id)) {
            return;
        }

        WiseModel findOne = repo.findOne(id);
        String input = "";

        // 명언 수정 부
        System.out.println("명언(기존) : " + findOne.getContent());
        System.out.print("명언 : ");
        input = Input.read();
        findOne.setContent(input);

        // 작가 수정 부
        System.out.println("작가(기존) : " + findOne.getAuthor());
        System.out.print("작가 : ");
        input = Input.read();
        findOne.setAuthor(input);
    }

    public void build() {
        JsonConverter jc = new JsonConverter();
        JSONArray jsonArray = jc.javaArrToJsonArr(repo.findAll());
        System.out.println("jsonArray = " + jsonArray.toJSONString());
    }

    public void save() {
        fc.save(repo.findAll());
    }
}
