package Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.*;

import lombok.Getter;
import lombok.Setter;

public class LoginInfo {

    @Expose
    @SerializedName("result")
    @Getter @Setter
    private String result;

    @Expose
    @SerializedName("userStunum")
    @Getter @Setter
    private String userStunum;
}
