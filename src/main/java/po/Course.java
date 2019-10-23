package po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Course {

    private String Id;
    private String courseOpenId;
    //教师姓名
    private String assisTeacherName;
    private String courseCode;
    //课程名
    private String courseName;

    private String openClassId;

    private int process;
}
