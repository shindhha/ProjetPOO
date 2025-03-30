package canardfigther;

import canardfigther.canards.Canard;
import fr.shindhha.canards.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CanardTest {

    static Canard.CanardBuilder builder = new Canard.CanardBuilder();

    @Test
    void Canard_should_be_able_to_deal_damage() {
        final int atk = 20;

        Canard attacker = builder.nom("Canard").type("EAU").pointsDeVie(100).vitesse(1).pointsAttaque(atk).getCanard();
        Canard target = builder.nom("Canard").type("FEU").pointsDeVie(100).vitesse(1).pointsAttaque(atk).getCanard();

        attacker.attaquer(target);
        // water has advantage over fire so : 100 - (20 * 0.5) = 90
        assertEquals(90, target.getPointsDeVie());
    }

    @Test
    void Canard_should_be_able_to_receive_damage() {
        Canard target = builder.nom("Canard").type("FEU").pointsDeVie(100).vitesse(1).pointsAttaque(10).getCanard();

        target.subirDegats(20);

        assertEquals(80, target.getPointsDeVie());

    }


    @Test
    void after_special_ability_eau_should_have_20_more_hp() {
        final int pdv = 100;
        Canard target = builder.nom("Canard").type("EAU").pointsDeVie(pdv).vitesse(1).pointsAttaque(20).getCanard();
        target.subirDegats(20);
        // heal 20 hp
        target.utiliserCapaciteSpeciale();

        assertEquals(pdv, target.getPointsDeVie());

    }

    @Test
    void after_special_ability_eau_should_not_exceed_max_hp() {
        final int pdv = 100;
        Canard target = builder.nom("Canard").type("EAU").pointsDeVie(pdv).vitesse(1).pointsAttaque(20).getCanard();
        target.subirDegats(10); // 100 - 10 = 90
        // Base and max hp is 100, so it should not exceed 100
        target.utiliserCapaciteSpeciale();
        // 90 + 20 > 100 so it should be 100
        assertEquals(pdv, target.getPointsDeVie());

    }

    @Test
    void after_special_ability_electrique_should_ignore_type() {
        final int pdv = 100;
        final int atk = 20;
        Canard attacker = builder.nom("Canard").type("ELECTRIQUE").pointsDeVie(pdv).vitesse(1).pointsAttaque(atk).getCanard();
        // GIVEN we take a type that is strong against electric
        Canard target = builder.nom("Canard").type("SOL").pointsDeVie(pdv).vitesse(1).pointsAttaque(atk).getCanard();
        // WHEN we use the special ability
        SpecialEffect effect = attacker.utiliserCapaciteSpeciale();
        effect.apply(target);
        // THEN the target get normal damage even if it is strong against electric
        assertEquals(pdv - atk, target.getPointsDeVie());

    }

    @Test
    void after_special_ability_and_one_attack_electrique_should_not_ignore_type() {
        final int pdv = 100;
        final int atk = 20;
        Canard attacker = builder.nom("Canard").type("ELECTRIQUE").pointsDeVie(pdv).vitesse(1).pointsAttaque(atk).getCanard();
        // GIVEN we take a type that is strong against electric
        Canard target = builder.nom("Canard").type("SOL").pointsDeVie(pdv).vitesse(1).pointsAttaque(atk).getCanard();
        // WHEN we use the special ability
        SpecialEffect effect = attacker.utiliserCapaciteSpeciale();
        effect.apply(target);
        // THEN the target get normal damage even if it is strong against electric
        assertEquals(pdv - atk, target.getPointsDeVie());

        // WHEN we attack again
        attacker.attaquer(target);
        // THEN the multiplier should be applied
        // pdv - 20 (first attack) - 20 * 0.5 (second attack)
        assertEquals(pdv - 20 - (atk * 0.5), target.getPointsDeVie());

    }




}
