/**
 * The responsibility for this class is to store
 * and manage all the countries entered by the
 * user via the console.
 *
 * @author  Patryk Stefanski
 * @version 4.0
 * @since   01/03/2020
 */
import java.util.ArrayList;

public class Country {
    private String name;
    private ArrayList<Mep> meps;
    private int noMEPs; //max amount of meps
    private Mep mep;


    public Country(String name, int noMEPs) {
        this.name = Utilities.max30Chars(name);
        if (Utilities.validIntNonNegative(noMEPs)) {
            this.noMEPs = noMEPs;
        }
        meps = new ArrayList<Mep>();
    }


    //getters
    public String getName() {
        return name;
    }

    public int getNoMEPs() {
        return noMEPs;
    }

    public ArrayList<Mep> getMeps() {
        return meps;
    }

    //setters


    public void setName(String name) {
        if (Utilities.onlyContainsNumbers(Utilities.max30Chars(name)) == false) {
            this.name = Utilities.max30Chars(name);
        }

    }

    public void setNoMEPs(int noMEPs) {
        if (Utilities.validIntNonNegative(noMEPs)) {
            this.noMEPs = noMEPs;
        }
        ;
    }

    public void setMeps(ArrayList<Mep> meps) {
        this.meps = meps;
    }

    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", meps=" + meps +
                ", noMEPs=" + noMEPs +
                ", mep=" + mep +
                '}';
    }

    public void addMEP(Mep mep) {

        if (meps.size() < noMEPs) {
            meps.add(mep);
        }
    }


    public String listOfMEPs() {
        if (meps.size() == 0) {
            return "No MEPs";
        } else {
            String listOfMEPs = "";
            for (int i = 0; i < meps.size(); i++) {
                listOfMEPs = listOfMEPs + i + ": " + meps.get(i) + "\n";
            }
            return listOfMEPs;
        }
    }

    public Mep getMEP(int i) {
        return meps.get(i);
    }

    public int numberOfMEPs() {
        return meps.size();
    }


    public boolean removeMEP(int index) {
        if ((index >= meps.size()) || (index < 0)) {
            return false;
        } else {
            meps.remove(index);
            return true;
        }
    }

    public String listOfMEPsByParty(Party party) {
        if ((meps.size() == 0)) {
            return "No MEPs";
        }
        String listOfMEPsByParty = "";
        for (int i = 0; i < meps.size(); i++) {
            if (meps.get(i).getMEPParty() == party) {
                listOfMEPsByParty = listOfMEPsByParty + i + ": " + meps.get(i) + "\n";
            }
        }
        return listOfMEPsByParty;
    }

    public int noOfMEPsByParty(Party y) {
        int total = 0;
        for (int i = 0; i < meps.size(); i++) {
            if (y.equals(meps.get(i).getMEPParty())) {
                total++;
            }
        }
        return total;
    }


}