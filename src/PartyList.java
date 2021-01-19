/**
 * The responsibility for this class is to store and manage
 * aLL the parties entered by the user via the console
 * (that are parties in the EU).
 *
 * @author  Patryk Stefanski
 * @version 4.0
 * @since   01/03/2020
 */
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PartyList {
    private ArrayList<Party> parties;
    private Utilities utilities;


    public PartyList() {
        parties = new ArrayList<Party>();
    }

    //getters


    public ArrayList<Party> getPartyList() {
        return parties;
    }


    public Party getParty(int i) {
        if (utilities.validIndex(i, parties) == true) {
            return parties.get(i);
        } else {
            return null;
        }
    }

    //setters

    public void setParties(ArrayList<Party> parties) {
        this.parties = parties;
    }


    public void addParty(Party party) {
        parties.add(party);
    }


    public String toString() {
        return "PartyList{" +
                "parties=" + parties +
                '}';
    }

    public boolean removeParty(int index) {
        if (utilities.validIndex(index, parties) == false) {
            return false;
        } else {
            parties.remove(index);
            return true;
        }
    }

    public int numberOfParties() {
        return parties.size();
    }

    public String listOfParties() {
        if (parties.size() == 0) {
            return "no parties in the list";
        } else {
            String listParties = "";
            for (int i = 0; i < parties.size(); i++) {
                listParties = listParties + (i + ":" + parties.get(i)) + "\n";
            }
            return listParties;
        }
    }


    public String listPartiesBySpecificGenre(String genre) {
        if (parties.size() == 0) {
            return "no parties";
        }

        String listParties = "";
        for (int i = 0; i < parties.size(); i++) { //cycles through parties
            if ((parties.get(i).getPartyGenre().equals(Utilities.validGenre(genre)))) {//gets party genre and checks against list
                listParties = listParties + (i + ":" + parties.get(i) + "\n"); //if party has the  same genre as entered
                                                                              // it adds it to the list
            }
        }

        if (listParties.isEmpty()) {
            return "no parties with the genre";
        }
        return listParties;
    }

    public Party largestParty() { //returns party with most local reps
        Party largestParty = getParty(0);
        for (int i = 0; i < parties.size(); i++) {
            if (getParty(i).getNumLocalReps() > largestParty.getNumLocalReps()) {
                largestParty = getParty(i);
            }
        }
        return largestParty;
    }

    public Party mostMEPs(ArrayList<Country> euCountries) { //returns party with most meps

        Party mostMEPs = parties.get(0);
        int mostMEPsInEU = euCountries.get(0).noOfMEPsByParty(parties.get(0));
        for (int o = 0; o < parties.size(); o++) {
            int totalMepsByParty = 0;
            for (int i = 0; i < euCountries.size(); i++) {
                totalMepsByParty += euCountries.get(i).noOfMEPsByParty(parties.get(o));
            }
//running total
            if (totalMepsByParty > mostMEPsInEU) {
                mostMEPsInEU = totalMepsByParty;
                mostMEPs = parties.get(o);
            }

        }
        return mostMEPs;
    }


    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("partyList.xml"));
        parties = (ArrayList<Party>) is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("partyList.xml"));
        out.writeObject(parties);
        out.close();
    }


}

