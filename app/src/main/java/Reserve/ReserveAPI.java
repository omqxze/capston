package Reserve;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ReserveAPI {
    @Headers("Content-Type: application/json")
    @POST("ReserDo.php")
    Call<ReserveInfo> getJsonString(@Body ReserveJsonObject reserveJsonObject);
}
