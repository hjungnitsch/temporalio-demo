package demo.workflow;

import ai.applica.spring.boot.starter.temporal.annotations.TemporalWorkflow;
import demo.model.BuchungsStatus;
import demo.model.ReiseBuchung;
import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
@TemporalWorkflow("ReiseBuchung")
public interface BuchungsWorkflow {

    @WorkflowMethod
    void process(ReiseBuchung reiseBuchung);

    @SignalMethod
    void storniere();

    @QueryMethod
    BuchungsStatus getBuchungsStatus();


}
