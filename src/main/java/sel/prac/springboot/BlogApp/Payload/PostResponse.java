package sel.prac.springboot.BlogApp.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    public int pageNo;
    public int pageSize;
    public List<PostDTO> content;
    private boolean last;
    private boolean first;
    private long totalElements;

}
