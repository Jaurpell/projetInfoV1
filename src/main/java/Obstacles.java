import java.util.random
public class Obstacles {
        private int longueur;
        private int hauteur;
        private int deplacement;

        public Obstacle(int maxlongueur) {
            Random rand = new Random();
            this.longueur= rand.nextInt(20-5);
            this.hauteur= 10

            this.deplacement = -5

        }

        public int getLongueuer() {
            return this.longueur;
        }
        public int getHauteur() {return this.hauteur}
        public int getDeplacementObstacle() {return this.deplacement}
}
