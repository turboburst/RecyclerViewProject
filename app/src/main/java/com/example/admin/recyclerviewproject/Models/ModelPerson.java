package com.example.admin.recyclerviewproject.Models;

/**
 * Created by admin on 12/12/2017.
 */

public class ModelPerson {
    private String id;
    private String name;
    private int age;

    public ModelPerson(){
        this.id = "000";
        this.name = "someone";
        this.age = 0;
    }
    public ModelPerson(String id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
