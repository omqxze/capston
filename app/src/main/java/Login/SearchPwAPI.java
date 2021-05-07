package Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SearchPwAPI {
    @Headers("Content-Type: application/json")
    @POST("SearchPass.php")
    Call<SearchPwInfo> getJsonString(@Body SearchPwJsonObject searchPwJsonObject);
}