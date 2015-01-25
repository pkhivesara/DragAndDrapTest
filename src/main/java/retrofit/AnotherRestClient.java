package retrofit;

import com.squareup.okhttp.OkHttpClient;
import retrofit.client.OkClient;

public class AnotherRestClient {
    private static AnotherApiCall REST_CLIENT;
    private static String URL =
            "http://headers.jsontest.com";

    static {
        setupRestClient();
    }

    private AnotherRestClient() {}

    public static AnotherApiCall get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(new OkHttpClient()));


        RestAdapter restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(AnotherApiCall.class);
    }

}
