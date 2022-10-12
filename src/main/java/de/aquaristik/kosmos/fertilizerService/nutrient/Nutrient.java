package de.aquaristik.kosmos.fertilizerService.nutrient;

import javax.persistence.*;

@Entity
@Table(name = "nutrirecommendation")
public class Nutrient {

    public Nutrient(Nutrient nutrient){
        this.id = nutrient.id;
        this.name = nutrient.name;
        this.min = nutrient.min;
        this.max = nutrient.max;
    }

    public Nutrient(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private double min;

    private double max;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
