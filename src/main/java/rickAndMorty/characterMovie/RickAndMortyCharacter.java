package rickAndMorty.characterMovie;

import java.util.ArrayList;

public class RickAndMortyCharacter {
        public Info info;
        public static ArrayList<Result> results;

        public static String getName() {
                if (results != null && !results.isEmpty()) {
                        return results.get(0).name;
                }
                return null;
        }
}
