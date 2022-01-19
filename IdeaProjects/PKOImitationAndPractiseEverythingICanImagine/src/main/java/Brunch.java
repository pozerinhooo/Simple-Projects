public class Brunch {
    private String adress;
    private String brunchNumber;
    private String phoneContactNumber;
    private String cityLocation;

    public Brunch(String adress, String brunchNumber, String phoneContactNumber, String cityLocation) {
        this.adress = adress;
        this.brunchNumber = brunchNumber;
        this.phoneContactNumber = phoneContactNumber;
        this.cityLocation = cityLocation;
    }

    @Override
    public String toString() {
        return "Brunch{" +
                "adress='" + adress + '\'' +
                ", brunchNumber=" + brunchNumber +
                ", phoneContactNumber='" + phoneContactNumber + '\'' +
                ", cityLocation='" + cityLocation + '\'' +
                '}';
    }

    public String getAdress() {
        return adress;
    }

    public String getBrunchNumber() {
        return brunchNumber;
    }

    public String getPhoneContactNumber() {
        return phoneContactNumber;
    }

    public String getCityLocation() {
        return cityLocation;
    }
}
