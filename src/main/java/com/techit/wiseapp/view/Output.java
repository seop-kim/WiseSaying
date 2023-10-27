package com.techit.wiseapp.view;

import com.techit.wiseapp.model.Saying;
import java.util.List;

public class Output {
    public void printAddSaying(Saying saying) {
        System.out.println(saying.getId() + "번 명언이 저장되었습니다.");
    }

    public void printSayings(List<Saying> sayings) {
        System.out.println("번호\t\t작가\t\t명언");
        System.out.println("---------------------------");
        for (Saying saying : sayings) {
            System.out.printf("%d \t\t %s \t\t %s\n", saying.getId(), saying.getAuthor(),
                    saying.getContent());
        }
    }

    public void printDelSaying(Long id) {
        System.out.println(id + " 번 명언이 삭제되었습니다.");
    }
}
