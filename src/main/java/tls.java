import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class tls {

    public static void main (String[] args){

        // Only 1 argument, prints to command line if valid path
        if (args.length == 1){
            String path = args[0];
            File directory = new File(path);

            if (!directory.exists() || !directory.isDirectory()){
                System.err.println("Incorrect directory. Usage: java <entry-dir>");
                System.exit(1);
            }

        }

        // 2 arguments and first argument is "-o", output to .csv file in same directory
        else if (args.length == 2 && args[0]=="-o"){
            String path = args[1];
            File directory = new File(path);

        }

        // 3 arguments and first argument is "-o", output to .csv file in specified directory
        else if (args.length == 3 && args[0]=="-o"){
            String path = args[2];
            File directory = new File(path);

        }

        // Invalid argument
        else {
            System.err.println("Correct usage: java <entry-dir> || java -o (<output-dir>) <entry-dir>");
            System.exit(1);
        }

    }

    // Find every test java file within the directory and its sub-directories
    public static List<File> testFiles(File directory){
        List<File> javaFiles = new ArrayList<>();
        
    }

}