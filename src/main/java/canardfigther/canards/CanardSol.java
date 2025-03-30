package canardfigther.canards;

import canardfigther.SpecialEffect;
import canardfigther.TypeCanard;

public class CanardSol extends Canard {


    protected CanardSol() {
        super(TypeCanard.SOL);
    }

    /**
     * Activer la capacité spéciale du CanardSol
     * @return SpecialEffect qui attaque l'adversaire
     */
    @Override
    protected SpecialEffect activerCapaciteSpeciale() {
        System.out.println(this.nom + " utilise sa capacité spéciale");
        return canard -> attaquer(canard);
    }
}
