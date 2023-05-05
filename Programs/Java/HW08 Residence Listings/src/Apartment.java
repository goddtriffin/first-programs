/**
 * Created by Todd on 10/31/2016.
 */
public class Apartment extends Residence {

    private int floorNumber;

    public Apartment (String address, int numBedrooms, int numBathrooms, int squareFootage, double monthlyRent, int floorNumber) {
        super(address, numBedrooms, numBathrooms, squareFootage, monthlyRent);

        this.floorNumber = floorNumber;
    }

    public int getFloorNumber () {
        return floorNumber;
    }

    @Override
    public String toString () {
        String s = "";

        s += "Address: "+ getAddress() +"\n";
        s += "Beds: "+ getNumBedrooms() +"\n";
        s += "Baths: "+ getNumBathrooms() +"\n";
        s += "Sqft: "+ getSquareFootage() +"\n";
        s += "Rent: "+ getMonthlyRent() +"\n";
        s += "Floor: "+ getFloorNumber();

        return s;
    }
}
