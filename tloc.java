import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TLOC {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("java TLOC <nom du fichier source>");
            System.exit(1);
        }

        String fileName = args[0];
        int tloc = calculateTLOC(fileName);

        System.out.println("TLOC (nombre de lignes de code non-vides qui ne sont pas de commentaires): " + tloc);
    }

    public static int calculateTLOC(String fileName) {
        int tloc = 0;
            //BufferReader nous permet de lire ligne par ligne le contenu du fichier
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Ignore les lignes vides et ne incremente pas tloc.
                if (line.trim().isEmpty()) {
                    continue; //continue a la prochaine ligne du fichier
                }

                // line.trim().startsWith regarde si la ligne commence avec '//' ou '/*' , si c'est le cas c'est une ligne de commentaire donc on saute
                // a la prochaine ligne sans incrementer tloc.  
                if (line.trim().startsWith("//") || line.trim().startsWith("/*")) {
                    continue; //continue a la prochaine ligne du fichier
                }
                //incremente tloc si c'est une ligne de code (pas commentaire ou vide)
                tloc++;
            }
        } catch (IOException e) {
            //probleme de lecture du ficher
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            System.exit(1);
        }

        return tloc;
    }
}
