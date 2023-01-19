package com.example.workflow;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class ReserveSeatOnBoat implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String money = "0";
        String ticketType = "Coach";

        money = (String) delegateExecution.getVariable("money");
        int moneyInt = Integer.parseInt(money);

        if (moneyInt >= 1000) {
            ticketType = "First Class ticket";
        } else if (moneyInt >= 500) {
            ticketType = "Business Class ticket";
        } else if (moneyInt <= 10) {
            throw new BpmnError("Fall_Overboard", "A cheap ticket has led to a small wave throwing you overboard");
        }
        delegateExecution.setVariable("ticketType", ticketType);
    }
}
