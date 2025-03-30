package canardfigther.canards;

import canardfigther.SpecialEffect;
import canardfigther.TypeCanard;

public class CanardVent extends Canard {


    protected CanardVent() {
        super(TypeCanard.VENT);
    }

    /**
     * Activer la capacité spéciale du CanardVent
     * @return null car la capacité spéciale du CanardVent n'a d'effet que sur lui même
     */
    @Override
    public SpecialEffect activerCapaciteSpeciale() {
        System.out.println(getNom() + " utilise sa capacité spéciale : Attaque rapide (attaque deux fois) !");
        vitesse *= 2;
        return null;
    }

    @Override
    public void attaquer(Canard autreCanard) {

    }
}
