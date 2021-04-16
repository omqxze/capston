package Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface LoginCheck {

    @FormUrlEncoded
    @POST("LoginCheck.php")
    Call<LoginInfo> setPostField(
            @Field("userId") String userId,
            @Field("userPass") String userPass
    );

}
