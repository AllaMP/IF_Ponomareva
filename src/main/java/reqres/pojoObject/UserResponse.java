package reqres.pojoObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String name;
    private String job;
    private String id;
    private String createdAt;
}