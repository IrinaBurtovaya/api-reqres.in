import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//создаем POJO модель, используя lombok
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {

    public UserData(String name, String job) {
        this.name = name;
        this.job = job;
    }

    private Long id;
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String name;
    private String job;
}
