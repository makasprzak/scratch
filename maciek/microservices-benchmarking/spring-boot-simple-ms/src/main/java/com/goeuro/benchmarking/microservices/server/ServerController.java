package com.goeuro.benchmarking.microservices.server;

import com.goeuro.benchmarking.microservices.ServerResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.goeuro.benchmarking.microservices.Methods.serverSide;

@RestController
@RequestMapping
public class ServerController {

    @RequestMapping
    public ServerResponse test(@RequestParam Long delayMillis) throws InterruptedException {
        return serverSide(delayMillis);
    }

}
