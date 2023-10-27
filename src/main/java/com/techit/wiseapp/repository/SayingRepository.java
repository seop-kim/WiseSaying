package com.techit.wiseapp.repository;

import com.techit.wiseapp.model.Saying;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SayingRepository {
    private static final Map<Long, Saying> sayings = new HashMap<>();
    private static Long id = 0L;

    private static final SayingRepository instance = new SayingRepository();

    private SayingRepository() {
        testDataInit();
    }

    public static SayingRepository getInstance() {
        return instance;
    }

    public Saying save(Saying saying) {
        saying.setId(++id);
        sayings.put(saying.getId(), saying);
        return saying;
    }

    public List<Saying> findAll() {
        return new ArrayList<>(sayings.values());
    }

    private void testDataInit() {
        for (int i = 1; i <= 10; i++) {
            Saying saying = new Saying();
            saying.setContent("test content " + i);
            saying.setAuthor("test author " + i);
            save(saying);
        }
    }
}
