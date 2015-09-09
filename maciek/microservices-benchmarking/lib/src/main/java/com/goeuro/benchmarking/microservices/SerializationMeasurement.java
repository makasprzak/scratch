package com.goeuro.benchmarking.microservices;

import com.goeuro.benchmarking.microservices.ServerResponse;
import com.google.gson.Gson;

import java.io.IOException;

public class SerializationMeasurement {

    private static final Gson MAPPER = new Gson();

    private long doCheckFor(ServerResponse response) throws IOException {
        long start = System.nanoTime();
        String serialized = MAPPER.toJson(response);
        MAPPER.fromJson(serialized, ServerResponse.class);
        long stop = System.nanoTime();
        return  stop - start;
    }

    public long checkFor(ServerResponse response) {
        try {
            return doCheckFor(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
