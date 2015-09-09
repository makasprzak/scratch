package com.goeuro.benchmarking.microservices;

import java.util.function.Function;

import static com.goeuro.benchmarking.microservices.ClientData.Builder.clientData;
import static com.goeuro.benchmarking.microservices.ServerResponse.Builder.benchmarkResponse;

public class Methods {
    public static ServerResponse serverSide(Long delayMillis) throws InterruptedException {
        long start = System.nanoTime();
        Thread.sleep(delayMillis);
        long stop = System.nanoTime();
        return benchmarkResponse()
                .withReceivedNanoTime(start)
                .withRespondedNanoTime(stop)
                .build();
    }

    public static ClientData clientSide(Long delayMillis, Function<Long, ServerResponse> serverCall) {
        long clientSentRequest = System.nanoTime();
        ServerResponse serverResponse = serverCall.apply(delayMillis);
        long clientReceivedResponse = System.nanoTime();
        long calculatedNetworkOverhead = (clientReceivedResponse - clientSentRequest) - (serverResponse.getRespondedNanoTime() - serverResponse.getReceivedNanoTime());
        long serializationTime = new SerializationMeasurement().checkFor(serverResponse);
        return clientData()
                .withClientSentRequest(clientSentRequest)
                .withClientReceivedResponse(clientReceivedResponse)
                .withServerReceivedRequest(serverResponse.getReceivedNanoTime())
                .withServerSentResponse(serverResponse.getRespondedNanoTime())
                .withCalculatedNetworkOverhead(calculatedNetworkOverhead)
                .withStringifiedOverhead(stringify(calculatedNetworkOverhead))
                .withSerializationTime(serializationTime)
                .withStringifiedSerializationTime(stringify(serializationTime))
                .build();

    }

    private static String stringify(long nanoseconds) {
        return String.format("%.3f ms", Double.valueOf(nanoseconds) / 1000_000);
    }

}
