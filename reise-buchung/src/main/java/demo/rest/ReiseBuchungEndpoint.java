package demo.rest;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.applica.spring.boot.starter.temporal.WorkflowFactory;
import demo.model.ReiseBuchung;
import demo.workflow.BuchungsWorkflow;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/reisen/v1")
public class ReiseBuchungEndpoint {

    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private WorkflowFactory fact;
    @Autowired
    private WorkflowClient workflowClient;

    @PostMapping(value = "/buchung",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<String> addBuchung(@RequestBody ReiseBuchung reiseBuchung) {
        String id = "reise-"+UUID.randomUUID().toString();
        BuchungsWorkflow workflow =fact.makeStub(BuchungsWorkflow.class,
                fact.defaultOptionsBuilder(BuchungsWorkflow.class)
                        .setWorkflowId(id));
        WorkflowExecution execution = WorkflowClient.start(workflow::process,reiseBuchung);
        return ResponseEntity.ok(id);
    }

    @PutMapping(value = "/buchung/{nummer}/stornieren")
    ResponseEntity<Void> storniereBuchung(@PathVariable String nummer) {
        workflowClient.newWorkflowStub(BuchungsWorkflow.class,nummer).storniere();
        return ResponseEntity.ok().build();
    }
}
