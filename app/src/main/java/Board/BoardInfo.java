package Board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class BoardInfo {
    /*
           "confCode": "4",
        "userId": "",
        "boardNum": "3",
        "rsvCount": "0",
        "startDateTime": "0000-00-00 00:00:00",
        "startArea": "here",
        "endArea": "there",
        "contents": "yi hih8h",
        "rsvStat": "0"
     */
    @Expose
    @SerializedName("confCode")
    @Getter
    @Setter
    private String confCode;

    @Expose
    @SerializedName("userId")
    @Getter
    @Setter
    private String userId;

    @Expose
    @SerializedName("boardNum")
    @Getter
    @Setter
    private String boardNum;

    @Expose
    @SerializedName("rsvCount")
    @Getter
    @Setter
    private String rsvCount;

    @Expose
    @SerializedName("startDateTime")
    @Getter
    @Setter
    private String startDateTime;

    @Expose
    @SerializedName("startArea")
    @Getter
    @Setter
    private String startArea;

    @Expose
    @SerializedName("endArea")
    @Getter
    @Setter
    private String endArea;

    @Expose
    @SerializedName("contents")
    @Getter
    @Setter
    private String contents;

    @Expose
    @SerializedName("rsvStat")
    @Getter
    @Setter
    private String rsvStat;
}