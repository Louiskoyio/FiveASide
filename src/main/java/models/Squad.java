package models;

import java.util.ArrayList;

public class Squad {
    private int id;
    private String name;
    private int size;
    private String objective;
    private static ArrayList<Hero> instances = new ArrayList<>();

    public Squad(int id, String name, int size, String objective) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.objective = objective;
    }


    public int getId() { return id; }

    public String getName() { return name; }

    public int getSize() { return size; }

    public String getObjective() { return objective; }

    public static ArrayList<Hero> getInstances() { return instances; }

}
