package fileworks;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

public class FileFilter {


    static ArrayList<File> getFilesByType(String dirPath, String fileType,long minsize,long maxsize){
        ArrayList<File> filteredFiles = new ArrayList<>();
        File wannabeDir = new File(dirPath);

        if (!wannabeDir.exists() && wannabeDir.isDirectory()){
            return null;
        }
        File[] dirContent = wannabeDir.listFiles();
        for (File file : dirContent){
            if (file.isFile() && file.getName().endsWith(fileType)&& file.length()<= minsize && file.length()>=maxsize){ //uprava pro filtrovani i dle velikosti
                filteredFiles.add(file);
            }
        }
        return filteredFiles;
    }

    final static Comparator<File> BY_SIZE = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {

            return Long.compare(o1.length(), o2.length());
        }
    };

    static void printSortedFiles(ArrayList<File> files){
        files.sort(BY_SIZE.reversed());
        for (File f : files){
            System.out.println(f + ": " + f.length());
        }
    }

    public static void main(String[] args) {
        ArrayList<File> data = getFilesByType("data\\clusterfuck", "txt",500,5000);
        printSortedFiles(data);
    }
}