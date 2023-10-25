package com.techit.wiseapp.generator;

public class IdGenerator {
    public int getPostNum() {
        if (wiseArr.size() == 0) {
            return 1;
        }
        return wiseArr.get(wiseArr.size() - 1).getId() + 1;
    }
}
