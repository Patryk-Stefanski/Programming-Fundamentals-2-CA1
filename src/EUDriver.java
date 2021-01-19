/**
 * The EUApp program implements an application that
 * manages the data about the european union  and its
 * MEPs,country's and parties
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
import java.util.Scanner;

public class EUDriver {
    private Scanner input = new Scanner(System.in);
    private ArrayList<Country> euCountries;
    private PartyList partyList = new PartyList();


    public EUDriver() {
        euCountries = new ArrayList<Country>();

        runMenu();
    }

    public static void main(String[] args) {
        new EUDriver();
    }

    public EUDriver(int i) {
        euCountries = new ArrayList<Country>();
    }

    private int mainMenu() {
        System.out.println("EU Menu");
        System.out.println("---------");
        System.out.println("  1) Add a country to EU");
        System.out.println("  2) Remove  a country from EU");
        System.out.println("  3) Update a EU countries information");
        System.out.println("  4 )list all EU Countries");
        System.out.println("  --------------------");
        System.out.println("  Country Menu");
        System.out.println("  5) Add an MEP");
        System.out.println("  6) Remove a MEP");
        System.out.println("  7) Update the information on a MEP");
        System.out.println("  8) List all MEPs in this country");
        System.out.println("  --------------------");
        System.out.println("  Party Menu");
        System.out.println("  9) Add a new party");
        System.out.println("  10) Remove a party");
        System.out.println("  11)Update the part information");
        System.out.println("  12) List all Parties");
        System.out.println("  --------------------");
        System.out.println("  Report Menu");
        System.out.println("  13) Print all the parties in the EU");
        System.out.println("  14)Calculate and print the party with the ost local representatives");
        System.out.println("  15)Calculate and print the party with the most MEPs");
        System.out.println("  16)List all parties of a given genre");
        System.out.println("  17)List all MEPs of a given party");
        System.out.println("  --------------------");
        System.out.println("  20)Save to XML");
        System.out.println("  21)Load from XML");
        System.out.println("  --------------------");
        System.out.println("  0)Exit");
        System.out.print("==>> ");
        int option = input.nextInt();
        return option;
    }

    private void runMenu() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1:
                    addCountry();
                    break;
                case 2:
                    deleteCountry();
                    break;
                case 3:
                    updateCountry();
                    break;
                case 4:
                    System.out.print(listCountries());
                    break;
                case 5:
                    addMEP();
                    break;
                case 6:
                    deleteMEP();
                    break;
                case 7:
                    updateMEP();
                    break;
                case 8:
                    listMEPSOfCountry();
                    break;
                case 9:
                    addParty();
                    break;
                case 10:
                    deleteParty();
                    break;
                case 11:
                    updateParty();
                    break;
                case 12:
                    System.out.print(listOfParties());
                    break;
                case 13:
                    System.out.print(listOfPartiesInEu());
                    break;
                case 14:
                    System.out.print(largestParty());
                    break;
                case 15:
                    System.out.print(mostMEPs());
                    break;
                case 16:
                    System.out.print(listPartyByGenre());

                    break;
                case 17:
                    System.out.print(listMEPsbyPartyName());
                    break;
                case 20:
                    try {
                        savePartyList();
                        saveCountries();
                    } catch (Exception e) {
                        System.err.println("Error writing to file: " + e);
                    }
                    break;
                case 21:
                    try {
                        loadPartyList();
                        loadCountries();
                    } catch (Exception e) {
                        System.err.println("Error reading from file: " + e);
                    }
                    break;

                default:
                    System.out.println("Invalid option selected.");
                    break;
            }
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();

            System.out.println("");
            option = mainMenu();
        }
        System.out.println("Exiting... bye");
    }


    //getters
    public PartyList getPartyList() {
        return partyList;
    }

    public ArrayList<Country> getEuCountries() {
        return euCountries;
    }

    //setters
    public void setPartyList(PartyList partyList) {
        this.partyList = partyList;
    }

    public void setEuCountries(ArrayList<Country> euCountries) {
        this.euCountries = euCountries;
    }

    //EU Menu

    //country menu this menu adds to ,deletes from ,displays and edits the country list
    private void addCountry() {
        input.nextLine();
        System.out.print("Enter the Country Name:  ");
        String name = input.nextLine();
        System.out.print(" Enter Number of seats:  ");
        int noMEPs = input.nextInt();


        euCountries.add(new Country(name, noMEPs));
    }

    private void deleteCountry() {
        System.out.print(listCountries());

        if (euCountries.size() > 0) {
            System.out.println("Enter the index of the country to delete ==>");
            int index = input.nextInt();
            if ((index >= 0) && (index < euCountries.size())) {

                euCountries.remove(index);
                System.out.println("Country deleted");
            } else {
                System.out.println("There is no Country for this index number");
            }
        }

    }

    private void updateCountry() {
        System.out.println(listCountries());

        if (euCountries.size() > 0) {
            System.out.print("enter the index od the Country to update==>");
            int index = input.nextInt();
            if ((index >= 0) && (index < euCountries.size())) {
                input.nextLine();
                System.out.println("enter Country name");
                String name = input.nextLine();
                System.out.println("enter the number of MEPs");
                int noMEPs = input.nextInt();


                Country country = euCountries.get(index);
                country.setName(name);
                country.setNoMEPs(noMEPs);
            } else {
                System.out.println("There Country is not recognised");
            }
        }
    }


    public String listCountries() {
        if (euCountries.size() == 0) {
            return "No country's";
        } else {
            String listOfCountries = "";
            for (int i = 0; i < euCountries.size(); i++) {
                listOfCountries = listOfCountries + i + ": " + euCountries.get(i).getName() + "\n";
            }
            return listOfCountries;
        }
    }

    //MEP menu this menu adds to deletes from ,display and edits the meps list

    private void addMEP() {
        System.out.print(listCountries());
        System.out.print("Please enter Index of the country to which you would like to add the mep");

        int countryChosen = input.nextInt();
        input.nextLine();
        System.out.print("Enter the Meps Name:  ");
        String MEPName = input.nextLine();
        System.out.print("Enter Meps email:  ");
        String MEPEmail = input.nextLine();
        System.out.println("Enter Meps phone number");
        String MEPPhone = input.nextLine();
        System.out.print("Enter  MEPs party");
        String MepParty = input.nextLine();


        Party MEPParty = Utilities.validParty(MepParty, partyList);

        euCountries.get(countryChosen).addMEP(new Mep(MEPName, MEPEmail, MEPPhone, MEPParty, partyList));
    }

    private void deleteMEP() {
        System.out.print("Please enter Index of the country of which you would like to delete the mep");
        System.out.print(listCountries());
        int countryChosen = input.nextInt();
        System.out.println(euCountries.get(countryChosen).listOfMEPs());

        if (euCountries.get(countryChosen).numberOfMEPs() > 0) {
            System.out.println("Enter the index of the MEP to delete ==>");
            int index = input.nextInt();
            if ((index >= 0) && (index < euCountries.get(countryChosen).numberOfMEPs())) {

                euCountries.get(countryChosen).removeMEP(index);
                System.out.println("MEP deleted");
            } else {
                System.out.println("There is no MEP for this index number");
            }
        }
    }


    private void updateMEP() {
        System.out.print("Enter the index of the Country to update==>");
        System.out.print(listCountries());
        int countryChosen = input.nextInt();


        if (euCountries.size() > 0) {
            if ((countryChosen >= 0) && (countryChosen < euCountries.size())) {
                if (euCountries.get(countryChosen).getNoMEPs() > 0) {
                    System.out.print(euCountries.get(countryChosen).listOfMEPs());
                    System.out.print("Enter the index of the MEP to update==>");
                    int mepIndex = input.nextInt();
                    if ((mepIndex >= 0) && (mepIndex < euCountries.get(countryChosen).getNoMEPs())) {
                        input.nextLine();
                        System.out.print("Enter MEP name");
                        String mepName = input.nextLine();
                        System.out.print("Enter MEPs email");
                        String mepEmail = input.nextLine();
                        System.out.print("Enter MEPs phone number");
                        String mepPhone = input.nextLine();
                        System.out.println(partyList.listOfParties());
                        System.out.print("Enter  MEPs party ");
                        String mepParty = input.nextLine();


                        Mep mep = euCountries.get(countryChosen).getMEP(mepIndex);
                        euCountries.get(countryChosen).getMEP(mepIndex).setMEPName(mepName);
                        euCountries.get(countryChosen).getMEP(mepIndex).setMEPEmail(mepEmail);
                        euCountries.get(countryChosen).getMEP(mepIndex).setMEPParty(mepParty, partyList);
                        euCountries.get(countryChosen).getMEP(mepIndex).setMEPPhone(mepPhone);

                    }


                }

            } else {
                System.out.println("There is no MEP for this index");
            }
        }
    }

    private void listMEPSOfCountry() {
        System.out.println("Please choose a country");
        System.out.println(listCountries());
        int chosenCountry = input.nextInt();

        if ((euCountries.get(chosenCountry).numberOfMEPs() == 0)) {
            System.out.print("no Meps");
        } else {
            String listOfMEPsByCountry = "";
            for (int i = 0; i < euCountries.get(chosenCountry).numberOfMEPs(); i++) {

                listOfMEPsByCountry = listOfMEPsByCountry + i + ": " + euCountries.get(chosenCountry).getMEP(i).getMEPName() + "\n";
            }

            System.out.println(listOfMEPsByCountry);
        }
    }

    //Party this menu adds to ,deletes from,displays and edits the partylist

    public void addParty() {

        input.nextLine();
        System.out.print("Enter the Party Name:  ");
        String partyName = input.nextLine();
        System.out.print("Enter Party Leader:  ");
        String partyLeader = input.nextLine();
        System.out.println("Enter Party Genre");
        String partyGenre = input.nextLine();
        System.out.print("enter  Partys number of local reps:");
        int numLocalReps = input.nextInt();

        partyList.addParty(new Party(partyName, partyLeader, partyGenre, numLocalReps));


    }


    public void deleteParty() {
        System.out.println(partyList.listOfParties());

        if (partyList.numberOfParties() > 0) {
            System.out.println("Enter the index of the Party to delete ==>");
            int index = input.nextInt();
            if ((index >= 0) && (index < partyList.numberOfParties())) {

                partyList.removeParty(index);
                System.out.println("Party deleted");
            } else {
                System.out.println("There is no Party for this index number");
            }
        }

    }

    private void updateParty() {
        System.out.println(partyList.listOfParties());

        if (partyList.numberOfParties() > 0) {
            System.out.print("enter the index of the Party to update==>");
            int index = input.nextInt();
            if ((index >= 0) && (index < partyList.numberOfParties())) {
                System.out.print("enter Party name");
                String partyName = input.nextLine();
                System.out.print("enter Party Leader");
                String partyLeader = input.nextLine();
                System.out.print("enter Party Genre");
                String partyGenre = input.nextLine();
                System.out.println("enter number of local reps");
                int numLocalReps = input.nextInt();


                Party party = partyList.getParty(index);
                party.setPartyName(partyName);
                party.setPartyLeader(partyLeader);
                party.setPartyGenre(partyGenre);
                party.setNumLocalReps(numLocalReps);
            }
        } else {
            System.out.println("There is no Party for this index");
        }
    }

    private String listOfParties() {
        if (partyList.getPartyList().size() == 0) {
            return "No Parties";
        } else {
            String listOfParties = "";
            for (int i = 0; i < partyList.getPartyList().size(); i++) {
                listOfParties = listOfParties + i + ": " + partyList.getPartyList().get(i) + "\n";
            }
            return listOfParties;
        }

    }

    //Report menu  this calculates  the party with the most meps or local reps
    //and list parties of a given genre or meps of a give party

    private String listOfPartiesInEu() {
        if (partyList.numberOfParties() == 0) {
            return "no Parties";
        } else {
            String listOfPartiesInEu = "";
            for (int i = 0; i < partyList.numberOfParties(); i++) {
                if (Utilities.validParty(partyList.getParty(i).getPartyName(), partyList) != null)
                    listOfPartiesInEu = listOfPartiesInEu + i + ": " + partyList.getParty(i) + "\n";
            }
            return listOfPartiesInEu;

        }
    }


    //Reporting Menu
    private String largestParty() {
        return partyList.largestParty().toString();
    }

    private String mostMEPs() {
        return partyList.mostMEPs(euCountries).toString();
    }

    private String listPartyByGenre() {
        input.nextLine();
        System.out.println("Please enter a party genre");
        String genre = input.nextLine();
        return partyList.listPartiesBySpecificGenre(genre);
    }


    private String listMEPsbyPartyName() {
        input.nextLine();
        System.out.println("Please enter party name");
        String partyName = input.nextLine();


        return listMEPsbySpecificParty(partyName);


    }

    public String listMEPsbySpecificParty(String s) {
        String listOfMEPsBySpecificParty = "";
        if ((partyList.getPartyList().size() == 0) || (Utilities.validParty(s, partyList) == null)) {
            listOfMEPsBySpecificParty = "There are no meps";
        } else {
            for (int o = 0; o < euCountries.size(); o++) {
                for (int i = 0; i < partyList.getPartyList().size(); i++) {
                    if (partyList.getParty(i).getPartyName().equals(s)) {
                        listOfMEPsBySpecificParty = listOfMEPsBySpecificParty  + ":" + euCountries.get(0).getMEP(i).getMEPName() + "\n";
                    }
                }
            }
        }
        return listOfMEPsBySpecificParty;
    }

   //loads and saves data entered about countries ,parties and meps
    public void loadCountries() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("euCountries.xml"));
        euCountries = (ArrayList<Country>) is.readObject();
        is.close();
    }

    public void saveCountries() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("euCountries.xml"));
        out.writeObject(euCountries);
        out.close();
    }

    public void savePartyList() throws Exception {
        partyList.save();
    }

    public void loadPartyList() throws Exception {
        partyList.load();
    }

    public int noMEPSBySpecificParty(String x) {
        int total = 0;
        for (int i = 0; i < euCountries.size(); i++) {
            for (int o = 0; o < partyList.getPartyList().size(); o++) {
                if (partyList.getParty(o).getPartyName().equals(x)) {

                    total = total + euCountries.get(i).noOfMEPsByParty(partyList.getParty(o));
                }
            }
        }
        return total;
    }


    public Country findCountry(String y) {
        for (Country country : euCountries) { //cycles through countries if country name is the same as string
            if (country.getName().equals(y)) { //it returns that country
                return country;
            }
        }
        return null;

    }
}

