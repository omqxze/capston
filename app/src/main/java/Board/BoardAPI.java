package Board;

import Join.JoinInfo;
import Join.JoinJsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BoardAPI {
    @Headers("Content-Type: application/json")
    @POST("PostRegi.php")
    Call<BoardInfo> getJsonString(@Body BoardJsonObject boardJsonObject);
}