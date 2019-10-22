package po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class CBT_childNodeList {
    private String Id;
    private String categoryName;
    private String cellContent;
    private String cellName;
    private String courseOpenId;
    private String parentId;
    private String resourceUrl;
    private int stuCellFourCount;
    private int stuCellFourPercent;
}
