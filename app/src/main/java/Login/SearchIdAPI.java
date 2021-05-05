package Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SearchIdAPI {
    @Headers("Content-Type: application/json")
    @POST("SearchId.php")
    Call<SearchIdInfo> getJsonString(@Body SearchIdJsonObject searchIdJsonObject);
}