/**
 * The responsibilty for this class is to manage a single MEP
 *
 * @author  Patryk Stefanski
 * @version 4.0
 * @since   01/03/2020
 */
public class Mep {

    private String MEPName;
    private String MEPEmail;
    private String MEPPhone;
    private Party MEPParty;
    private PartyList partyList;


    public Mep(String MEPName, String MEPEmail, String MEPPhone, Party MEPParty, PartyList partyList) {
        this.MEPName = Utilities.max30Chars(MEPName);
        if (Utilities.validEmail(MEPEmail) == true) {
            this.MEPEmail = MEPEmail;
        } else this.MEPEmail = ("invalid format email. Needs to contain . and @");
        if (Utilities.onlyContainsNumbers(MEPPhone) == true) {
            this.MEPPhone = MEPPhone;
        } else this.MEPPhone = ("unknown");
        this.MEPParty = Utilities.validParty(MEPParty.getPartyName(), partyList);
        //takes in party string  and checks against partylist parties ,returns null if invalid


    }


    //getters


    public String getMEPName() {
        return MEPName;
    }

    public String getMEPPhone() {
        return MEPPhone;
    }

    public String getMEPEmail() {
        return MEPEmail;
    }

    public Party getMEPParty() {
        return MEPParty;
    }


    //setters

    public void setMEPName(String MEPName) {

        this.MEPName = Utilities.max30Chars(MEPName);
    }

    public void setMEPPhone(String MEPPhone) {
        if (Utilities.onlyContainsNumbers(MEPPhone) == true) {
            this.MEPPhone = MEPPhone;
        } else MEPPhone = ("unknown");
    }


    public void setMEPEmail(String MEPEmail) {
        if (Utilities.validEmail(MEPEmail) == true) { //has to contain . and @
            this.MEPEmail = MEPEmail;
        } else MEPEmail = ("invalid email .Email must contain . and @.");
    }

    public void setMEPParty(String MEPParty, PartyList partyList) {
        if (Utilities.validParty(MEPParty, partyList) != null) {//valid party
            for (int i = 0; i < partyList.getPartyList().size(); i++) {//cycles through parties
                if (MEPParty == partyList.getParty(i).getPartyName()) {//compares name entered to list names
                    Party party = partyList.getParty(i);
                    this.MEPParty = party;
                }
            }
        }
    }


    public String toString() {
        return "MEP Name: " + MEPName + '\''
                + ", MEP Phone: " + MEPPhone + '\''
                + ", MEP Email: " + MEPEmail + '\''
                + ", MEP Party: " + MEPParty + '\''
                ;
    }

}

