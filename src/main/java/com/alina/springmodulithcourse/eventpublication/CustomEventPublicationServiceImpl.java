package com.alina.springmodulithcourse.eventpublication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomEventPublicationServiceImpl implements CustomEventPublicationService {

    private final CustomEventPublicationRepository customEventPublicationRepository;

    @Override
    public List<CustomEventPublication> getUncompletedEventPublications() {
        return customEventPublicationRepository.getCustomEventPublicationsByPublicationDateNotNullAndCompletionDateIsNull();
    }

    @Override
    public List<CustomEventPublication> getCompletedEventPublications() {
        return customEventPublicationRepository.getCustomEventPublicationsByPublicationDateNotNullAndCompletionDateNotNull();
    }
}
