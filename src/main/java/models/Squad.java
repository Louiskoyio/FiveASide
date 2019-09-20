package models;

public class Squad {
    private int squad_id;
    private String name;
    private String objective;
    private String homeground;


    public Squad(String name, String objective, String homeground) {
        this.name = name;
        this.objective = objective;
        this.homeground = homeground;
    }

    public String getHomeground() {return homeground; }

    public void setId(int squad_id) { this.squad_id = squad_id;}

    public int getId() { return squad_id; }

    public String getName() { return name; }

    public String getObjective() { return objective; }



}
