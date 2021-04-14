package Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface LoginCheck {

    @FormUrlEncoded
    @POST("/Webapp/Login/")
    Call<loginInfo> setPostField(
            @Field("userId") String userId,
            @Field("userPass") String userPass
    );

}
