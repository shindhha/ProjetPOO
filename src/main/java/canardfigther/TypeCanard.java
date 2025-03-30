package canardfigther;

import java.util.Arrays;

public enum TypeCanard {

    EAU(1, 6),
    FEU(2, 5),
    GLACE(3, 6),
    VENT(0, 5),
    ELECTRIQUE(3, 0),
    TOXIQUE(2, 0),
    SOL(1, 4);



    private Integer[] strongAgainst;

    TypeCanard(Integer... strongAgainst){
        this.strongAgainst = strongAgainst;
    }


    private static final double ADVANTAGE_MULTIPLIER = 1.5;
    private static final double DISADVANTAGE_MULTIPLIER = 0.5;
    private static final double NEUTRAL_MULTIPLIER = 1.0;

    /**
     * Get the multiplier for the damage calculation
     * We use the attributes of the enum to determine the multiplier
     * @param attackerType type of the attacker
     * @param defenderType type of the defender
     * @return the multiplier
     */
    public static double getMultiplicateur(TypeCanard attackerType, TypeCanard defenderType) {

        if (Arrays.asList(attackerType.strongAgainst).contains(defenderType.ordinal())) {
            return ADVANTAGE_MULTIPLIER;
        }
        if (Arrays.asList(defenderType.strongAgainst).contains(attackerType.ordinal())) {
            return DISADVANTAGE_MULTIPLIER;
        }
        return NEUTRAL_MULTIPLIER;
    }

    public double getMuliplierAgainst(TypeCanard defenderType) {
        return getMultiplicateur(this, defenderType);
    }
}
