package org.example;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<Integer, String> buff = new HashMap<>();
        buff.putIfAbsent(1, "bb");
        buff.compute(1, (k, v) -> String.valueOf(k+1));
        String itog = buff.getOrDefault(1, "def");
        System.out.println(itog);
    }
}
