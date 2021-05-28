package Reserve;

import Board.BoardWriteInfo;
import Board.BoardWriteJsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ReserveDeleteAPI {
    @Headers("Content-Type: application/json")
    @POST("ReserDrop.php")
    Call<ReserveDeleteInfo> getJsonString(@Body ReserveDeleteJsonObject reserveDeleteJsonObject);
}
