package com.alina.springmodulithcourse.eventpublication;

import java.util.List;

public interface CustomEventPublicationService {

    List<CustomEventPublication> getUncompletedEventPublications();

    List<CustomEventPublication> getCompletedEventPublications();
}
