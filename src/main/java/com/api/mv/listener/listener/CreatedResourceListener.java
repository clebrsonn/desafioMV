package com.api.mv.listener.listener;

import com.api.mv.listener.EventListenerCreated;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class CreatedResourceListener implements ApplicationListener<EventListenerCreated> {

    @Override
    public void onApplicationEvent(EventListenerCreated eventListenerCreated) {
        HttpServletResponse response = eventListenerCreated.getResponse();
        Long codigo = eventListenerCreated.getId();

        addHeaderLocation(response, codigo);
    }

    private void addHeaderLocation(HttpServletResponse response, Long codigo) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(codigo).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }

}
