/**
 * Created by Todd on 10/31/2016.
 */
public class House extends Residence {

    private int numFloors;
    private boolean hasGarage;

    public House (String address, int numBedrooms, int numBathrooms, int squareFootage, double monthlyRent, int numFloors, boolean hasGarage) {
        super(address, numBedrooms, numBathrooms, squareFootage, monthlyRent);

        this.numFloors = numFloors;
        this.hasGarage = hasGarage;
    }

    public int getNumFloors () {
        return numFloors;
    }

    public boolean hasGarage () {
        return hasGarage;
    }

    @Override
    public String toString () {
        String s = "";

        s += "Address: "+ getAddress() +"\n";
        s += "Beds: "+ getNumBedrooms() +"\n";
        s += "Baths: "+ getNumBathrooms() +"\n";
        s += "Sqft: "+ getSquareFootage() +"\n";
        s += "Rent: "+ getMonthlyRent() +"\n";
        s += "Floors: "+ getNumFloors() +"\n";
        s += "Garage: "+ hasGarage();

        return s;
    }
}
