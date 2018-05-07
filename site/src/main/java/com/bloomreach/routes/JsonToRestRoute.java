package com.bloomreach.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import net.sf.json.JSON;

@Component
public class JsonToRestRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:rest")
                .convertBodyTo(JSON.class)
                .to("log:com.bloomreach.jsonroute?level=INFO")
                .setHeader("CamelHttpMethod", constant("POST"))
                .to("http4://localhost:9999/");
    }
}
