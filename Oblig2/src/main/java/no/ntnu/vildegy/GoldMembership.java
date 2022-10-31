package no.ntnu.vildegy;

public class GoldMembership extends Membership {

    private final float POINT_SCALING_FACTOR_LEVEL_1 = 1.3f;
    private final float POINT_SCALING_FACTOR_LEVEL_2 = 1.5f;

    /**
     * Adds the new bonusPoints to the new bonusPointsBalance and accounts for the membership-perks
     * Rounds up to the closest integer
     * Adding the membership extras, the point scaling level depends on whether the bonusPointsBalacne is <90000 or >=90000
     * @param bonusPointBalance the original balance of points
     * @param newPoints the new points before adding the membership-extras
     * @return the new total sum of the bonuspoints
     */
    @Override
    public int registerPoints(int bonusPointBalance, int newPoints) {
        if (bonusPointBalance < 90000) {
            return Math.round(bonusPointBalance + newPoints * POINT_SCALING_FACTOR_LEVEL_1);
        }

        return Math.round(bonusPointBalance + newPoints * POINT_SCALING_FACTOR_LEVEL_2);

    }

    /**
     *
     * @return a string with the grade/name of the membership level
     */
    @Override
    public String getMembershipName() {
        return "Gold";
    }
}
