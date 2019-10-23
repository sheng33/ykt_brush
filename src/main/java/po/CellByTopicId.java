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
    private int stuCellPercent;
    public CellByTopicId(String id, String categoryName, String cellContent, String cellName, String courseOpenId, String parentId, int sortOrder, String topicId,int stuCellPercent) {
        Id = id;
        this.categoryName = categoryName;
        this.cellContent = cellContent;
        this.cellName = cellName;
        this.courseOpenId = courseOpenId;
        this.parentId = parentId;
        this.sortOrder = sortOrder;
        this.topicId = topicId;
        this.stuCellPercent = stuCellPercent;

    }
}
