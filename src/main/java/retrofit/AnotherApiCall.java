package retrofit;


import responses.HeadersTest;
import responses.IpTest;
import retrofit.http.GET;

public interface AnotherApiCall {

    @GET("/")
    public HeadersTest getHeadersTestDetails();
}
