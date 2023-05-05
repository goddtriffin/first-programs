import javax.swing.*;

/**
 * Driver for the ResidenceListings inheritance homework assignment.
 * You must complete the 3 missing code blocks, marked by the TODO
 * comments below.
 */
public class ResidenceListingsDriver {
    public static void main(String[] args) {
        ResidenceListings rl = new ResidenceListings();
        JOptionPane.showMessageDialog(null, "Welcome to the Residence Finder!");
        String[] mainMenuSelections = {"Post a listing", "Find a listing", "Remove a listing"};
        String[] residenceTypePostSelections = {"Apartment", "House"};
        String[] residenceTypeFindSelections = {"Apartments", "Houses", "All"};
        String[] doneNextButtons = {"Done", "Next"};
        String mainMenuSelection;

        do {
            mainMenuSelection = (String) JOptionPane.showInputDialog(null, "What would you like to do?",
                    "Main Menu", JOptionPane.PLAIN_MESSAGE, null, mainMenuSelections, null);
            if (mainMenuSelection == null) {
                JOptionPane.showMessageDialog(null, "Thanks for using the Residence Finder!");
            } else if (mainMenuSelection.equals(mainMenuSelections[0])) {
                String postSelection = (String) JOptionPane.showInputDialog(null, "Type of residence",
                        "Post a Listing", JOptionPane.PLAIN_MESSAGE, null, residenceTypePostSelections, null);
                if (postSelection == null) {
                    continue;
                }
                String address = JOptionPane.showInputDialog(null, "Address?",
                        "Post a Listing", JOptionPane.PLAIN_MESSAGE);
                int numBedrooms = Integer.parseInt(JOptionPane.showInputDialog(null, "Number of bedrooms?",
                        "Post a Listing", JOptionPane.PLAIN_MESSAGE));
                int numBathrooms = Integer.parseInt(JOptionPane.showInputDialog(null, "Number of bathrooms?",
                        "Post a Listing", JOptionPane.PLAIN_MESSAGE));
                int squareFootage = Integer.parseInt(JOptionPane.showInputDialog(null, "Size in square feet?",
                        "Post a Listing", JOptionPane.PLAIN_MESSAGE));
                double monthlyRent = Double.parseDouble(JOptionPane.showInputDialog(null, "Monthly rent?",
                        "Post a Listing", JOptionPane.PLAIN_MESSAGE));

                if (postSelection.equals(residenceTypePostSelections[0])) {
                    int floorNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Floor number?",
                            "Post a Listing", JOptionPane.PLAIN_MESSAGE));
                    Residence residence = new Apartment(address, numBedrooms, numBathrooms, squareFootage, monthlyRent,
                            floorNumber);
                    rl.addResidence(residence);
                    JOptionPane.showMessageDialog(null, String.format("New apartment listing added!%n%s", residence));
                } else if (postSelection.equals(residenceTypePostSelections[1])) {
                    int numFloors = Integer.parseInt(JOptionPane.showInputDialog(null, "Number of floors?",
                            "Post a Listing", JOptionPane.PLAIN_MESSAGE));
                    boolean hasGarage = JOptionPane.showConfirmDialog(null, "Does it have a garage?",
                            "Post a Listing", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    Residence residence = new House(address, numBedrooms, numBathrooms, squareFootage, monthlyRent,
                            numFloors, hasGarage);
                    rl.addResidence(residence);
                    JOptionPane.showMessageDialog(null, String.format("New house listing added!%n%s", residence));
                }
            } else if (mainMenuSelection.equals(mainMenuSelections[1])) {
                String residenceType = (String) JOptionPane.showInputDialog(null, "Type of residence",
                        "Find a Listing", JOptionPane.PLAIN_MESSAGE, null, residenceTypeFindSelections, null);
                if (residenceType == null) {
                    continue;
                }
                if (residenceType.equals(residenceTypeFindSelections[0])) {
                    if (rl.getNumResidences() == 0) {
                        JOptionPane.showMessageDialog(null, "There are no apartments to show.");
                        continue;
                    }
                    /* TODO: Iterate through the 'Residence[] residences' of ResidenceListings.
                     * TODO: Display an OptionDialog window for any of the elements that are Apartments.
                     * TODO: The dialog will display all information about the Apartment and give the user
                     * TODO: two choices: "Done" to stop showing listings, "Next" to view the next Apartment listing*/

                    showListing(getApartments(rl.getResidences()));

                    JOptionPane.showMessageDialog(null, "End of listings.");
                } else if (residenceType.equals(residenceTypeFindSelections[1])) {
                    if (rl.getNumResidences() == 0) {
                        JOptionPane.showMessageDialog(null, "There are no houses to show.");
                        continue;
                    }
                    /* TODO: Iterate through the 'Residence[] residences' of ResidenceListings.
                     * TODO: Display an OptionDialog window for any of the elements that are Houses.
                     * TODO: The dialog will display all information about the House and give the user
                     * TODO: two choices: "Done" to stop showing listings, "Next" to view the next House listing*/

                    showListing(getHouses(rl.getResidences()));

                    JOptionPane.showMessageDialog(null, "End of listings.");
                } else if (residenceType.equals(residenceTypeFindSelections[2])) {
                    if (rl.getNumResidences() == 0) {
                        JOptionPane.showMessageDialog(null, "There are no residences to show.");
                        continue;
                    }
                    /* TODO: Iterate through the 'Residence[] residences' of ResidenceListings.
                     * TODO: Display an OptionDialog window for all elements, both Apartments and Houses.
                     * TODO: The dialog will display all information about the Residence and give the user
                     * TODO: two choices: "Done" to stop showing listings, "Next" to view the next Residence listing*/

                    showListing(getAllResidences(rl.getResidences()));

                    JOptionPane.showMessageDialog(null, "End of listings.");
                }
            } else if (mainMenuSelection.equals(mainMenuSelections[2])) {
                String residenceAddress = JOptionPane.showInputDialog(null, "Address of residence to remove?",
                        "Remove a Residence", JOptionPane.PLAIN_MESSAGE);
                Residence residence = rl.findResidenceByAddress(residenceAddress);
                if (residence == null) {
                    JOptionPane.showMessageDialog(null, "Residence was not found.");
                } else {
                    try {
                        rl.removeResidence(residence);
                        JOptionPane.showMessageDialog(null, "Residence removed!");
                    } catch (NoSuchResidenceException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }
            }
        } while(mainMenuSelection != null);
    }

    private static Residence[] getApartments (Residence[] listings) {
        Residence[] apartments = new Residence[0];

        for (int i=0; i<listings.length; i++) {
            if (listings[i] instanceof Apartment) {
                apartments = appendToArray(apartments, listings[i]);
            }
        }

        return apartments;
    }

    private static Residence[] getHouses (Residence[] listings) {
        Residence[] houses = new Residence[0];

        for (int i=0; i<listings.length; i++) {
            if (listings[i] instanceof House) {
                houses = appendToArray(houses, listings[i]);
            }
        }

        return houses;
    }

    private static Residence[] getAllResidences (Residence[] listings) {
        Residence[] apartments = getApartments(listings);
        Residence[] houses = getHouses(listings);

        Residence[] all = new Residence[apartments.length + houses.length];

        for (int i=0; i<apartments.length; i++) {
            all[i] = apartments[i];
        }

        for (int i=apartments.length; i<apartments.length + houses.length; i++) {
            all[i] = houses[i - apartments.length];
        }

        return all;
    }

    private static void showListing (Residence[] residences) {
        if (residences.length == 0) {
            return;
        }

        String[] buttons = {"Done", "Next"};
        int selection = 0;

        boolean done = false;
        while (!done) {
            int choice = JOptionPane.showOptionDialog(null, residences[selection].toString(), "Find a Listing", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[1]);

            if (choice == 0) { //done clicked
                done = true;
            } else if (choice == 1) { //next clicked
                selection++;
            }

            if (selection >= residences.length - 1) { //no more selections left
                done = true;
            }
        }
    }

    private static Residence[] appendToArray (Residence[] residences, Residence residence) {
        Residence[] temp = new Residence[residences.length + 1];

        for (int i=0; i<residences.length; i++) {
            temp[i] = residences[i];
        }

        temp[temp.length - 1] = residence;

        return temp;
    }
}
