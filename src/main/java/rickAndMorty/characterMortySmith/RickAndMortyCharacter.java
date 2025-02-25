package rickAndMorty.characterMortySmith;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class RickAndMortyCharacter {
        public Info info;
        public ArrayList<Result> results;

        public String getName() {
                if (results != null && !results.isEmpty()) {
                        return results.get(0).name;
                }
                return null;
        }
}
