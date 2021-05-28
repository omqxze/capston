package QR;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class QRInfo {
    @Expose
    @SerializedName("userId")
    @Getter
    @Setter
    private String userId;

    @Expose
    @SerializedName("userName")
    @Getter
    @Setter
    private String userName;

    @Expose
    @SerializedName("userMajor")
    @Getter
    @Setter
    private String userMajor;

    @Expose
    @SerializedName("userStunum")
    @Getter
    @Setter
    private String userStunum;
}
