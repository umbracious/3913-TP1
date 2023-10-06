import java.io.File;
import java.util.*;

public class tropcomp {

    public static void main (String[] args){
        if (args.length==2){

            float threshold = Float.parseFloat(args[1]);
            String path = args[0];
            File directory = new File(path);

            // Invalid directory argument
            if (!directory.exists() || !directory.isDirectory()){
                System.err.println("Incorrect directory: java <entry-dir>");
                System.exit(1);
            }

            // Find all test files within directory
            List<File> testFiles = tls.findTestFiles(directory);

            List<Integer> tlocList = new ArrayList<>();
            List<Float> tcmpList = new ArrayList<>();

            // Compile a list of tloc and tcmp
            for (File file: testFiles) {
                HashMap info = tls.getInfo(file, path);
                int currTLOC = (int) info.get("tloc");
                int currTASSERT = (int) info.get("tassert");
                float currTCMP = currTLOC/currTASSERT;

                tlocList.add(currTLOC);
                tcmpList.add(currTCMP);

            }

            // Sort both lists
            Collections.sort(tlocList);
            Collections.sort(tcmpList);

            int tlocListSize = tlocList.size();
            int tcmpListSize = tcmpList.size();

            System.out.println("Files suspected of being too complex:");

            // Revisit each file and calculate its percentile compared to the compiled lists
            for (File file: testFiles){

                HashMap info = tls.getInfo(file, path);
                int currTLOC = (int) info.get("tloc");
                int currTASSERT = (int) info.get("tassert");
                float currTCMP = currTLOC/currTASSERT;

                float tlocPercentile = (tlocList.indexOf(currTLOC)+1)/tlocListSize;
                float tcmpPercentile = (tcmpList.indexOf(currTCMP)+1)/tcmpListSize;

                // If both percentiles exceed the threshold, print out the file in question
                if (tlocPercentile >= (100 - threshold) && tcmpPercentile >= (100 - threshold)){
                    System.out.println(info.get("relativePath") + " " + info.get("package") + " " +
                            info.get("className") + " " + info.get("tloc") + " " +  info.get("tassert") + " " +
                            (info.get("tcmp")));
                }

            }
        }

        else if (args.length==3){
            return;
        }

        else if (args.length==4){
            return;
        }

        else {
            System.out.println("Invalid Arguments. Correct syntax: java tropcomp (-o (<output-dir>) <input-dir>" +
                    " <threshold>");
        }

    }

}