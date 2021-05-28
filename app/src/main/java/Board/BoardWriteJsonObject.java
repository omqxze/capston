package Board;

import lombok.Getter;
import lombok.Setter;

public class BoardWriteJsonObject {
    @Getter
    @Setter
    private String userId;
    @Getter
    @Setter
    private String startArea;
    @Getter
    @Setter
    private String startDateTime;
    @Getter
    @Setter
    private String endArea;
    @Getter
    @Setter
    private String boardNum;
    @Getter
    @Setter
    private String contents;

    @Override
    public String toString() {return "CalssPojo [userid="+userId+", startArea="+startArea+", startDateTime = "+startDateTime+", endArea = "+endArea+", boardNum = "+boardNum+",contents = "+contents+"]";}

    public BoardWriteJsonObject(String userId,String startArea, String startDateTime, String endArea, String boardNum, String contents){
        this.userId=userId;
        this.startArea = startArea;
        this.startDateTime = startDateTime;
        this.endArea = endArea;
        this.boardNum = boardNum;
        this.contents = contents;
    }
}