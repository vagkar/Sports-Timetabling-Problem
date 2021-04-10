package com.thesis.xmldata;

import java.io.File;
import java.util.Scanner;

public class SelectXML {

    private final String pathToFilesFolder = "instances/";

    private final String[] xmlNames = {
            "ITC2021_Test1.xml",
            "ITC2021_Test2.xml",
            "ITC2021_Test3.xml",
            "ITC2021_Test4.xml",
            "ITC2021_Test5.xml",
            "ITC2021_Test6.xml",
            "ITC2021_Test7.xml",
            "ITC2021_Test8.xml",
            "TestInstanceDemo.xml",
            "test.xml"
    };

    private String fileName;

    public SelectXML() {
        for (int i = 0; i < xmlNames.length; i++) {
            System.out.println((i+1) + ") " + xmlNames[i]);
        }
        System.out.print("Select an Instance:");


        int option = new Scanner(System.in).nextInt();
        while (option > xmlNames.length || option < 1) {
            System.out.print("Try again or select 0 for Exit Program: ");
            option = getOption();
            if (option == 0) System.exit(-1);
        }
        fileName = pathToFilesFolder + xmlNames[option - 1];
    }

    public File getSelectedFile(){
        return new File(fileName);
    }

    private int getOption() {
        return new Scanner(System.in).nextInt();
    }

}
