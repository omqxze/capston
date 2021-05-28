package QR;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface QRAPI {
    @Headers("Content-Type: application/json")
    @POST("CreateQR.php")
    Call<QRInfo> getJsonString(@Body QRJsonObject qrJsonObject);
}
