package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.config.SmsSettings;
import com.revature.exceptions.SmsCustomException;
import com.revature.markers.SmsValidatable;

@Entity
@Table(name = "LOCATIONS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Location implements SmsValidatable {

	transient private static SmsSettings settings = SmsSettings.getInstance();
	
	@Id
	@Column(name = "LOCATION_ID")
	@SequenceGenerator(name = "LOCATION_ID_SEQ", sequenceName = "LOCATION_ID_SEQ")
	@GeneratedValue(generator = "LOCATION_ID_SEQ", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "LOCATION_NAME")
	private String name;

	@Column(name = "LOCATION_COUNTRY")
	private String country;

	@Column(name = "LOCATION_STATE")
	private String state;

	@Column(name = "LOCATION_CITY")
	private String city;

	public Location() {
		super();
	}

	public Location(Long id, String name, String country, String state, String city) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.state = state;
		this.city = city;
	}

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		Location other = (Location) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", country=" + country + ", state=" + state + ", city=" + city
				+ "]";
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.

	}

}
