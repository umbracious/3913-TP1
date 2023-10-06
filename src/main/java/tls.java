import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class tls {

    public static void main (String[] args){

        // Only 1 argument, prints to command line if valid path
        if (args.length == 1){
            String path = args[0];
            File directory = new File(path);

            // Invalid directory argument
            if (!directory.exists() || !directory.isDirectory()){
                System.err.println("Incorrect directory: java <entry-dir>");
                System.exit(1);
            }

        }

        // 2 arguments and first argument is "-o", output to .csv file in same directory
        else if (args.length == 2 && args[0]=="-o"){
            String path = args[1];
            File directory = new File(path);

            // Invalid directory argument
            if (!directory.exists() || !directory.isDirectory()){
                System.err.println("Incorrect directory: java -o <entry-dir>");
                System.exit(1);
            }

        }

        // 3 arguments and first argument is "-o", output to .csv file in specified directory
        else if (args.length == 3 && args[0]=="-o"){
            String path = args[2];
            File directory = new File(path);

            // Invalid entry directory
            if (!directory.exists() || !directory.isDirectory()){
                System.err.println("Incorrect entry directory. java -o (<output-dir>) <entry-dir>");
                System.exit(1);
            }

        }

        // Invalid argument
        else {
            System.err.println("Correct usage: java <entry-dir> || java -o (<output-dir>) <entry-dir>");
            System.exit(1);
        }

    }

    // Find every test java file within the directory and its sub-directories
    public static List<File> findTestFiles(File directory){
        List<File> files = new ArrayList<>();

        testFilesRec(directory,files);

        return files;
    }

    // Recursive in place function that adds test java files from every sub-directory to the list
    public static void testFilesRec(File directory, List<File> files){

        File[] dirFiles = directory.listFiles();

        if (files != null){
            for (File file: dirFiles){

                // If files is a directory, recursive call for all files inside it
                if (file.isDirectory()){
                    testFilesRec(file, files);
                }

                // Adds file to list if it's a .java file with test somewhere in the name
                else if (file.getName().toLowerCase().endsWith(".java")
                        && file.getName().toLowerCase().contains("test")){
                    files.add(file);
                }
            }
        }

    }

}