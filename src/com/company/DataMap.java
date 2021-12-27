package com.company;

import java.util.HashMap;
import java.util.Map;

public class DataMap {
    private Map<String, String> loginAndPasswordDataMap = new HashMap<>();

    public DataMap() {
        this.loginAndPasswordDataMap.put("pozerinhooo", "Czajnik13");
        this.loginAndPasswordDataMap.put("kolos123", "kolos321");
        this.loginAndPasswordDataMap.put("siemano123", "elo123");
    }

    public Map<String, String> getLoginAndPasswordDataMap() {
        return loginAndPasswordDataMap;
    }
}
