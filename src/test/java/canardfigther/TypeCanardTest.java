package canardfigther;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TypeCanardTest {

    @Test
    void EauWeakAgainstVent() {
        assertEquals(0.5, TypeCanard.getMultiplicateur(TypeCanard.EAU, TypeCanard.VENT));
    }

    @Test
    void VentStrongAgainstEau() {
        assertEquals(1.5, TypeCanard.getMultiplicateur(TypeCanard.VENT, TypeCanard.EAU));
    }

    @Test
    void NoRelation() {
        assertEquals(1, TypeCanard.getMultiplicateur(TypeCanard.VENT, TypeCanard.FEU));
    }

}
