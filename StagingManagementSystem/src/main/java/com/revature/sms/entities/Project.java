package com.revature.sms.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "INTERNAL_PROJECT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Project {

	@Id
	@Column(name = "PROJECT_ID")
	@SequenceGenerator(name = "PROJECT_ID_SEQ", sequenceName = "PROJECT_ID_SEQ")
	@GeneratedValue(generator = "PROJECT_ID_SEQ", strategy = GenerationType.SEQUENCE)
	private long projectId;
	@Column(name = "PROJECT_NAME")
	private String projectName;
	@Column(name = "PROJECT_DESCRIPTION")
	private String projectDescription;
	@Column(name = "PROJECT_STATUS")
	private String projectStatus;
	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @JsonProperty(access = Access.WRITE_ONLY)
	private Set<Associate> associates;

	public Project() {
		super();
		this.associates = new HashSet<>();
	}
	
	public Project(long projectId, String projectName, String projectDescription, String projectStatus,
			Set<Associate> associates) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.projectStatus = projectStatus;
		this.associates = associates;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Set<Associate> getAssociates() {

		return associates;
	}

	public void setAssociates(Set<Associate> associates) {

		this.associates = associates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associates == null) ? 0 : associates.hashCode());
		result = prime * result + ((projectDescription == null) ? 0 : projectDescription.hashCode());
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
		result = prime * result + ((projectStatus == null) ? 0 : projectStatus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Project))
			return false;
		Project other = (Project) obj;
		if (associates == null) {
			if (other.associates != null)
				return false;
		} else if (!associates.equals(other.associates))
			return false;
		if (projectDescription == null) {
			if (other.projectDescription != null)
				return false;
		} else if (!projectDescription.equals(other.projectDescription))
			return false;
		if (projectId != other.projectId)
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		if (projectStatus == null) {
			if (other.projectStatus != null)
				return false;
		} else if (!projectStatus.equals(other.projectStatus))
			return false;
		return true;
	}

//	@Override
//	public String toString() {
//		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", projectDescription="
//				+ projectDescription + ", projectStatus=" + projectStatus + ", associates=" + associates + "]";
//	}
}
