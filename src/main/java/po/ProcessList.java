package po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class ProcessList {
    private String courseOpenId;
    private String openClassId;
    private String openCourseCellCount;
    private int stuStudyCourseOpenCellCount;
    private List<moduleList> moduleLists;
}
