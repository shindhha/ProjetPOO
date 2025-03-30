package canardfigther.canards;

import canardfigther.SpecialEffect;
import canardfigther.TypeCanard;

public abstract class Canard {

    // Classe interne permettant de créer un Canard avec un Builder
    public static class CanardBuilder {

        private static CanardFactory factory = new CanardFactory();
        private String nom;
        private int pointsDeVie;
        private int pointsAttaque;
        private int vitesse;
        private String typeString;
        private CanardState state;
        private boolean hasUsedSpecialAbility;

        // Création d'un Canard en utilisant les valeurs du Builder
        public Canard getCanard() {
            Canard canard = factory.creerCanard(typeString);
            canard.nom = this.nom;
            canard.pointsDeVie = this.pointsDeVie;
            canard.pointsAttaque = this.pointsAttaque;
            canard.vitesse = this.vitesse;
            canard.state = this.state;
            canard.hasUsedSpecialAbility = this.hasUsedSpecialAbility;
            return canard;
        }

        // Méthodes permettant de configurer chaque attribut du CanardBuilder
        public CanardBuilder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public CanardBuilder pointsDeVie(int pointsDeVie) {
            this.pointsDeVie = pointsDeVie;
            return this;
        }

        public CanardBuilder pointsAttaque(int pointsAttaque) {
            this.pointsAttaque = pointsAttaque;
            return this;
        }

        public CanardBuilder vitesse(int vitesse) {
            this.vitesse = vitesse;
            return this;
        }

        public CanardBuilder type(TypeCanard type) {
            this.typeString = type.name();
            return this;
        }

        public CanardBuilder type(String type) {
            this.typeString = type;
            return this;
        }

        public CanardBuilder state(CanardState state) {
            this.state = state;
            return this;
        }

        public CanardBuilder hasUsedSpecialAbility(boolean hasUsedSpecialAbility) {
            this.hasUsedSpecialAbility = hasUsedSpecialAbility;
            return this;
        }

        // Copie un Canard existant en créant une nouvelle instance
        public Canard copyCanard(Canard canard) {
            Canard copy = factory.creerCanard(canard.type);
            copy.nom = canard.nom;
            copy.pointsDeVie = canard.pointsDeVie;
            copy.pointsAttaque = canard.pointsAttaque;
            copy.vitesse = canard.vitesse;
            copy.type = canard.type;
            copy.state = canard.state;
            copy.hasUsedSpecialAbility = canard.hasUsedSpecialAbility;
            return copy;
        }
    }

    // États possibles d'un Canard
    public enum CanardState {
        NORMAL("normal"),
        POISONED("damage"),
        FROZEN("control");

        private String type;

        CanardState(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    // Attributs de base d'un Canard
    protected String nom;
    protected int pointsDeVie;
    protected int pointsAttaque;
    protected int vitesse;
    protected TypeCanard type;
    protected CanardState state;
    protected final int MAX_HEALTH = 100;
    private boolean hasUsedSpecialAbility;

    // Constructeur protégé pour forcer l'utilisation du Builder
    protected Canard(TypeCanard type) {
        this.type = type;
    }

    // Action d'attaque sur un autre Canard
    public void attaquer(Canard autreCanard) {
        double multiplicateur = type.getMuliplierAgainst(autreCanard.getType());
        int degats = (int) (this.pointsAttaque * multiplicateur);
        autreCanard.subirDegats(degats);
        System.out.println(this.nom + " attaque " + autreCanard.getNom() + " et inflige " + degats + " dégâts.");
    }

    // Réduction des points de vie du Canard attaqué
    public void subirDegats(int degats) {
        this.pointsDeVie -= degats;
        if (this.pointsDeVie < 0) {
            this.pointsDeVie = 0;
        }
    }

    // Activation d'une capacité spéciale
    public SpecialEffect utiliserCapaciteSpeciale() {
        if (!hasUsedSpecialAbility) {
            hasUsedSpecialAbility = true;
            return activerCapaciteSpeciale();
        }
        throw new IllegalArgumentException(this.nom + " a déjà utilisé sa capacité spéciale.");
    }

    // Vérifie si le Canard peut jouer
    public boolean canPlay() {
        return !estKO() && !state.type.equals("control");
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public TypeCanard getType() {
        return type;
    }

    public boolean estKO() {
        return pointsDeVie <= 0;
    }

    // Méthode abstraite pour activer une capacité spéciale propre à chaque type de Canard
    protected abstract SpecialEffect activerCapaciteSpeciale();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Canard) {
            Canard other = (Canard) obj;
            return this.nom.equals(other.nom)
                    && this.pointsDeVie == other.pointsDeVie
                    && this.pointsAttaque == other.pointsAttaque
                    && this.type == other.type;
        }
        return false;
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    public int getPointsAttaque() {
        return pointsAttaque;
    }

    public boolean hasUsedSpecialAbility() {
        return hasUsedSpecialAbility;
    }

    public CanardState getState() {
        return state;
    }
}
