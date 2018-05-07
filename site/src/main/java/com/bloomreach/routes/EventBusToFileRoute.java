package com.bloomreach.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import net.sf.json.JSON;

@Component
public class EventBusToFileRoute extends RouteBuilder {
    @Override
    public void configure() {
        //Subscribe publish/depublish events as JSON from HippoEventBus.
        from("hippoevent:?category=workflow&action=publish,depublish")
                .convertBodyTo(String.class)
                .convertBodyTo(JSON.class)
                .to("log:com.bloomreach.kafka?level=INFO")
                // Store the JSON string to a file in the 'inbox' folder
                .to("file:siteinbox?autoCreate=true&charset=utf-8");
    }
}
