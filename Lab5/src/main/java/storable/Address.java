package storable;

public class Address implements Comparable<Address>{

    private String zipCode;
    private Location location;

    public Address (String zipCode, Location location) {
        this.zipCode = zipCode;
        this.location = location;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public int compareTo(Address address) {
        return zipCode.compareTo(address.getZipCode());
    }
}
