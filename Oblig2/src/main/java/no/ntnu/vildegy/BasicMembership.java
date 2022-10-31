package no.ntnu.vildegy;

public class BasicMembership extends Membership {

    /**
     * Adds the new bonusPoints to the new bonusPointsBalance and accounts for the membership-perks
     * Rounds up to the closest integer
     * @param bonusPointBalance the original balance of points
     * @param newPoints the new points before adding the original sum of bonusPointsBalance
     * @return the new total sum of the bonuspoints
     */
    @Override
    public int registerPoints(int bonusPointBalance, int newPoints) {
        return bonusPointBalance + newPoints;
    }

    /**
     *
     * @return a string with the grade/name of the membership level
     */
    @Override
    public String getMembershipName() {
        return "Basic";
    }
}
