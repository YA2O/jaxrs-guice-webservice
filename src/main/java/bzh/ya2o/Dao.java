package bzh.ya2o;

import java.util.List;

public interface Dao<T> {
    List<? extends T> getAll();

    com.google.common.base.Optional<String> getById(String id);
}
