/**
 * Created by Todd on 10/31/2016.
 */
public class ResidenceListings {

    private int numResidences;
    private int maxResidences;
    private Residence[] residences;

    public ResidenceListings () {
        numResidences = 0;
        maxResidences = 10;
        residences = new Residence[maxResidences];
    }

    public void addResidence (Residence residence) {
        while (true) {
            for (int i=0; i<maxResidences; i++) {
                if (residences[i] == null) {
                    residences[i] = residence;
                    numResidences++;
                    return;
                }
            }

            resizeResidences();
        }
    }

    public void removeResidence (Residence residence) throws NoSuchResidenceException {
        for (int i=0; i<residences.length; i++) {
            if (residences[i] == residence) {
                residences[i] = null;
                shiftResidenceElements(i);
                numResidences--;
                return;
            }
        }

        throw new NoSuchResidenceException("Could not find given residence.");
    }

    public Residence findResidenceByAddress (String address) {
        for (int i=0; i<residences.length; i++) {
            if (residences[i] != null && residences[i].getAddress().equals(address)) {
                return residences[i];
            }
        }

        return null;
    }

    public int getNumResidences () {
        return numResidences;
    }

    public int getMaxResidences () {
        return maxResidences;
    }

    public Residence[] getResidences () {
        return residences;
    }

    private void resizeResidences () {
        maxResidences *= 2;

        Residence[] temp = new Residence[maxResidences];

        for (int i=0; i<residences.length; i++) {
            temp[i] = residences[i];
        }

        residences = temp;
    }

    private void shiftResidenceElements (int initLoc) {
        for (int i=initLoc + 1; i<residences.length; i++) {
            if (residences[i] != null) {
                residences[i - 1] = residences[i];
                residences[i] = null;
            } else {
                break;
            }
        }
    }
}
