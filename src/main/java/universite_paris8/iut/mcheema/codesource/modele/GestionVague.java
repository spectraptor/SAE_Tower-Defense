package universite_paris8.iut.mcheema.codesource.modele;
public class GestionVague {
    private final static int TEMPS_ENNEMIS_VAGUE_FRM = 100;

    private int numVagueCourante;
    private Vague[] vagues;
    private Environnement environnement;

    public GestionVague(int numNiv, Environnement env) {
        this.numVagueCourante = 0;
        this.vagues = new Vague[3];
        this.vagues[0] = new Vague(1, numNiv, env);
        this.vagues[1] = new Vague(2, numNiv, env);
        this.vagues[2] = new Vague(3, numNiv, env);
        this.environnement = env;
    }

    public Vague[] getVagues() {
        return this.vagues;
    }

    public int getNbreVagues() {
        return this.vagues.length;
    }

    /**
     * Met à jour les ennemis de la vague au fur et à mesure.
     */
    public void mettreAJour() {
            if (!this.vagues[this.numVagueCourante].getlisteEnnemisVague().isEmpty())
                if (this.environnement.getNbTours() % TEMPS_ENNEMIS_VAGUE_FRM == 0) {
                    this.environnement.ajouterEnnemi(this.vagues[this.numVagueCourante].getlisteEnnemisVague().remove(0));
        }
    }

    public int getNumVagueCourante() {
        return this.numVagueCourante;
    }

    public void incrementerVagueCourante() {
        // Ligne obligatoire : sinon, lorsque la derniere vague est finie,
        // on va à nouveau incrémenter et accéder à un indice qui n'existe pas.
        if (this.numVagueCourante < this.getNbreVagues() - 1)
            this.numVagueCourante++;
    }
}
