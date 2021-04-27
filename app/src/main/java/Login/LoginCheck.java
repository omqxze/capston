package Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginCheck {
    @Headers("Content-Type: application/json")
    @POST("LoginCheck.php")
    Call<LoginInfo> getJsonString(@Body LoginJsonObject loginJsonObject);
}
