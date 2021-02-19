package demo.workflow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import demo.model.Flug;
import demo.model.Hotel;
import reactor.core.publisher.Mono;

@Service
public class BuchungsService implements BuchungsActivity {

    private static final Logger LOG = LogManager.getLogger();

    private final WebClient webClientFlug;
    private final WebClient webClientHotel;

    public BuchungsService(WebClient.Builder webClientBuilder) {
        this.webClientFlug = webClientBuilder.baseUrl("http://flug-service/fluege/v1").build();
        this.webClientHotel = webClientBuilder.baseUrl("http://hotel-service/hotels/v1").build();
    }

    @Override
    public String bucheFlug(Flug flug) {
        return webClientFlug.post().uri("/buchung")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(flug)
                .retrieve().onStatus(HttpStatus.NOT_ACCEPTABLE::equals,
                        response -> Mono.just(new BuchungException()))
                .bodyToMono(String.class).block();
    }

    @Override
    public String bucheHotel(Hotel hotel) {
        return webClientHotel.post().uri("/buchung")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(hotel)
                .retrieve().onStatus(HttpStatus.NOT_ACCEPTABLE::equals,
                        response -> Mono.just(new BuchungException()))
                .bodyToMono(String.class).block();
    }

    @Override
    public void storniereBuchungFlug(String buchungsNr) {
        webClientFlug.put().uri("/buchung/{nummer}/stornieren",buchungsNr)
                .retrieve().toBodilessEntity().block().getBody();
    }

    @Override
    public void storniereBuchungHotel(String buchungsNr) {
        webClientHotel.put().uri("/buchung/{nummer}/stornieren",buchungsNr)
                .retrieve().toBodilessEntity().block().getBody();
    }

}
