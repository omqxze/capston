package Reserve;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Reserve {
    @Headers("Content-Type: application/json")
    @POST("ReserInfo.php")
    Call<ResponseBody> getJsonString(@Body ReserveTotalJsonObject reserveTotalJsonObject);
}
