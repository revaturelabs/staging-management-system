package com.revature.sms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BATCH_TYPES")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BatchType {

    @Id
    @Column(name = "BATCH_TYPE_ID")
    @SequenceGenerator(name = "BATCH_TYPE_ID_SEQ", sequenceName = "BATCH_TYPE_ID_SEQ")
    @GeneratedValue(generator = "BATCH_TYPE_ID_SEQ", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "BATCH_TYPE_VALUE")
    private String value;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "BATCH_TYPE_SKILLS", joinColumns = @JoinColumn(name = "BATCH_TYPE_ID"), inverseJoinColumns = @JoinColumn(name = "SKILL_ID"))
    private Set<Skill> skills;

    public BatchType() {

        super();
        this.skills = new HashSet<>();
    }

    public BatchType(long id, String value, Set<Skill> skills) {

        super();
        this.id = id;
        this.value = value;
        this.skills = skills;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getValue() {

        return value;
    }

    public void setValue(String value) {

        this.value = value;
    }

    public Set<Skill> getSkills() {

        return skills;
    }

    public void setSkills(Set<Skill> skills) {

        this.skills = skills;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((skills == null) ? 0 : skills.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public final boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof BatchType))
            return false;
        BatchType other = (BatchType) obj;
        if (skills == null) {
            if (other.skills != null)
                return false;
        } else if (!skills.equals(other.skills))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {

        return "BatchType [id=" + id + ", value=" + value + ", skills=" + skills + "]";
    }
}
