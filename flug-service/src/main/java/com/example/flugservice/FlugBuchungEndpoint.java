package com.example.flugservice;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/fluege/v1")
public class FlugBuchungEndpoint {

    private static final Logger LOG = LogManager.getLogger();

    @PostMapping(value = "/buchung",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<String> addBuchung(@RequestBody FlugBuchung flugBuchung) {
        LOG.info("Buchung für Flug mit der Nummer: "+flugBuchung.getFlugnummer());
        if(flugBuchung.getFlugnummer().equals("12345")) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.ok("flug-"+UUID.randomUUID().toString());
    }

    @PutMapping(value = "/buchung/{nummer}/stornieren")
    ResponseEntity<Void> storniereBuchung(@PathVariable String nummer) {
        LOG.info("Stornierung für Flug-Buchung: "+ nummer);
        return ResponseEntity.ok().build();
    }
}
