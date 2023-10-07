import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
                float currTCMP = (float) currTLOC/ (float) currTASSERT;

                tlocList.add(currTLOC);
                tcmpList.add(currTCMP);

            }

            // Sort both lists
            Collections.sort(tlocList);
            Collections.sort(tcmpList);

            int fileListSize = tlocList.size();

            System.out.println("Files suspected of being too complex:");

            // Revisit each file and calculate its percentile compared to the compiled lists
            for (File file: testFiles){

                HashMap info = tls.getInfo(file, path);
                int currTLOC = (int) info.get("tloc");
                int currTASSERT = (int) info.get("tassert");
                float currTCMP = (float) currTLOC/ (float) currTASSERT;

                float tlocPercentile = (float) ((tlocList.indexOf(currTLOC)+1)*100)/(float) fileListSize;
                float tcmpPercentile = (float) ((tcmpList.indexOf(currTCMP)+1)*100)/(float) fileListSize;

                // If both percentiles exceed the threshold, write the files info to the .csv file

                float percentileThreshold = 100 - threshold;

                // If both percentiles exceed the threshold, print out the file in question
                if (tlocPercentile >= (100 - threshold) && tcmpPercentile >= (100 - threshold)){
                    System.out.println(info.get("relativePath") + " " + info.get("package") + " " +
                            info.get("className") + " " + info.get("tloc") + " " +  info.get("tassert") + " " +
                            (info.get("tcmp")));
                }

            }
        }

        else if (args.length==3 && args[0].equals("-o")){

            float threshold = Float.parseFloat(args[2]);
            String path = args[1];
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
                float currTCMP = (float) currTLOC/ (float) currTASSERT;

                tlocList.add(currTLOC);
                tcmpList.add(currTCMP);

            }

            // Sort both lists
            Collections.sort(tlocList);
            Collections.sort(tcmpList);

            int fileListSize = tlocList.size();

            // Output to tropcomp
            try {

                FileWriter fileWriter = new FileWriter("tropcomp.csv");

                // Revisit each file to calculate its percentile and compare it to the threshold
                for (File file: testFiles){

                    HashMap info = tls.getInfo(file, path);
                    int currTLOC = (int) info.get("tloc");
                    int currTASSERT = (int) info.get("tassert");
                    float currTCMP = (float) currTLOC/ (float) currTASSERT;

                    float tlocPercentile = (float) ((tlocList.indexOf(currTLOC)+1)*100)/(float) fileListSize;
                    float tcmpPercentile = (float) ((tcmpList.indexOf(currTCMP)+1)*100)/(float) fileListSize;

                    // If both percentiles exceed the threshold, write the files info to the .csv file

                    float percentileThreshold = 100 - threshold;


                    if (tlocPercentile >= percentileThreshold && tcmpPercentile >= percentileThreshold){
                        // Write info from hashmap to .csv file
                        fileWriter.write(info.get("relativePath") + "," + info.get("package") + ","
                                + info.get("className") + "," + Integer.toString((Integer) info.get("tloc")) + ","
                                + Integer.toString((Integer) info.get("tassert"))  + ","
                                + Float.toString((Float) info.get("tcmp")) + "\n");
                    }



                }

                fileWriter.close();

            } catch (IOException e) {
                System.out.println("Invalid output file directory");
                e.printStackTrace();
                System.exit(1);
            }

        }

        else if (args.length==4 && args[0].equals("-o")){

            float threshold = Float.parseFloat(args[3]);
            String path = args[2];
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
                float currTCMP = (float) currTLOC/ (float) currTASSERT;

                tlocList.add(currTLOC);
                tcmpList.add(currTCMP);

            }

            // Sort both lists
            Collections.sort(tlocList);
            Collections.sort(tcmpList);

            int fileListSize = tlocList.size();

            // Output to tls.csv
            try {

                FileWriter fileWriter = new FileWriter(args[1]);

                // Revisit each file to calculate its percentile and compare it to the threshold
                for (File file: testFiles){

                    HashMap info = tls.getInfo(file, path);
                    int currTLOC = (int) info.get("tloc");
                    int currTASSERT = (int) info.get("tassert");
                    float currTCMP = (float) currTLOC/ (float) currTASSERT;

                    float tlocPercentile = (float) ((tlocList.indexOf(currTLOC)+1)*100)/(float) fileListSize;
                    float tcmpPercentile = (float) ((tcmpList.indexOf(currTCMP)+1)*100)/(float) fileListSize;

                    // If both percentiles exceed the threshold, write the files info to the .csv file

                    float percentileThreshold = 100 - threshold;

                    if (tlocPercentile >= percentileThreshold && tcmpPercentile >= percentileThreshold){
                        // Write info from hashmap to .csv file
                        fileWriter.write(info.get("relativePath") + "," + info.get("package") + ","
                                + info.get("className") + "," + Integer.toString((Integer) info.get("tloc")) + ","
                                + Integer.toString((Integer) info.get("tassert"))  + ","
                                + Float.toString((Float) info.get("tcmp")) + "\n");
                    }



                }

                fileWriter.close();

            } catch (IOException e) {
                System.out.println("Invalid output file directory");
                e.printStackTrace();
                System.exit(1);
            }

        }

        else {
            System.out.println("Invalid Arguments. Correct syntax: java tropcomp (-o (<output-dir>)) <input-dir>" +
                    " <threshold>");
        }

    }

}