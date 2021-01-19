/**
 * The responsibility of this class is to manage
 * a single party
 *
 * @author  Patryk Stefanski
 * @version 4.0
 * @since   01/03/2020
 */
public class Party {
    private String partyName;
    private String partyLeader;
    private String partyGenre;
    private int numLocalReps;

    public Party(String partyName, String partyLeader, String partyGenre, int numLocalReps) {
        this.partyName = Utilities.max30Chars(partyName);
        this.partyLeader = Utilities.max30Chars(partyLeader);
        this.partyGenre = Utilities.validGenre(partyGenre);//checks against list of genres
        if (Utilities.validIntNonNegative(numLocalReps)) {
            this.numLocalReps = numLocalReps;
        }
    }

    //getters
    public String getPartyName() {
        return partyName;
    }

    public String getPartyLeader() {
        return partyLeader;
    }

    public String getPartyGenre() {
        return partyGenre;
    }

    public int getNumLocalReps() {
        return numLocalReps;
    }

    //setters
    public void setPartyName(String partyName) {
        this.partyName = Utilities.max30Chars(partyName);
    }

    public void setPartyLeader(String partyLeader) {
        this.partyLeader = Utilities.max30Chars(partyLeader);
    }

    public void setPartyGenre(String genre) {
        genre = genre.toUpperCase();
        if (Utilities.validGenre(genre).equals(genre)) {
            this.partyGenre = Utilities.validGenre(genre);
        }
    }

    public void setNumLocalReps(int numLocalReps) {
        if (Utilities.validIntNonNegative(numLocalReps)) {
            this.numLocalReps = numLocalReps;
        }
    }

    public String toString() {
        return "Party{" +
                "partyName='" + partyName + '\'' +
                ", partyLeader='" + partyLeader + '\'' +
                ", partyGenre='" + partyGenre + '\'' +
                ", numLocalReps=" + numLocalReps +
                '}';
    }
}