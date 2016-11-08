package no.fint.audit.plugin.mongo.admin.test;

import no.fint.audit.plugin.mongo.admin.model.MongoAuditEvent;
import no.fint.audit.plugin.mongo.admin.repository.AuditEventMongoRepository;
import no.fint.events.model.Event;
import no.fint.events.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Profile(value = "test")
@Component
public class TestMongo {

    @Autowired
    AuditEventMongoRepository auditEventMongoRepository;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 5 ; i++) {
            auditEventFactory("rogfk.no", "Arbeidstaker", "VFS", "GET_ALL_EMPLOYEES");
        }

        for (int i = 0; i < 6 ; i++) {
            auditEventFactory("rogfk.no", "Arbeidstaker", "IDM", "GET_ALL_EMPLOYEES");
        }

        for (int i = 0; i < 5 ; i++) {
            auditEventFactory("rogfk.no", "ELEV", "ItsLearning", "GET_ALL_STUDENTS");
        }

        for (int i = 0; i < 6 ; i++) {
            auditEventFactory("rogfk.no", "Kodeverk", "IDM", "GET_EMPLOYEE_CODES");
        }

        for (int i = 0; i < 5 ; i++) {
            auditEventFactory("rogfk.no", "Arbeidstaker", "VFS", "GET_ALL_EMPLOYEES");
        }

        for (int i = 0; i < 6 ; i++) {
            auditEventFactory("vaf.no", "Arbeidstaker", "IDM", "GET_ALL_EMPLOYEES");
        }

        for (int i = 0; i < 5 ; i++) {
            auditEventFactory("vaf.no", "ELEV", "ItsLearning", "GET_ALL_STUDENTS");
        }

        for (int i = 0; i < 6 ; i++) {
            auditEventFactory("vaf.no", "Kodeverk", "IDM", "GET_EMPLOYEE_CODES");
        }

        for (int i = 0; i < 5 ; i++) {
            auditEventFactory("hfk.no", "Arbeidstaker", "VFS", "GET_ALL_EMPLOYEES");
        }

        for (int i = 0; i < 6 ; i++) {
            auditEventFactory("hfk.no", "Arbeidstaker", "IDM", "GET_ALL_EMPLOYEES");
        }

        for (int i = 0; i < 5 ; i++) {
            auditEventFactory("hfk.no", "ELEV", "ItsLearning", "GET_ALL_STUDENTS");
        }

        for (int i = 0; i < 6 ; i++) {
            auditEventFactory("hfk.no", "Kodeverk", "IDM", "GET_EMPLOYEE_CODES");
        }



    }

    public void auditEventFactory(String orgId, String source, String client, String verb) {

            Event event = new Event(orgId, source, verb, Status.NEW);
            event.setClient(client);
            for (int j = 0; j < 10; j++) {
                event.getData().add(new String("Hello world " + (j + 1)));
            }
            MongoAuditEvent mongoAuditEvent = new MongoAuditEvent(event, true);
            auditEventMongoRepository.save(mongoAuditEvent);

            event.setStatus(Status.INBOUND_QUEUED);
            mongoAuditEvent = new MongoAuditEvent(event, true);
            auditEventMongoRepository.save(mongoAuditEvent);

            event.setStatus(Status.DELIVERED_TO_PROVIDER);
            mongoAuditEvent = new MongoAuditEvent(event, true);
            auditEventMongoRepository.save(mongoAuditEvent);

            event.setStatus(Status.PROVIDER_CONFIRMED);
            mongoAuditEvent = new MongoAuditEvent(event, true);
            auditEventMongoRepository.save(mongoAuditEvent);

            event.setStatus(Status.PROVIDER_RESPONSE);
            mongoAuditEvent = new MongoAuditEvent(event, true);
            auditEventMongoRepository.save(mongoAuditEvent);

            event.setStatus(Status.OUTBOUND_QUEUE);
            mongoAuditEvent = new MongoAuditEvent(event, true);
            auditEventMongoRepository.save(mongoAuditEvent);

            event.setStatus(Status.SENT_TO_CLIENT);
            mongoAuditEvent = new MongoAuditEvent(event, true);
            auditEventMongoRepository.save(mongoAuditEvent);

    }

}
