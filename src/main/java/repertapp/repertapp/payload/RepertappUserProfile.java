package repertapp.repertapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepertappUserProfile {
    private Long id;
    private String name;
    private String email;
    private String username;
}
