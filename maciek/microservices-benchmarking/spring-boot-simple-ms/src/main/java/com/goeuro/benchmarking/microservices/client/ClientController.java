package com.goeuro.benchmarking.microservices.client;

import com.goeuro.benchmarking.microservices.ClientData;
import com.goeuro.benchmarking.microservices.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.goeuro.benchmarking.microservices.Methods.clientSide;

@RestController
@RequestMapping
public class ClientController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping
    public ClientData test(@RequestParam Long delayMillis) throws InterruptedException {
        return clientSide(delayMillis,
                delay -> restTemplate.getForObject("http://localhost:8080?delayMillis={delayMillis}",
                        ServerResponse.class, delay));
    }

}
