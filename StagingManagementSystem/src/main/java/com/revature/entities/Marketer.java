package com.revature.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.exceptions.SmsCustomException;
import com.revature.markers.SmsValidatable;

import javax.persistence.*;

@Entity
@Table(name = "MARKETERS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Marketer implements SmsValidatable {

    @Id
    @Column(name = "MARKETER_ID")
    @SequenceGenerator(name = "MARKETER_ID_SEQ", sequenceName = "MARKETER_ID_SEQ")
    @GeneratedValue(generator = "MARKETER_ID_SEQ", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "MARKETER_NAME")
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CREDENTIAL_ID")
    private Credential credential;

    public Marketer() {
        super();
    }

    public Marketer(String name, Credential credential) {
        this.name = name;
        this.credential = credential;
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

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Marketer)) return false;

        Marketer marketer = (Marketer) o;

        if (id != marketer.id) return false;
        if (!name.equals(marketer.name)) return false;
        return credential.equals(marketer.credential);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + credential.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Marketer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", credential=" + credential +
                '}';
    }

    @Override
    public void validate() throws SmsCustomException {
        // TODO Validate your members.

    }

}
