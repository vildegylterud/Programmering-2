package no.ntnu.vildegy;

public class SilverMembership extends Membership {

    private final float POINTS_SCALING_FACTOR = 1.2f;

    /**
     * Adds the new bonusPoints to the new bonusPointsBalance and accounts for the membership-perks
     * Rounds up to the closest integer
     * @param bonusPointBalance the original balance of points
     * @param newPoints the new points before adding the membership-extras
     * @return the new total sum of the bonuspoints
     */
    @Override
    public int registerPoints(int bonusPointBalance, int newPoints) {
        return Math.round(bonusPointBalance + newPoints * POINTS_SCALING_FACTOR);
    }

    /**
     *
     * @return a string with the grade/name of the membership level
     */
    @Override
    public String getMembershipName() {
        return "Silver";
    }
}
