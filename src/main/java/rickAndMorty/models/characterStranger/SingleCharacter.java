package rickAndMorty.models.characterStranger;

import lombok.Getter;
import lombok.Setter;
import rickAndMorty.models.characterMortySmith.Location;
import rickAndMorty.models.characterMortySmith.Origin;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
public class SingleCharacter {
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Origin origin;
    private Location location;
    private String image;
    private ArrayList<String> episode;
    private String url;
    private Date created;
}