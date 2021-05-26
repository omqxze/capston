package Board;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface BoardAPI {
    @Headers("Content-Type: application/json")
    @GET("Postlist.php")
    Call<ResponseBody> getJsonString();
}