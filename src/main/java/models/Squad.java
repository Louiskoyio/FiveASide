package models;

import java.util.ArrayList;

public class Squad {
    private int id;


    private String name;
    private String objective;
    private static ArrayList<Hero> instances = new ArrayList<>();

    public Squad(String name, String objective) {
        this.name = name;
        this.objective = objective;
    }


    public void setId(int id) { this.id = id;}

    public int getId() { return id; }

    public String getName() { return name; }

    public String getObjective() { return objective; }

    public static ArrayList<Hero> getInstances() { return instances; }

}
