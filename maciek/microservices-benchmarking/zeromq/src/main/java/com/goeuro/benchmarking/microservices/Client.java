package com.goeuro.benchmarking.microservices;

import com.google.gson.Gson;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;

import static java.lang.Long.parseLong;
import static spark.Spark.get;
import static spark.SparkBase.port;

public class Client {

    public static final Gson GSON = new Gson();

    public static void main(String[] args) {
        Context context = ZMQ.context(1);
        ZMQ.Socket requester = context.socket(ZMQ.REQ);
        JsonTransformer transformer = new JsonTransformer();
        port(8081);
        get("/",(req,res) -> Methods.clientSide(parseLong(req.queryParams("delayMillis")),delay -> callServer(delay, requester)),transformer);
    }

    private static ServerResponse callServer(Long delay, ZMQ.Socket requester) {
        requester.connect("tcp://localhost:5555");
        requester.send(GSON.toJson(delay));
        return GSON.fromJson(requester.recvStr(0),ServerResponse.class);
    }

}
