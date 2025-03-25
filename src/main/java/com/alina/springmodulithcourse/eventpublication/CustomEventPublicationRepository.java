package com.alina.springmodulithcourse.eventpublication;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomEventPublicationRepository extends CrudRepository<CustomEventPublication, Long> {

    List<CustomEventPublication> getCustomEventPublicationsByPublicationDateNotNullAndCompletionDateIsNull();

    List<CustomEventPublication> getCustomEventPublicationsByPublicationDateNotNullAndCompletionDateNotNull();
}
