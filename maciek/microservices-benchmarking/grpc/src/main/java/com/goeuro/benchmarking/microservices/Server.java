package com.goeuro.benchmarking.microservices;

import io.grpc.ServerServiceDefinition;
import io.grpc.netty.NettyServerBuilder;

public class Server {
    private static int port = 8080;
    private static ServiceImpl service;

    public static void main(String[] args) {
        //TODO trying to do it according to docs, but seems they are outdated
//        service = NettyServerBuilder.forPort(port)
//                .addService(ServerServiceDefinition.builder("service").)
    }
}
