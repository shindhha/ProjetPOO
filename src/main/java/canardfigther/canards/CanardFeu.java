package canardfigther.canards;

import canardfigther.SpecialEffect;
import canardfigther.TypeCanard;

public class CanardFeu extends Canard {
    private static final int BONUS_ATTAQUE = 10;

    protected CanardFeu() {
        super(TypeCanard.FEU);
    }


    @Override
    /**
     * Activer la capacité spéciale du canard de feu
     * octroi un bonus d'attaque de 10 points
     * @return SpecialEffect : effet spécial du canard de feu à appliquer
     */
    protected SpecialEffect activerCapaciteSpeciale() {
        System.out.println(this.nom + " utilise sa capacité spéciale : inflige des dégâts supplémentaires.");
        return canard -> {
            pointsAttaque += BONUS_ATTAQUE;
            attaquer(canard);
            pointsAttaque -= BONUS_ATTAQUE;
        };
    }

}
