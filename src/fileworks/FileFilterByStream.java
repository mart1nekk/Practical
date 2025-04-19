package fileworks;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FileFilterByStream {

    static ArrayList<File> GetFilteredFiles (String dirPath, String fileType, long minSize, long maxSize){

        ArrayList<File> filteredFiles = new ArrayList<>();

        File folder = new File(dirPath);
        //ověřím si, jestli složka existuje a nebo jestli to neni soubor
        if (!folder.exists() || !folder.isDirectory()) {
            return filteredFiles;
        }
        //sem se vysype potom obsah
        File[] contents = folder.listFiles();
        if (contents == null) {
            return filteredFiles;
        }

        filteredFiles.stream()
                .filter(File::isFile)
                .filter(s -> s.getName().endsWith(".txt"))
                .filter(s -> s.length() >= minSize && s.length() <= maxSize)
                .sorted((f1, f2) -> Long.compare(f2.length(), f1.length()))
               // .collect(Collectors.toCollection(ArrayList::new));
                .toList();
        return filteredFiles;
    }

    static void printFiles(ArrayList<File> files) {
        files.forEach(f ->
                System.out.println(f.getName() + " (" + f.length() + " B)")
        );
    }

    public static void main(String[] args) {
        String path = "data/clusterfuck";
        String filetype= ".txt";
        long minSize = 5 * 1024;
        long maxSize = 100 * 1024;

        ArrayList<File> result = GetFilteredFiles(path, filetype, minSize, maxSize);
        printFiles(result);
    }
}

