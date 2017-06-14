package com.revature.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.*;

@Entity
@Table(name = "CREDENTIALS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Credential {

    @JsonProperty(access = Access.WRITE_ONLY)
    @Id
    @Column(name = "CREDENTIAL_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDENTIAL_ID_SEQ")
    @SequenceGenerator(name = "CREDENTIAL_ID_SEQ", sequenceName = "CREDENTIAL_ID_SEQ")
    private long id;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "CREDENTIAL_USERNAME")
    private String username;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "CREDENTIAL_PASSWORD")
    private String password;

    public Credential() {
        super();
    }

    public Credential(long id, String username, String password) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Credential))
            return false;
        Credential other = (Credential) obj;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Credential [id=" + id + ", username=" + username + ", password=" + password + "]";
    }

}
