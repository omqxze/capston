package Join;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class JoinInfo {
    @Expose @SerializedName("userId") @Getter @Setter
    private String userId;

    @Expose @SerializedName("userPass") @Getter @Setter
    private String userPass;

    @Expose @SerializedName("userEmail") @Getter @Setter
    private String userEmail;

    @Expose @SerializedName("userBirth") @Getter @Setter
    private String userBirth;

    @Expose @SerializedName("userMajor") @Getter @Setter
    private String userMajor;

    @Expose @SerializedName("userStunum") @Getter @Setter
    private String userStunum;

    @Expose @SerializedName("userLisence") @Getter @Setter
    private String userLisence;

    @Expose @SerializedName("userDate") @Getter @Setter
    private String userDate;

    @Expose @SerializedName("userName") @Getter @Setter
    private String userName;

    @Expose @SerializedName("userQR") @Getter @Setter
    private String userQR;
}
