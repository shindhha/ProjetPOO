package canardfigther.canards;

import canardfigther.TypeCanard;

public class CanardFactory {


    /**
     * Créer un canard en fonction du type
     * @param type le type de canard à créer
     * @return le canard créé
     */
    public Canard creerCanard(String type) {
        switch (type) {
            case "FEU":
                return new CanardElectrique();
            case "VENT":
                return new CanardVent();
            case "GLACE":
                return new CanardGlace();
            case "ELECTRIQUE":
                return new CanardElectrique();
            case "SOL":
                return new CanardSol();
            case "TOXIQUE":
                return new CanardToxique();
            case "EAU":
                return new CanardEau();
            default:
                return null;
        }
    }

    public Canard creerCanard(TypeCanard type) {
        return creerCanard(type.name());
    }


}
