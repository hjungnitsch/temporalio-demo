package demo.workflow;

import java.time.Duration;

import org.springframework.stereotype.Component;

import ai.applica.spring.boot.starter.temporal.annotations.ActivityStub;
import ai.applica.spring.boot.starter.temporal.annotations.RetryActivityOptions;
import ai.applica.spring.boot.starter.temporal.annotations.TemporalWorkflow;
import demo.model.BuchungsStatus;
import demo.model.ReiseBuchung;
import io.temporal.failure.ActivityFailure;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;

@Component
@TemporalWorkflow("ReiseBuchung")
public class BuchungsWorkflowImpl implements BuchungsWorkflow {

    @ActivityStub(scheduleToClose = "P1D",
            retryOptions = @RetryActivityOptions(doNotRetry = {"demo.workflow.BuchungException"}
                ))
    private BuchungsActivity buchungsActivity;

    private BuchungsStatus buchungsStatus = new BuchungsStatus();

    @Override
    public void process(ReiseBuchung reiseBuchung) {
        Saga.Options sagaOptions = new Saga.Options.Builder().build();
        Saga saga = new Saga(sagaOptions);

        try {
            String buchungsNrHotel = buchungsActivity.bucheHotel(reiseBuchung.getHotel());
            buchungsStatus.setBuchungsNummerHotel(buchungsNrHotel);
            saga.addCompensation(buchungsActivity::storniereBuchungHotel, buchungsNrHotel);

            String buchungsNrFlug = buchungsActivity.bucheFlug(reiseBuchung.getFlug());
            buchungsStatus.setBuchungsNummerFlug(buchungsNrFlug);
            saga.addCompensation(buchungsActivity::storniereBuchungFlug, buchungsNrFlug);

            Workflow.await(Duration.ofMinutes(2), () -> buchungsStatus.isStorniert());
            if(buchungsStatus.isStorniert()) {
                saga.compensate();
            }
        } catch (ActivityFailure e) {
            saga.compensate();
            throw e;
        }

    }

    @Override
    public void storniere() {
        buchungsStatus.setStorniert(true);
    }

    @Override
    public BuchungsStatus getBuchungsStatus() {
        return buchungsStatus;
    }
}