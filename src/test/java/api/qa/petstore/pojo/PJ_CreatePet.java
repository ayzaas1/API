package api.qa.petstore.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PJ_CreatePet {

    private int id;
    private String name;
    private String status;
    private PJ_Create_CategoryPet category;

}
