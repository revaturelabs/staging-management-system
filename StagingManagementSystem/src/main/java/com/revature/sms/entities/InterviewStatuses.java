package com.revature.sms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "INTERVIEW_STATUSES")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InterviewStatuses {

    @Id
    @Column(name = "INTERVIEW_STATUS_ID")
    @SequenceGenerator(name = "INTERVIEW_STATUS_ID_SEQ", sequenceName = "INTERVIEW_STATUS_ID_SEQ")
    @GeneratedValue(generator = "INTERVIEW_STATUS_ID_SEQ", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "INTERVIEW_STATUS_VALUE")
    private String value;

    public InterviewStatuses() {

        super();
    }

    public InterviewStatuses(long id, String value) {

        super();
        this.id = id;
        this.value = value;
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

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public final boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof InterviewStatuses))
            return false;
        InterviewStatuses other = (InterviewStatuses) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {

        return "InterviewStatuses [id=" + id + ", value=" + value + "]";
    }
}
