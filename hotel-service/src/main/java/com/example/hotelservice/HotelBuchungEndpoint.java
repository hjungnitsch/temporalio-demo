package com.example.hotelservice;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/hotels/v1")
public class HotelBuchungEndpoint {

    private static final Logger LOG = LogManager.getLogger();

    @PostMapping(value = "/buchung",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<String> addBuchung(@RequestBody HotelBuchung hotelBuchung) {
        LOG.info("Buchung für Hotel: "+ hotelBuchung.getName());
        return ResponseEntity.ok("hotel-"+UUID.randomUUID().toString());
    }

    @PutMapping(value = "/buchung/{nummer}/stornieren")
    ResponseEntity<Void> storniereBuchung(@PathVariable String nummer) {
        LOG.info("Stornierung für Hotel-Buchung: "+ nummer);
        return ResponseEntity.ok().build();
    }
}
