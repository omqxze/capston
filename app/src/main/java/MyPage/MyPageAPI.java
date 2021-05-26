package MyPage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MyPageAPI {
    @Headers("Content-Type: application/json")
    @POST("UserInfo.php")
    Call<MyPageInfo> getJsonString(@Body MyPageJsonObject mypageJsonObject);
}
