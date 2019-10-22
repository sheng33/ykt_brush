package po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class moduleList {
    private String id;
    private String name;
    private int sortOrder;
    private int percent;

}
