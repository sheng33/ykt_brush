package po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class child_DIrectory {
    private String courseOpenId;
    private double audioVideoLong;
    private String courseName;
    private String openClassId;
    private String moduleId;
    private String topicId;
    private String cellId;
    private String cellName;
    private String categoryName;
    private int pageCount;
    private String cellLogId;
    private int stuCellViewTime;
    private int stuCellPicCount;
    private int stuStudyNewlyTime;
    private int stuStudyNewlyPicCount;
    private int cellPerCent;
    private String guIdToken;

}
