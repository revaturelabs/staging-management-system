package com.revature.sms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "ASSOCIATES")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Associate {

    @Id
    @Column(name = "ASSOCIATE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSOCIATE_ID_SEQ")
    @SequenceGenerator(name = "ASSOCIATE_ID_SEQ", sequenceName = "ASSOCIATE_ID_SEQ")
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CREDENTIAL_ID")
    private Credential credential;

    @Column(name = "ASSOCIATE_NAME")
    private String name;

    @Column(name = "ASSOCIATE_PORTFOLIO_LINK")
    private String portfolioLink;

    @ManyToOne
    @JoinColumn(name = "BATCH_ID")
    private Batch batch;

    @Column(name = "ASSOCIATE_STATUS")
    private String associateStatus;

    @Column(name = "PORTFOLIO_STATUS")
    private String portfolioStatus;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client lockedTo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ASSOCIATE_SKILLS", joinColumns = @JoinColumn(name = "ASSOCIATE_ID"), inverseJoinColumns = @JoinColumn(name = "SKILL_ID"))
    private Set<Skill> skills;

    @OneToMany(mappedBy = "associate")
    private Set<Job> jobs;

    public Associate() {
        super();
        this.skills = new HashSet<>();
        this.jobs = new HashSet<>();

    }

    public Associate(Long id, Credential credential, String name, String portfolioLink, Batch batch, String associateStatus, String portfolioStatus, Client lockedTo, Set<Skill> skills, Set<Job> jobs) {
        super();
        this.id = id;
        this.credential = credential;
        this.name = name;
        this.portfolioLink = portfolioLink;
        this.batch = batch;
        this.associateStatus = associateStatus;
        this.portfolioStatus = portfolioStatus;
        this.lockedTo = lockedTo;
        this.skills = skills;
        this.jobs = jobs;
    }

    /**
     * Returns true if associate was on job during the given date.
     */
    public boolean hasJobOnDate(LocalDateTime date) {
        for (Job j : jobs) {
            boolean beforeEnd = j.getEndDate() == null || date.compareTo(j.getEndDate()) < 0;
            boolean hasentStopped = j.getEndDate() == null;
            boolean afterStart = date.compareTo(j.getStartDate()) > 0;

            if (afterStart && (hasentStopped || beforeEnd))
                return true;
        }
        return false;
    }

    public boolean inTrackedOnBench(LocalDateTime date) {
        for (Job j : jobs) {
            if (j.getEndDate() == null)
                return false;
            return date.compareTo(j.getEndDate()) > 0;
        }
        return false;
    }

    /**
     * Returns true if associate has not started thier training and they have not had
     * any jobs. Leaving it possible for associates to participate in multiple training
     * batches only after they have had atleast one job.
     */
    public boolean hasStartedOnDate(LocalDateTime date) {
        boolean hasBegunTraining = date.compareTo(batch.getStartDate()) > 0;

        if (hasBegunTraining)
            return true;
        return false;
    }

    /**
     * Returns true if associate was in training during the given date.
     */
    public boolean isTrainingOnDate(LocalDateTime adate) {
        LocalDateTime date = adate.withHour(12); //Set mid day all other events should be the beginning of the day.
        boolean afterBatchStart = date.compareTo(batch.getStartDate()) > 0;
        boolean beforeBatchEnd = date.compareTo(batch.getEndDate()) < 0;
        if (afterBatchStart && beforeBatchEnd)
            return true;

        return false;
    }

    /**
     * This function returns true if the associate was in staging on the given date.
     */
    public boolean isTrackedOnDate(LocalDateTime date) {
        if (hasStartedOnDate(date) && !isTrainingOnDate(date) && !hasJobOnDate(date))
            return true;
        return false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortfolioLink() {
        return portfolioLink;
    }

    public void setPortfolioLink(String portfolioLink) {
        this.portfolioLink = portfolioLink;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public String getAssociateStatus() {
        return associateStatus;
    }

    public boolean isActive() {
        return (this.associateStatus.equals("Staging")) ? true : false;
    }

    public void setAssociateStatus() {
        if (this.isTrackedOnDate(LocalDateTime.now()))
            this.associateStatus = "Training";
        else if (isTrackedOnDate(LocalDateTime.now()))
            this.associateStatus = "Staging";
        else if(hasJobOnDate(LocalDateTime.now()))
            this.associateStatus = "Project";
        //need the method to check if the associate is in bench
        else
        if (this.inTrackedOnBench(LocalDateTime.now()))
            this.associateStatus = "Bench";
    }

    public String getPortfolioStatus() {
        return portfolioStatus;
    }

    public void setPortfolioStatus(String portfolioStatus) {
        this.portfolioStatus = portfolioStatus;
    }

    public Client getLockedTo() {
        return lockedTo;
    }

    public void setLockedTo(Client lockedTo) {
        this.lockedTo = lockedTo;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Associate associate = (Associate) o;

        if (id != associate.id) return false;
        if (credential != null ? !credential.equals(associate.credential) : associate.credential != null) return false;
        if (name != null ? !name.equals(associate.name) : associate.name != null) return false;
        if (portfolioLink != null ? !portfolioLink.equals(associate.portfolioLink) : associate.portfolioLink != null)
            return false;
        if (batch != null ? !batch.equals(associate.batch) : associate.batch != null) return false;
        if (associateStatus != null ? !associateStatus.equals(associate.associateStatus) : associate.associateStatus != null)
            return false;
        if (portfolioStatus != null ? !portfolioStatus.equals(associate.portfolioStatus) : associate.portfolioStatus != null)
            return false;
        if (lockedTo != null ? !lockedTo.equals(associate.lockedTo) : associate.lockedTo != null) return false;
        if (skills != null ? !skills.equals(associate.skills) : associate.skills != null) return false;
        return jobs != null ? jobs.equals(associate.jobs) : associate.jobs == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (credential != null ? credential.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (portfolioLink != null ? portfolioLink.hashCode() : 0);
        result = 31 * result + (batch != null ? batch.hashCode() : 0);
        result = 31 * result + (associateStatus != null ? associateStatus.hashCode() : 0);
        result = 31 * result + (portfolioStatus != null ? portfolioStatus.hashCode() : 0);
        result = 31 * result + (lockedTo != null ? lockedTo.hashCode() : 0);
        result = 31 * result + (skills != null ? skills.hashCode() : 0);
        result = 31 * result + (jobs != null ? jobs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Associate{" +
                "id=" + id +
                ", credential=" + credential +
                ", name='" + name + '\'' +
                ", portfolioLink='" + portfolioLink + '\'' +
                ", associateStatus='" + associateStatus + '\'' +
                ", portfolioStatus='" + portfolioStatus + '\'' +
                ", lockedTo=" + lockedTo +
                '}';
    }
}
