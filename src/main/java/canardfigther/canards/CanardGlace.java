package canardfigther.canards;

import canardfigther.SpecialEffect;
import canardfigther.TypeCanard;

public class CanardGlace extends Canard {


    protected CanardGlace() {
        super(TypeCanard.GLACE);
    }
    /**
     * Activer la capacité spéciale du CanardGlace
     * Gèle l'adversaire
     * @return SpecialEffect qui gèle l'adversaire
     */
    @Override
    protected SpecialEffect activerCapaciteSpeciale() {
        System.out.println(this.nom + " utilise sa capacité spéciale : gèle l'adversaire.");
        return canard -> canard.state = CanardState.FROZEN;
    }

}
