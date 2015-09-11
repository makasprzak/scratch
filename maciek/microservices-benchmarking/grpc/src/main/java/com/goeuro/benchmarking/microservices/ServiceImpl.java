package com.goeuro.benchmarking.microservices;

import com.goeuro.benchmarking.microservices.ClientserviceCopied.ServiceRequest;
import io.grpc.stub.StreamObserver;

public class ServiceImpl {
    public void call(ServiceRequest request, StreamObserver<ClientserviceCopied.ServerResponse> responseObserver) {
        try {
            ServerResponse serverResponse = Methods.serverSide((long) request.getDelayMillis());
            ClientserviceCopied.ServerResponse response = ClientserviceCopied.ServerResponse
                    .newBuilder()
                    .setReceivedNanoTime(serverResponse.getReceivedNanoTime())
                    .setRespondedNanoTime(serverResponse.getRespondedNanoTime())
                    .build();
            responseObserver.onValue(response);
            responseObserver.onCompleted();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
