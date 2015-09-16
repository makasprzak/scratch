package com.goeuro.benchmarking.microservices;

import com.google.gson.Gson;
import org.zeromq.ZMQ;

public class Server {

    private static final Gson GSON = new Gson();

    public static void main (String[] args) {
        ZMQ.Context context = ZMQ.context(1);

        //  Socket to talk to clients
        ZMQ.Socket responder  = context.socket(ZMQ.REP);
        responder.connect("tcp://localhost:5555");

        System.out.println("launch and connect server.");

        while (!Thread.currentThread().isInterrupted()) {
            //  Wait for next request from client
            byte[] request = responder.recv(0);
            try {
                ServerResponse serverResponse = Methods.serverSide(GSON.fromJson(new String(request), Long.class));
                //  Send reply back to client
                responder.send(GSON.toJson(serverResponse), 0);

            } catch (InterruptedException e) {
                //and what now?
            }

        }

        //  We never get here but clean up anyhow
        responder.close();
        context.term();
    }
}
