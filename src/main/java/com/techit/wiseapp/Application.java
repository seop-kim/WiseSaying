package com.techit.wiseapp;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("명령) ");
            String input = sc.nextLine();

            if (input.equals("종료")) {
                break;
            }
        }
    }
}