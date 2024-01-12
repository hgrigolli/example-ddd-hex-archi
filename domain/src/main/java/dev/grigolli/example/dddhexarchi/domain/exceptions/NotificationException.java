package dev.grigolli.example.dddhexarchi.domain.exceptions;


import dev.grigolli.example.dddhexarchi.domain.validation.handler.Notification;

public class NotificationException extends DomainException {

    public NotificationException(final String aMessage, final Notification notification) {
        super(aMessage, notification.getErrors());
    }
}
