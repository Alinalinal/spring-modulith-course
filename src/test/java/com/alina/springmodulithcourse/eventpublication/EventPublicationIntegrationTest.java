package com.alina.springmodulithcourse.eventpublication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
class EventPublicationIntegrationTest {

    @Autowired
    CustomEventPublicationService service;

    @Test
    void getUncompletedEventPublicationsTest() {
        List<CustomEventPublication> eventPublications = service.getUncompletedEventPublications();

        eventPublications.forEach(ev -> {
            assertThat(ev).isInstanceOf(CustomEventPublication.class);
            assertThat(ev.getCompletionDate()).isNull();
        });
    }

    @Test
    void getCompletedEventPublicationsTest() {
        List<CustomEventPublication> eventPublications = service.getCompletedEventPublications();

        eventPublications.forEach(ev -> {
            assertThat(ev).isInstanceOf(CustomEventPublication.class);
            assertThat(ev.getCompletionDate()).isNotNull();
        });
    }
}