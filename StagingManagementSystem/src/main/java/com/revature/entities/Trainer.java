package com.revature.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by mnikitin on 5/31/17.
 */
@Entity
@Table(name="TRAINERS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Trainer {
    @Id
    @Column(name="TRAINER_ID")
    @SequenceGenerator(name="TRAINER_ID_SEQ", sequenceName="TRAINER_ID_SEQ")
    @GeneratedValue(generator="TRAINER_ID_SEQ", strategy=GenerationType.SEQUENCE)
    private long id;

    @Column(name="NAME")
    private String name;

    public Trainer(){
        //  //
    }

    public Trainer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trainer trainer = (Trainer) o;

        if (id != trainer.id) return false;
        return name.equals(trainer.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
