package retrofit;

import responses.IpTest;
import retrofit.http.GET;


public interface ApiCall {


    @GET("/")
    public IpTest getIpTestDetails();
}