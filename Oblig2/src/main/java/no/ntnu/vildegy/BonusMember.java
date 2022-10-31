package no.ntnu.vildegy;

/**
 * Lagde getmetoder til alle variablene utenom gold-og silverlimit, selv om disse ikke sto i oppgitt klassediagram.
 *
 */

import java.time.LocalDate;


public class BonusMember {

    private int memberNumber;
    private LocalDate enrolledDate;
    private int bonusPointsBalance = 0;
    private String name;
    private String eMailAddress;
    private String password;

    private Membership membership;

    private static final int SILVER_LIMIT = 25000;
    private static final int GOLD_LIMIT = 75000;

    /**
     * Constructor
     * @param memberNumber
     * @param enrolledDate
     * @param bonusPointsBalance
     * @param name
     * @param eMailAddress
     */
    public BonusMember(int memberNumber, LocalDate enrolledDate, int bonusPointsBalance, String name, String eMailAddress) {
        this.memberNumber = memberNumber;
        this.enrolledDate = enrolledDate;
        this.bonusPointsBalance = bonusPointsBalance;
        this.name = name;
        this.eMailAddress = eMailAddress;

        checkAndSetMembership();
    }

    //Getters

    public String getName() {
        return name;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getEnrolledDate() {
        return enrolledDate;
    }

    public int getBonusPointsBalance() {
        return bonusPointsBalance;
    }

    public String getMailAddress() {
        return eMailAddress;
    }


    public Membership getMembershipLevel() {
        return membership;
    }

    //Methods

    /**
     *
     * @param password
     * @return false if the password does not match, en true if it match
     */
    public boolean checkPassword(String password) {
        if(password.equals(this.password)) {
            return true;
        }
        return false;
    }

    /**
     * The method register the new bonus point balance. The new bonus points will be registered with the
     * right membership-bonus
     * @param newPoints the new value of bonus points
     */
    public void registerBonusPoints(int newPoints) {
        // bruker metoden registerPoints fra membership klassen (gjør at gullmeldem får mer bonus enn eks sølvmedlem)
        this.bonusPointsBalance = membership.registerPoints(this.bonusPointsBalance, newPoints);
        //bruker metoden check and set membership for å sjekke brukerens memebership level
        checkAndSetMembership(); //
    }

    /**
     * the method checks the level/grade of the membership and sets the membership on the right level/grade
     */
    public void checkAndSetMembership() {
        if(bonusPointsBalance < SILVER_LIMIT) {
            membership = new BasicMembership();
        } else if(bonusPointsBalance >= SILVER_LIMIT && bonusPointsBalance < GOLD_LIMIT) {
            membership = new SilverMembership();
        } else {
            membership = new GoldMembership();
        }
    }

    /**
     *
     * @return a String with all the information of the membership
     */
    @Override
    public String toString() {
        return "Information of the membership: " + "\n"  +
                "MemberNumber: " + memberNumber + "\n"  +
                "Membership: " + membership.getMembershipName() + "\n" +
                "EnrolledDate: " + enrolledDate + "\n" +
                "BonusPointsBalance: " + bonusPointsBalance + "\n" +
                "Name: '" + name + "\n"  +
                "EMailAddress: '" + eMailAddress + "\n";
    }
}