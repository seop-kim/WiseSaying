package com.techit.wiseapp.repository;

import com.techit.wiseapp.model.WiseModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SayingRepository {
    private static final Map<Long, WiseModel> sayings = new HashMap<>();
    private static Long id = 0L;

    private static final SayingRepository instance = new SayingRepository();

    private SayingRepository() {

    }

    public static SayingRepository getInstance() {
        return instance;
    }

    public WiseModel save(WiseModel saying) {
        saying.setId(++id);
        sayings.put(saying.getId(), saying);
        return saying;
    }

    public List<WiseModel> findAll() {
        return new ArrayList<>(sayings.values());
    }

    public WiseModel delete(Long id) {
        return sayings.remove(id);

    }

    public WiseModel findOne(Long id) {
        return sayings.get(id);
    }

    public void clear() {
        id = 0L;
        sayings.clear();
    }

    public void load(List<WiseModel> wiseArr) {
        for (WiseModel model : wiseArr) {
            sayings.put(model.getId(), model);
        }
        id = wiseArr.get(wiseArr.size() - 1).getId();
    }
}
