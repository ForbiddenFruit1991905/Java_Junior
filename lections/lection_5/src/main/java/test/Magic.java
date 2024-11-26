package test;

import jakarta.persistence.*;

@Entity
@Table(name = "magic")
public class Magic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "damage")
    private int damage;
    @Column(name = "attack")
    private int attBonus;

//    public Magic(String name, int damage, int attBonus) {
//        this.name = name;
//        this.damage = damage;
//        this.attBonus = attBonus;
//    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Magic() {
    }

    @Override
    public String toString() {
        return "Magic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", damage=" + damage +
                ", attBonus=" + attBonus +
                '}';
    }

    public void setAttBonus(int attBonus) {
        this.attBonus = attBonus;
    }

    public void setName(String name) {
        this.name = name;
    }
}
