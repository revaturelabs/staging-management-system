package com.revature.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.config.SmsSettings;
import com.revature.exceptions.SmsCustomException;
import com.revature.markers.SmsValidatable;
import com.revature.util.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "INTERVIEWS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Interview implements SmsValidatable {

    transient private static SmsSettings settings = SmsSettings.getInstance();

    @Id
    @Column(name = "INTERVIEW_ID")
    @SequenceGenerator(name = "INTERVIEW_ID_SEQ", sequenceName = "INTERVIEW_ID_SEQ")
    @GeneratedValue(generator = "INTERVIEW_ID_SEQ", strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSOCIATE_ID")
    private Associate associate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MARKETER_ID")
    private Marketer marketer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INTERVIEW_STATUS_ID")
    private InterviewStatuses interviewStatus;

    @Column(name = "INTERVIEW_TIME")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime scheduled;

    public Interview() {
        super();
    }

    public Interview(Associate associate, Client client, Marketer marketer, InterviewStatuses interviewStatus, LocalDateTime scheduled) {
        this.associate = associate;
        this.client = client;
        this.marketer = marketer;
        this.interviewStatus = interviewStatus;
        this.scheduled = scheduled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Associate getAssociate() {
        return associate;
    }

    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Marketer getMarketer() {
        return marketer;
    }

    public void setMarketer(Marketer marketer) {
        this.marketer = marketer;
    }

    public InterviewStatuses getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(InterviewStatuses interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public LocalDateTime getScheduled() {
        return scheduled;
    }

    public void setScheduled(LocalDateTime scheduled) {
        this.scheduled = scheduled;
    }


    @Override
    final public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interview)) return false;

        Interview interview = (Interview) o;

        if (id != interview.id) return false;
        if (!associate.equals(interview.associate)) return false;
        if (!client.equals(interview.client)) return false;
        if (!marketer.equals(interview.marketer)) return false;
        if (!interviewStatus.equals(interview.interviewStatus)) return false;
        return scheduled != null ? scheduled.equals(interview.scheduled) : interview.scheduled == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + associate.hashCode();
        result = 31 * result + client.hashCode();
        result = 31 * result + marketer.hashCode();
        result = 31 * result + interviewStatus.hashCode();
        result = 31 * result + (scheduled != null ? scheduled.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "id=" + id +
                ", associate=" + associate +
                ", client=" + client +
                ", marketer=" + marketer +
                ", interviewStatus=" + interviewStatus +
                ", scheduled=" + scheduled +
                '}';
    }

    @Override
    public void validate() throws SmsCustomException {
        // TODO Validate your members.

    }

}
