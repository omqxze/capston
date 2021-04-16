package Join;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JoinAPI {
    @FormUrlEncoded
    @POST("JoinInfo.php")
    Call<JoinInfo> setPostField(
            @Field("userId") String userId,       // 유저 아이디
            @Field("userPass") String userPass,   // 유저 비밀번호
            @Field("userEmail") String userEmail,  // 유저 이메일
            @Field("userBirth") String userBirth,  // 유저 생일
            @Field("userMajor") String userMajor,  // 유저 전공
            @Field("userStunum") String userStunum,  //유저 학번
            @Field("userLisence") String userLisence,  // 유저 학생증
            @Field("userDate") String userDate,  // 유저 가입날짜
            @Field("userName") String userName,  // 유저 이름
            @Field("userQR") String userQR //유저 QR
    );

}
