package com.techit.wiseapp.repository;

import com.techit.wiseapp.model.Saying;
import java.util.HashMap;
import java.util.Map;

public class SayingRepository {
    private static final Map<Long, Saying> sayings = new HashMap<>();
    private static Long id = 0L;

    private static final SayingRepository instance = new SayingRepository();

    private SayingRepository() {
    }

    public static SayingRepository getInstance() {
        return instance;
    }

    public Saying save(Saying saying) {
        saying.setId(++id);
        sayings.put(saying.getId(), saying);
        return saying;
    }
}
