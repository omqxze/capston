package Board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class BoardWriteInfo {
    @Expose
    @SerializedName("result")
    @Getter
    @Setter
    private String result;
}