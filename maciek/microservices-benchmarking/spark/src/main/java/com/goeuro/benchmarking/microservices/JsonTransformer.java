package com.goeuro.benchmarking.microservices;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    private final Gson gson = new Gson();

    @Override
    public String render(Object o) throws Exception {
        return gson.toJson(o);
    }

    public Gson getGson() {
        return gson;
    }
}
