package Board;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BoardReviseAPI {
    @Headers("Content-Type: application/json")
    @POST("PostUpdate.php")
    Call<BoardReviseInfo> getJsonString(@Body BoardReviseJsonObject boardReviseObject);
}
