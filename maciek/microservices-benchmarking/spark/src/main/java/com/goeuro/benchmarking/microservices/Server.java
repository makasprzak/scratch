package com.goeuro.benchmarking.microservices;

import static com.goeuro.benchmarking.microservices.Methods.serverSide;
import static spark.Spark.get;
import static spark.SparkBase.port;

public class Server {
    public static void main(String[] args) {
        JsonTransformer transformer = new JsonTransformer();
        port(8080);
        get("/", (req, res) -> serverSide(Long.parseLong(req.queryParams("delayMillis"))), transformer);
    }
}
