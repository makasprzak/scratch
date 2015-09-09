package com.goeuro.benchmarking.microservices;

public class ServerResponse {
    private long receivedNanoTime;
    private long respondedNanoTime;

    public void setReceivedNanoTime(long receivedNanoTime) {
        this.receivedNanoTime = receivedNanoTime;
    }

    public void setRespondedNanoTime(long respondedNanoTime) {
        this.respondedNanoTime = respondedNanoTime;
    }

    public ServerResponse(){}

    public ServerResponse(long receivedNanoTime, long respondedNanoTime) {
        this.receivedNanoTime = receivedNanoTime;
        this.respondedNanoTime = respondedNanoTime;
    }

    public long getReceivedNanoTime() {
        return receivedNanoTime;
    }

    public long getRespondedNanoTime() {
        return respondedNanoTime;
    }

    public static interface ReceivedNanoTimeStep {
        RespondedNanoTimeStep withReceivedNanoTime(long receivedNanoTime);
    }

    public static interface RespondedNanoTimeStep {
        BuildStep withRespondedNanoTime(long respondedNanoTime);
    }

    public static interface BuildStep {
        ServerResponse build();
    }


    public static class Builder implements ReceivedNanoTimeStep, RespondedNanoTimeStep, BuildStep {
        private long receivedNanoTime;
        private long respondedNanoTime;

        private Builder() {
        }

        public static ReceivedNanoTimeStep benchmarkResponse() {
            return new Builder();
        }

        @Override
        public RespondedNanoTimeStep withReceivedNanoTime(long receivedNanoTime) {
            this.receivedNanoTime = receivedNanoTime;
            return this;
        }

        @Override
        public BuildStep withRespondedNanoTime(long respondedNanoTime) {
            this.respondedNanoTime = respondedNanoTime;
            return this;
        }

        @Override
        public ServerResponse build() {
            return new ServerResponse(
                    this.receivedNanoTime,
                    this.respondedNanoTime
            );
        }
    }
}
