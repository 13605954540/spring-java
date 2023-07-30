package org.lp.example.q1;

public class Entity2 extends Entity1 {

    public Entity2() {
        System.err.println("Entity2: " + "实例化");
    }

    static {
        System.err.println("Entity2: " + "static");
    }
}
