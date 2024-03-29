package com.api.mv.listener;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class EventListenerCreated extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private HttpServletResponse response;
    private Long id;

    public EventListenerCreated(Object source, HttpServletResponse response, Long id) {
        super(source);
        this.response = response;
        this.id = id;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Long getId() {
        return id;
    }

}
