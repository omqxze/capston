package Join;

import Login.LoginInfo;
import Login.LoginJsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface JoinAPI {
    @Headers("Content-Type: application/json")
    @POST("JoinInfo.php")
    Call<JoinInfo> getJsonString(@Body JoinJsonObject joinJsonObject);
}
