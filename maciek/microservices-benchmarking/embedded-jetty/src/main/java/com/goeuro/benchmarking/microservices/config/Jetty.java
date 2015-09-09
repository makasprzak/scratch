package com.goeuro.benchmarking.microservices.config;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Arrays.asList;

@Configuration
public class Jetty {
    @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory() throws Exception {
        return new JettyEmbeddedServletContainerFactory() {
            @Override
            protected JettyEmbeddedServletContainer getJettyEmbeddedServletContainer(
                    Server server) {

                JettyEmbeddedServletContainer container = super.getJettyEmbeddedServletContainer(server);
                asList(server.getConnectors()).forEach(System.out::println);
                asList(server.getHandlers()).forEach(System.out::println);
                server.getBeans().forEach(System.out::println);
                return container;
            }
        };
    }
    
}
