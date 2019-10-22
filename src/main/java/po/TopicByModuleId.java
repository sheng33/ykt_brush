package po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class TopicByModuleId {
    private String id;
    private String name;
    private int sortOrder;
}
