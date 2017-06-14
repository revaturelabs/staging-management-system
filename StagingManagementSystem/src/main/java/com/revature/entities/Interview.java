package com.revature.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.util.LocalDateTimeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "INTERVIEWS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Interview {

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

    @Column(name = "INTERVIEW_COMMENT")
    private String comment;

    public Interview() {

        super();
    }

    public Interview(long id, Associate associate, Client client, Marketer marketer, InterviewStatuses interviewStatus, LocalDateTime scheduled) {

        super();
        this.id = id;
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

    public String getComment() {

        return comment;
    }

    public void setComment(String comment) {

        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Interview)) return false;
        Interview interview = (Interview) o;
        if (associate != null ? !associate.equals(interview.associate) : interview.associate != null) return false;
        if (client != null ? !client.equals(interview.client) : interview.client != null) return false;
        if (marketer != null ? !marketer.equals(interview.marketer) : interview.marketer != null) return false;
        if (interviewStatus != null ? !interviewStatus.equals(interview.interviewStatus) : interview.interviewStatus != null)
            return false;
        if (scheduled != null ? !scheduled.equals(interview.scheduled) : interview.scheduled != null) return false;
        return comment != null ? comment.equals(interview.comment) : interview.comment == null;
    }

    @Override
    public int hashCode() {

        int result = associate != null ? associate.hashCode() : 0;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (marketer != null ? marketer.hashCode() : 0);
        result = 31 * result + (interviewStatus != null ? interviewStatus.hashCode() : 0);
        result = 31 * result + (scheduled != null ? scheduled.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "Interview{" + "id=" + id + ", associate=" + associate + ", client=" + client + ", marketer=" + marketer + ", interviewStatus=" + interviewStatus + ", scheduled=" + scheduled + '}';
    }
}
