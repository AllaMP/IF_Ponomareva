package reqres.pojoObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String job;

    @JsonCreator
    public UserRequest(
            @JsonProperty("name") String name,
            @JsonProperty("job") String job) {
        this.name = name;
        this.job = job;
    }
}