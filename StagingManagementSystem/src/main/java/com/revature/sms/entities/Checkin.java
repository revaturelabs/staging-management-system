package com.revature.sms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.sms.util.LocalDateTimeConverter;

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
//TODO: Table needs to change? Dependent on Manager, cant be dependent on Manager
@Entity
@Table(name = "CHECKINS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Checkin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKIN_ID_SEQ")
    @SequenceGenerator(name = "CHECKIN_ID_SEQ", sequenceName = "CHECKIN_ID_SEQ")
    @Column(name = "CHECKIN_ID")
    private long id;

    @Column(name = "CHECKIN_IN_TIME")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime checkinTime;

    @Column(name = "CHECKIN_OUT_TIME")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime checkoutTime;

    
    //TODO:Needs to instead populate it by getting salesforce user? Somethings gotta change
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MANAGER_ID")
    private Manager approvedBy;

	@ManyToOne
	@JoinColumn(name = "ASSOCIATE_ID")
	private Associate associate;

    @ManyToOne
    @JoinColumn(name = "ASSOCIATE_ID")
    private Associate associate;

    public Checkin() {

        super();
    }

    public Checkin(long id, LocalDateTime checkinTime, LocalDateTime checkoutTime, Manager approvedBy, LocalDateTime approveTime, Associate associate) {

        super();
        this.id = id;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.approvedBy = approvedBy;
        this.approveTime = approveTime;
        this.associate = associate;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public LocalDateTime getCheckinTime() {

        return checkinTime;
    }

    public void setCheckinTime(LocalDateTime checkinTime) {

        this.checkinTime = checkinTime;
    }

    public LocalDateTime getCheckoutTime() {

        return checkoutTime;
    }

    public void setCheckoutTime(LocalDateTime checkoutTime) {

        this.checkoutTime = checkoutTime;
    }

    public Manager getApprovedBy() {

        return approvedBy;
    }

    public void setApprovedBy(Manager approvedBy) {

        this.approvedBy = approvedBy;
    }

    public LocalDateTime getApproveTime() {

        return approveTime;
    }

    public void setApproveTime(LocalDateTime approveTime) {

        this.approveTime = approveTime;
    }

    public Associate getAssociate() {

        return associate;
    }

    public void setAssociate(Associate associate) {

        this.associate = associate;
    }

		return "Checkin [id=" + id + ", checkinTime=" + checkinTime + ", checkoutTime=" + checkoutTime
				+ ", approveTime=" + approveTime + ", associate=" + associate + "]";
	}
}
