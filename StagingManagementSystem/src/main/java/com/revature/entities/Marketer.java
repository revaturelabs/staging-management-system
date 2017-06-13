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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credential == null) ? 0 : credential.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Marketer other = (Marketer) obj;
		if (credential == null) {
			if (other.credential != null)
				return false;
		} else if (!credential.equals(other.credential))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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
