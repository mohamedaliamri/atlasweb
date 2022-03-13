package atlasweb.service;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
 

public class SendSmsBasic {
 
    private static final String BASE_URL = "https://pw424e.api.infobip.com";
    private static final String API_KEY = "App 8f8c7ebf6be2853c577ea337824ccf10-b86e62e7-5826-4e58-a8cd-f87dd15465ff";
    private static final String MEDIA_TYPE = "application/json";
    private static final String SENDER = "Info-ATLAS";
    private static final String RECIPIENT = "21625428554" ;// "33781096653";
    //"21625428554"; //"
    private static final String MESSAGE_TEXT = "Votre compte:Identifiant '25428554'/Code 'meTgUj'";


    public void sendSMS() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
 
        String bodyJson = String.format("{\"messages\":[{\"from\":\"%s\",\"destinations\":[{\"to\":\"%s\"}],\"text\":\"%s\"}]}",
                SENDER,
                RECIPIENT,
                MESSAGE_TEXT
        );
 
        MediaType mediaType = MediaType.parse(MEDIA_TYPE);
        RequestBody body = RequestBody.create(bodyJson, mediaType);
 
        Request request = prepareHttpRequest(body);
        Response response = client.newCall(request).execute();
 
        System.out.println("HTTP status code: " + response.code());
        System.out.println("Response body: " + response.body().string());
    }
 
    private static Request prepareHttpRequest(RequestBody body) {
        return new Request.Builder()
                .url(String.format("%s/sms/2/text/advanced", BASE_URL))
                .method("POST", body)
                .addHeader("Authorization", API_KEY)
                .addHeader("Content-Type", MEDIA_TYPE)
                .addHeader("Accept", MEDIA_TYPE)
                .build();
    }
}

