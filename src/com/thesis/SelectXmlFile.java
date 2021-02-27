package com.thesis;

import java.util.Scanner;

public class SelectXmlFile {
    public static String selectXmlFile() {
        String[] xmlFiles = {"instances/ITC2021_Test1.xml",
                "instances/ITC2021_Test2.xml",
                "instances/ITC2021_Test3.xml",
                "instances/ITC2021_Test4.xml",
                "instances/ITC2021_Test5.xml",
                "instances/ITC2021_Test6.xml",
                "instances/ITC2021_Test7.xml",
                "instances/ITC2021_Test8.xml",
                "instances/TestInstanceDemo.xml"};

        for (int i = 0; i < xmlFiles.length; i++) {
            if (i == 8) {
                System.out.println(i + ") " + xmlFiles[i].substring(10,26));
                continue;
            }
            System.out.println(i + ") " + xmlFiles[i].substring(10,23));
        }

        System.out.print("Choose Instance XML File: ");
        Scanner scanner = new Scanner(System.in);
        int indexFile = scanner.nextInt();

        return xmlFiles[indexFile];
    }
}
