package Board;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

public  class Board {
    @Getter
    @Setter
    private String startArea;
    @Getter
    @Setter
    private String startDateTime;
    @Getter
    @Setter
    private String rsv;
    @Getter
    @Setter
    private String endArea;
    @Getter
    @Setter
    private String contents;
    @Getter
    @Setter
    private String confCode;

    public Board(JSONObject jo) throws JSONException {
        this.startArea = jo.getString("startArea");
        this.startDateTime = jo.getString("startDateTime");
        this.rsv = jo.getString("rsvCount");
        this.endArea = jo.getString("endArea");
        this.contents = jo.getString("contents");
        this.confCode=jo.getString("confCode");
    }
}
