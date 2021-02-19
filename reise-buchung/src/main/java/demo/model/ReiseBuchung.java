package demo.model;

//@Value.Immutable
//@JsonDeserialize(builder = ImmutableReiseBuchung.Builder.class)
public class ReiseBuchung {
    private Flug flug;
    private Hotel hotel;

    public Flug getFlug() {
        return flug;
    }

    public void setFlug(Flug flug) {
        this.flug = flug;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
