package com.goeuro.benchmarking.microservices;

public class ClientData {
    private final long clientSentRequest;
    private final long clientReceivedResponse;
    private final long serverReceivedRequest;
    private final long serverSentResponse;
    private final long calculatedNetworkOverhead;
    private final String stringifiedOverhead;
    private final long serializationTime;
    private final String stringifiedSerializationTime;

    public ClientData(
            long clientSentRequest,
            long clientReceivedResponse,
            long serverReceivedRequest,
            long serverSentResponse,
            long calculatedNetworkOverhead,
            String stringifiedOverhead,
            long serializationTime,
            String stringifiedSerializationTime) {

        this.clientSentRequest = clientSentRequest;
        this.clientReceivedResponse = clientReceivedResponse;
        this.serverReceivedRequest = serverReceivedRequest;
        this.serverSentResponse = serverSentResponse;
        this.calculatedNetworkOverhead = calculatedNetworkOverhead;
        this.stringifiedOverhead = stringifiedOverhead;
        this.serializationTime = serializationTime;
        this.stringifiedSerializationTime = stringifiedSerializationTime;
    }

    public long getClientSentRequest() {
        return clientSentRequest;
    }

    public long getClientReceivedResponse() {
        return clientReceivedResponse;
    }

    public long getServerReceivedRequest() {
        return serverReceivedRequest;
    }

    public long getServerSentResponse() {
        return serverSentResponse;
    }

    public long getCalculatedNetworkOverhead() {
        return calculatedNetworkOverhead;
    }

    public String getStringifiedOverhead() {
        return stringifiedOverhead;
    }

    public long getSerializationTime() {
        return serializationTime;
    }

    public String getStringifiedSerializationTime() {
        return stringifiedSerializationTime;
    }

    public static interface ClientSentRequestStep {
        ClientReceivedResponseStep withClientSentRequest(long clientSentRequest);
    }

    public static interface ClientReceivedResponseStep {
        ServerReceivedRequestStep withClientReceivedResponse(long clientReceivedResponse);
    }

    public static interface ServerReceivedRequestStep {
        ServerSentResponseStep withServerReceivedRequest(long serverReceivedRequest);
    }

    public static interface ServerSentResponseStep {
        CalculatedNetworkOverheadStep withServerSentResponse(long serverSentResponse);
    }

    public static interface CalculatedNetworkOverheadStep {
        StringifiedOverheadStep withCalculatedNetworkOverhead(long calculatedNetworkOverhead);
    }

    public static interface StringifiedOverheadStep {
        SerializationTimeStep withStringifiedOverhead(String stringifiedOverhead);
    }

    public static interface SerializationTimeStep {
        StringifiedSerializationTimeStep withSerializationTime(long serializationTime);
    }

    public static interface StringifiedSerializationTimeStep {
        BuildStep withStringifiedSerializationTime(String stringifiedSerializationTime);
    }

    public static interface BuildStep {
        ClientData build();
    }


    public static class Builder implements ClientSentRequestStep, ClientReceivedResponseStep, ServerReceivedRequestStep, ServerSentResponseStep, CalculatedNetworkOverheadStep, StringifiedOverheadStep, SerializationTimeStep, StringifiedSerializationTimeStep, BuildStep {
        private long clientSentRequest;
        private long clientReceivedResponse;
        private long serverReceivedRequest;
        private long serverSentResponse;
        private long calculatedNetworkOverhead;
        private String stringifiedOverhead;
        private long serializationTime;
        private String stringifiedSerializationTime;

        private Builder() {
        }

        public static ClientSentRequestStep clientData() {
            return new Builder();
        }

        @Override
        public ClientReceivedResponseStep withClientSentRequest(long clientSentRequest) {
            this.clientSentRequest = clientSentRequest;
            return this;
        }

        @Override
        public ServerReceivedRequestStep withClientReceivedResponse(long clientReceivedResponse) {
            this.clientReceivedResponse = clientReceivedResponse;
            return this;
        }

        @Override
        public ServerSentResponseStep withServerReceivedRequest(long serverReceivedRequest) {
            this.serverReceivedRequest = serverReceivedRequest;
            return this;
        }

        @Override
        public CalculatedNetworkOverheadStep withServerSentResponse(long serverSentResponse) {
            this.serverSentResponse = serverSentResponse;
            return this;
        }

        @Override
        public StringifiedOverheadStep withCalculatedNetworkOverhead(long calculatedNetworkOverhead) {
            this.calculatedNetworkOverhead = calculatedNetworkOverhead;
            return this;
        }

        @Override
        public SerializationTimeStep withStringifiedOverhead(String stringifiedOverhead) {
            this.stringifiedOverhead = stringifiedOverhead;
            return this;
        }

        @Override
        public StringifiedSerializationTimeStep withSerializationTime(long serializationTime) {
            this.serializationTime = serializationTime;
            return this;
        }

        @Override
        public BuildStep withStringifiedSerializationTime(String stringifiedSerializationTime) {
            this.stringifiedSerializationTime = stringifiedSerializationTime;
            return this;
        }

        @Override
        public ClientData build() {
            return new ClientData(
                    this.clientSentRequest,
                    this.clientReceivedResponse,
                    this.serverReceivedRequest,
                    this.serverSentResponse,
                    this.calculatedNetworkOverhead,
                    this.stringifiedOverhead,
                    this.serializationTime,
                    this.stringifiedSerializationTime
            );
        }
    }
}
