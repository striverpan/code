package com.pan.drl;

import org.drools.core.event.DebugAgendaEventListener;
import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pan on 2017/11/13.
 */
public class Test {

    public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        KieSession ksession = kc.newKieSession("Applicant");

        ksession.addEventListener( new DebugAgendaEventListener() );
        ksession.addEventListener( new DebugRuleRuntimeEventListener() );

/*
        final Message message = new Message();
        message.setMessage( "first message" );
        message.setStatus( Message.HELLO );
        ksession.insert( message );*/

     /*   Applicant applicant = new Applicant("xx",12);
        ksession.insert(applicant);*/

        for (int i=0;i<10000000;i++){
            ksession.getEntryPoint("signal-event").insert(new SignalEvent(1000));
            ksession.fireAllRules();
        }

        // and fire the rules


    }

    public static class Message {
        public static final int HELLO   = 0;
        public static final int GOODBYE = 1;

        private String          message;

        private int             status;

        public Message() {

        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(final String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(final int status) {
            this.status = status;
        }

        public static Message doSomething(Message message) {
            return message;
        }

        public boolean isSomething(String msg,
                                   List<Object> list) {
            list.add( this );
            return this.message.equals( msg );
        }
    }
}


