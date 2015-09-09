package com.goeuro.benchmarking.microservices;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.gson.Gson;
import org.apache.http.client.HttpClient;
import spark.Spark;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.lang.Long.parseLong;
import static spark.Spark.get;
import static spark.SparkBase.port;

public class Client {

    public static final Gson GSON = new Gson();

    public static void main(String[] args) {
        JsonTransformer transformer = new JsonTransformer();
        port(8081);
        get("/",(req,res) -> Methods.clientSide(parseLong(req.queryParams("delayMillis")),delay -> callServer(delay)),transformer);
    }

    private static ServerResponse callServer(Long delay) {
        try {
            InputStream content = new ApacheHttpTransport()
                    .createRequestFactory()
                    .buildGetRequest(getUrl(delay))
                    .execute()
                    .getContent();
            ServerResponse serverResponse = GSON.fromJson(new InputStreamReader(content), ServerResponse.class);
            return serverResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static GenericUrl getUrl(Long delay) {
        return new GenericUrl("http://localhost:8080/?delayMillis="+delay);
    }
}
