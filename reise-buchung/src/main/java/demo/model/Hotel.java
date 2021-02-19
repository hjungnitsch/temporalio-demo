package demo.model;

//@Value.Immutable
//@JsonDeserialize(builder = ImmutableHotel.Builder.class)
public class Hotel {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}