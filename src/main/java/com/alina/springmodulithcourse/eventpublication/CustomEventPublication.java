package com.alina.springmodulithcourse.eventpublication;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "event_publication")
public class CustomEventPublication {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String listenerId;
    private String eventType;
    private String serializedEvent;
    private Timestamp publicationDate;
    private Timestamp completionDate;
}
