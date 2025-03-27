package com.alina.springmodulithcourse.eventaction;

import com.alina.springmodulithcourse.eventaction.action.Action;
import com.alina.springmodulithcourse.eventaction.action.RepublishUncompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("event-action")
public class Controller {

    private final RepublishUncompletedEvent republishUncompletedEvent;

    @GetMapping
    public ResponseEntity<String> publishEvent(@RequestParam(name = "action") String action) {
        republishUncompletedEvent.republish(Action.getActionByName(action));
        return new ResponseEntity<>("Event Triggered", HttpStatus.NO_CONTENT);
    }
}
