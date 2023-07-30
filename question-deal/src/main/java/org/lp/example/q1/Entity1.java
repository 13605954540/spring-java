package org.lp.example.q1;

public class Entity1 {

    public Entity1() {
        System.err.println("Entity1: " + "实例化");
    }

    static {
        System.err.println("Entity1: " + "static");
    }
}
