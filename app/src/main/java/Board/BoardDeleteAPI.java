package Board;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BoardDeleteAPI {
    @Headers("Content-Type: application/json")
    @POST("PostDrop.php")
    Call<BoardDeleteInfo> getJsonString(@Body BoardDeleteJsonObject boardDeleteJsonObject);
}