package com.revature.entities;

import javax.persistence.*;

/**
 * Created by mnikitin on 5/31/17.
 */
@Entity
@Table(name = "BATCH_TYPES")
public class BatchType {
    @Id
    @Column(name="BATCH_TYPE_ID")
    @SequenceGenerator(name="BATCH_TYPE_ID_SEQ", sequenceName="BATCH_TYPE_ID_SEQ")
    @GeneratedValue(generator="BATCH_TYPE_ID_SEQ", strategy=GenerationType.SEQUENCE)
    private long id;

    @Column(name="BATCH_TYPE")
    private String type;

    public BatchType(){
        // Default constructor of uselessness.
    }
    public BatchType(long id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BatchType batchType = (BatchType) o;

        if (id != batchType.id) return false;
        return type.equals(batchType.type);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + type.hashCode();
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
