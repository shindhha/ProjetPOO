package canardfigther.canards;

import canardfigther.SpecialEffect;
import canardfigther.TypeCanard;

public class CanardElectrique extends Canard {


    protected CanardElectrique() {
        super(TypeCanard.ELECTRIQUE);
    }

    /**
     * Activer la capacité spéciale du CanardElectrique
     * Inflige des dégâts supplémentaires
     * @return SpecialEffect qui inflige des dégâts supplémentaires
     */
    @Override
    protected SpecialEffect activerCapaciteSpeciale() {
        System.out.println(this.nom + " utilise sa capacité spéciale : inflige des dégâts supplémentaires.");
        return canard -> canard.subirDegats(this.pointsAttaque);

    }


}
