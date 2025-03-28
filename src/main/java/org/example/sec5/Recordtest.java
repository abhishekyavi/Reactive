package org.example.sec5;

public class Recordtest {
    record person(int age, String name) {
    }

    public static void main(String[] args) {
        person p1 = new person(10, "John");
        person p2 = new person(10, "John");

        System.out.println(p1.equals(p2)); // true
        System.out.println(p1 == p2); // false

        System.out.println(p1); // person[age=10, name=John]
        System.out.println(p1.age()); // 10
        System.out.println(p1.name()); // John
    }

}
