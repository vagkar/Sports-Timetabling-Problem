package thesis.xmldata;

import java.io.File;
import java.util.Scanner;

public class SelectXML {

    private final String pathToFilesFolder = "src/main/resources/";

    private final String[] xmlTestNames = {
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

    private final String[] xmlEarlyNames = {
            "ITC2021_Early_1.xml",
            "ITC2021_Early_2.xml",
            "ITC2021_Early_3.xml",
            "ITC2021_Early_4.xml",
            "ITC2021_Early_5.xml",
            "ITC2021_Early_6.xml",
            "ITC2021_Early_7.xml",
            "ITC2021_Early_8.xml",
            "ITC2021_Early_9.xml",
            "ITC2021_Early_10.xml",
            "ITC2021_Early_11.xml",
            "ITC2021_Early_12.xml",
            "ITC2021_Early_13.xml",
            "ITC2021_Early_14.xml",
            "ITC2021_Early_15.xml"
    };

    private final String[] xmlMiddleNames = {
            "ITC2021_Middle_1.xml",
            "ITC2021_Middle_2.xml",
            "ITC2021_Middle_3.xml",
            "ITC2021_Middle_4.xml",
            "ITC2021_Middle_5.xml",
            "ITC2021_Middle_6.xml",
            "ITC2021_Middle_7.xml",
            "ITC2021_Middle_8.xml",
            "ITC2021_Middle_9.xml",
            "ITC2021_Middle_10.xml",
            "ITC2021_Middle_11.xml",
            "ITC2021_Middle_12.xml",
            "ITC2021_Middle_13.xml",
            "ITC2021_Middle_14.xml",
            "ITC2021_Middle_15.xml"
    };

    private final String[] xmlLateNames = {
            "ITC2021_Late_1.xml",
            "ITC2021_Late_2.xml",
            "ITC2021_Late_3.xml",
            "ITC2021_Late_4.xml",
            "ITC2021_Late_5.xml",
            "ITC2021_Late_6.xml",
            "ITC2021_Late_7.xml",
            "ITC2021_Late_8.xml",
            "ITC2021_Late_9.xml",
            "ITC2021_Late_10.xml",
            "ITC2021_Late_11.xml",
            "ITC2021_Late_12.xml",
            "ITC2021_Late_13.xml",
            "ITC2021_Late_14.xml",
            "ITC2021_Late_15.xml"
    };

    private String fileName;

    public SelectXML() {
        selectInstance();
    }

    public SelectXML(int option) {
        this.fileName = pathToFilesFolder + xmlTestNames[option - 1];
    }

    private void selectInstance() {
        System.out.println("1)Test Instances");
        System.out.println("2)Early Instances");
        System.out.println("3)Middle Instances");
        System.out.println("4)Late Instances");
        System.out.print("Select Problem Instances: ");
        int option = new Scanner(System.in).nextInt();
        while (option > 4 || option < 1) {
            System.out.print("Try again or select 0 for Exit Program: ");
            option = getOption();
            if (option == 0) System.exit(-1);
        }
        switch (option) {
            case 1:
                selectFile(xmlTestNames);
                break;

            case 2:
                selectFile(xmlEarlyNames);
                break;

            case 3:
                selectFile(xmlMiddleNames);
                break;

            case 4:
                selectFile(xmlLateNames);
                break;
        }
    }

    private void selectFile(String[] xmlNames) {
        for (int i = 0; i < xmlNames.length; i++) {
            System.out.println((i+1) + ") " + xmlNames[i]);
        }
        System.out.print("Select an Instance: ");
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
