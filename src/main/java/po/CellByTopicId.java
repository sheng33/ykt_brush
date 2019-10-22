package po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@AllArgsConstructor
@ToString
public class CellByTopicId {
    private String Id;
    private String categoryName;
    private String cellContent;
    private String cellName;
    private List<CBT_childNodeList> childNodeLists;
    private String courseOpenId;
    private String parentId;
    private int sortOrder;
    private String topicId;
}
