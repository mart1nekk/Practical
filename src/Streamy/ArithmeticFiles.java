package Streamy;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ArithmeticFiles {

    public static void main(String[] args) throws IOException {
        File[] files = new File("data/priklady_parse_text").listFiles();
//        System.out.println(Arrays.toString(files));

        double countTxt = 0;
        double countAll = files.length;
        ArrayList<File> filteredFiles = new ArrayList<>();
        for (File f : files) {
            if (f.getName().startsWith("file_") && f.getName().endsWith("txt")) {
                filteredFiles.add(f);
            }
            if (f.getName().endsWith("txt")) {
                countTxt++;
            }
        }
       System.out.println(filteredFiles);


        PrintWriter pw = new PrintWriter(new FileWriter("data/priklady_parse_text/out.txt"));
        for (int i = 1; i <= 10; i++) {
            BufferedReader br = new BufferedReader(new FileReader("data/priklady_parse_text/file_" + i + ".txt"));
            String line;
            String[] split;
            while ((line = br.readLine()) != null) {
                split = line.split(",");
                if (!(split[0].startsWith("["))) {
                    switch (split[2]) {
                        case "M" -> pw.println(Double.parseDouble(split[0]) * Double.parseDouble(split[1]));
                        case "S" -> pw.println(Double.parseDouble(split[0]) - Double.parseDouble(split[1]));
                        case "A" -> pw.println(Double.parseDouble(split[0]) + Double.parseDouble(split[1]));
                        case "D" -> pw.println(Double.parseDouble(split[0]) / Double.parseDouble(split[1]));
                    }
                }
            }
            br.close();
        }
        pw.close();

        System.out.println("Percentage: " + (countTxt / countAll * 100));

        //hleda jendnotlive  typy souboru a počítá kolik jich je
        Map<String, Long> mapTypes = Arrays.stream(files)
                .collect(Collectors.groupingBy(l -> l.getName().substring(l.getName().lastIndexOf(".")+1), Collectors.counting()));
        System.out.println(mapTypes);
    }
}
