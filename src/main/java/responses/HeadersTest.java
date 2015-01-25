package responses;


import com.google.gson.annotations.SerializedName;

public class HeadersTest {
    @SerializedName("Accept-Language")
    public String AcceptLanguage;
    public String Host;
    public String DNT;
    @SerializedName("User-Agent")
    public String UserAgent;
    public String Accept;

}
