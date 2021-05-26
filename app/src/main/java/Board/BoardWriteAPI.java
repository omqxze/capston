package Board;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BoardWriteAPI {
    @Headers("Content-Type: application/json")
    @POST("PostRegi.php")
    Call<BoardWriteInfo> getJsonString(@Body BoardWriteJsonObject boardJsonObject);
}