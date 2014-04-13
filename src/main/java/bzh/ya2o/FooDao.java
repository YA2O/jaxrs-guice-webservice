package bzh.ya2o;

import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FooDao implements Dao<String> {

    private static final Map<String, String> db = new HashMap<String, String>() {{
        put("1", "A");
        put("2", "B");
        put("3", "C");
        put("4", "D");
    }};

    @Override
    public List<String> getAll() {
        List<String> hits = new ArrayList<>();
        for (String value : db.values()) {
            hits.add(value);
        }
        return hits;
    }

    @Override
    public Optional<String> getById(String id) {
        if (!db.containsKey(id))
            return Optional.absent();
        else
            return Optional.of(db.get(id));
    }
}
