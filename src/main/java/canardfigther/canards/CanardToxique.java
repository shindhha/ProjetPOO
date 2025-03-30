package canardfigther.canards;

import canardfigther.SpecialEffect;
import canardfigther.TypeCanard;

public class CanardToxique extends Canard {


    protected CanardToxique() {
        super(TypeCanard.TOXIQUE);
    }

    /**
     * Activer la capacité spéciale du CanardToxique
     * @return SpecialEffect qui empoisonne l'adversaire
     */
    @Override
    protected SpecialEffect activerCapaciteSpeciale() {
        System.out.println(this.nom + " utilise sa capacité spéciale : empoisonne l'adversaire.");
        return canard -> canard.state = CanardState.POISONED;

    }

}
