package demo.model;

public class BuchungsStatus {

    private boolean storniert;
    private String buchungsNummerHotel;
    private String buchungsNummerFlug;

    public boolean isStorniert() {
        return storniert;
    }

    public void setStorniert(boolean storniert) {
        this.storniert = storniert;
    }

    public String getBuchungsNummerHotel() {
        return buchungsNummerHotel;
    }

    public void setBuchungsNummerHotel(String buchungsNummerHotel) {
        this.buchungsNummerHotel = buchungsNummerHotel;
    }

    public String getBuchungsNummerFlug() {
        return buchungsNummerFlug;
    }

    public void setBuchungsNummerFlug(String buchungsNummerFlug) {
        this.buchungsNummerFlug = buchungsNummerFlug;
    }
}
