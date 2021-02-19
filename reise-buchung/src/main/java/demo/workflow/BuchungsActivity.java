package demo.workflow;

import demo.model.Flug;
import demo.model.Hotel;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface BuchungsActivity {

    String bucheFlug(Flug flug);
    String bucheHotel(Hotel hotel);
    void storniereBuchungFlug(String buchungsNr);
    void storniereBuchungHotel(String buchungsNr);
}
