package models;
import java.util.Objects;

public class Player {

    private int id;
    private String name;
    private int age;
    private String special_power;
    private String weakness;
    private int overall_rating;
    private int squad_id;
    private String position;
    private int attack;
    private int defence;
    private int chemistry;
    private int passing;
    private int physical;


    public Player(String name, int age, String special_power, String weakness, int squad_id, int overall_rating, String position, int attack, int defence, int chemistry, int passing, int physical) {
        this.name = name;
        this.age = age;
        this.special_power = special_power;
        this.weakness = weakness;
        this.overall_rating = overall_rating;
        this.squad_id = squad_id;
        this.position = position;
        this.attack = attack;
        this.defence = defence;
        this.chemistry = chemistry;
        this.passing = passing;
        this.physical = physical;
    }


    public String  getPosition() { return position; }

    public int getAttack() { return attack; }

    public int getDefence() { return defence; }

    public int getChemistry() { return chemistry; }

    public int getPassing() { return passing; }

    public int getPhysical() { return physical; }

    public int getSquadId() { return squad_id; }

    public int getOverallRating() { return overall_rating; }

    public int getId() { return id; }

    public String getName() { return name; }

    public int getAge() { return age; }

    public String getSpecialPower() { return special_power; }

    public String getWeakness() { return weakness; }

    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }
    public void setSquadId(int squad_id) { this.squad_id = squad_id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player hero = (Player) o;
        return id == hero.id &&
                age == hero.age &&
                overall_rating == hero.overall_rating &&
                squad_id == hero.squad_id &&
                Objects.equals(name, hero.name) &&
                Objects.equals(special_power, hero.special_power) &&
                Objects.equals(weakness, hero.weakness);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, special_power, weakness, overall_rating, squad_id);
    }
}
