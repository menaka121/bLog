package com.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by menaka on 5/8/15.
 */
public class File_Test {
    private static String file_Dir = "resources/";

    public static void main(String args[]) {
        check();
        File file = new File(file_Dir);
        LinkedList<File> fl = new LinkedList<File>();

        for (File f : file.listFiles()) {
            fl.add(f);
        }

        Collections.sort(fl);


        int i = 0;
        if (fl.size() != 0) {
            try {
                System.out.println(fl.size());
                int fn = Integer.parseInt(fl.getLast().getName().replaceAll(".txt", ""));
                File f = new File(file_Dir + (fn + 1) + ".txt");
//                f.mkdir();
                FileWriter fw = new FileWriter(f);
                BufferedWriter d = new BufferedWriter(fw);
                d.write("kfkdjfjdslfjdsl");
                d.close();
                f.mkdirs();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {
                File f = new File(file_Dir + "100000.txt");

//                f.mkdir();
                FileWriter fw = new FileWriter(f);
                BufferedWriter d = new BufferedWriter(fw);
                d.write("kfkdjfjdslfjlljljdsl");
                d.close();
                f.mkdirs();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void check() {
        File f = new File(file_Dir);
        System.out.println(f.listFiles().length);
    }
}
