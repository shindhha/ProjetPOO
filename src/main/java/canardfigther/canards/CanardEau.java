package canardfigther.canards;

import canardfigther.SpecialEffect;
import canardfigther.TypeCanard;

public class CanardEau extends Canard {


    protected CanardEau() {
        super(TypeCanard.EAU);
    }

    /**
     * Activer la capacité spéciale du CanardEau
     * Régénère 20 PV
     * @return null car le canard n'agit que sur lui même
     */
    @Override
    protected SpecialEffect activerCapaciteSpeciale() {
        System.out.println(this.nom + " utilise sa capacité spéciale : régénère 20 PV.");
        if (this.pointsDeVie + 20 > MAX_HEALTH) {
            this.pointsDeVie = MAX_HEALTH;
        } else {
            this.pointsDeVie += 20;
        }
        return null;
    }
}