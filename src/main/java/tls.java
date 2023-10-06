import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class tls {

    public static void main (String[] args){

        // Only 1 argument, prints to command line if valid path
        if (args.length == 1){
            String path = args[0];

        }

        // 2 arguments and first argument is "-o", output to .csv file in same directory
        else if (args.length == 2 && args[0]=="-o"){
            String path = args[1];

        }

        // 3 arguments and first argument is "-o", output to .csv file in specified directory
        else if (args.length == 3 && args[0]=="-o"){
            String path = args[2];

        }

        // Invalid argument
        else {
            System.err.println("Usage correcte: java <chemin-entree> || java -o (<chemin-sortie>) <chemin-entree>");
            System.exit(1);
        }

    }

    // Find every test java file within the directory and its sub-directories
    public static List<File> testFiles(String path){

    }

}