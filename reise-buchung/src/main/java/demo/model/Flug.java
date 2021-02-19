package demo.model;

//@Value.Immutable
//@JsonDeserialize(builder = ImmutableFlug.Builder.class)
public class Flug {
    private String flugnummer;

    public String getFlugnummer() {
        return flugnummer;
    }

    public void setFlugnummer(String flugnummer) {
        this.flugnummer = flugnummer;
    }
}
