package models;

import java.util.ArrayList;

public class Squad {
    private int id;
    private String name;
    private String objective;


    public Squad(String name, String objective) {
        this.name = name;
        this.objective = objective;
    }


    public void setId(int id) { this.id = id;}

    public int getId() { return id; }

    public String getName() { return name; }

    public String getObjective() { return objective; }



}
