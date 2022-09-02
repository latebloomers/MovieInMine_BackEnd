package com.latebloomers.MovieInMine.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter @ToString
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer partyId;

    // ex) aa's party
    private String partyName;
    private String OttServiceName;
    private String accountName;
    private String accountPassword;

    @Temporal(TemporalType.DATE)
    private Date paymentDate;

}
