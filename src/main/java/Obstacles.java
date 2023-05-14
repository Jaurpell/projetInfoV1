import java.util.random
public class Obstacles {
        private int longueur;
        private int hauteur;

        public Obstacle(int maxlongueur, int maxhauteur) {
            Random rand = new Random();
            longueur= rand.nextInt(maxlongueur);
            hauteur = rand.nextInt(maxhauteur);
        }

        public int getlongueuer() {
            return longueur;
        }

        public int gethauteur() {
            return hauteur;
        }
}
