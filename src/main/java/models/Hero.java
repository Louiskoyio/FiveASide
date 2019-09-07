package models;
import java.util.Objects;

public class Hero {

    private int id;
    private String name;
    private int age;
    private String specialPower;
    private String weakness;
    private int overallRating;
    private int squadId;


    public Hero(String name, int age, String specialPower, String weakness, int overallRating) {
        this.name = name;
        this.age = age;
        this.specialPower = specialPower;
        this.weakness = weakness;
        this.overallRating = overallRating;
        this.squadId = 0;
    }


    public int getSquadId() { return squadId; }

    public int getOverallRating() { return overallRating; }

    public int getId() { return id; }

    public String getName() { return name; }

    public int getAge() { return age; }

    public String getSpecialPower() { return specialPower; }

    public String getWeakness() { return weakness; }

    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return id == hero.id &&
                overallRating == hero.overallRating &&
                Objects.equals(name, hero.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, overallRating);
    }
}
