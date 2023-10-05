import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TASSERT {
    public static void main(String[] args) {
        //si le nombre d'argument est plus qu'un (exit)
        if (args.length != 1) {
            System.err.println("Usage: java TASSERTCounter <nom du fichier source>");
            System.exit(1);
        }

        String fileName = args[0];
        int tassert = calculateTASSERT(fileName);

        System.out.println("TASSERT (Total : nombre de assertions JUnitAssertions): " + tassert);
    }

    public static int calculateTASSERT(String fileName) {
        int tassert = 0;

        //le pattern.compile cherche pour des strings qui commence avec "assert" + des lettres entre A-z et finalment
        //qui fini avec une parenthese.
        Pattern assertionPattern = Pattern.compile("(assert[A-Za-z]+\\()");

        //lit ligne par ligne tout les lignes du fichier entré en parametre
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                //define matcher comme une expression pour chercher les ASSERTS
                Matcher matcher = assertionPattern.matcher(line); 
                
                //cherche le pattern des ASSERTS et lorsqu'on trouve
                //incremente tassert.
                while (matcher.find()) {
                    tassert++;
                }
            }
        } catch (IOException e) {
            //probleme de lecture
            System.err.println("Erreur lecture du fichier : " + e.getMessage());
            System.exit(1);
        }

        return tassert;
    }
}

//sources (Regex-Matcher et Pattern) :
//pour Pattern  https://www.geeksforgeeks.org/pattern-compilestring-method-in-java-with-examples
//pour Matcher , incluant matcher.fin() : https://jenkov.com/tutorials/java-regex/matcher.html