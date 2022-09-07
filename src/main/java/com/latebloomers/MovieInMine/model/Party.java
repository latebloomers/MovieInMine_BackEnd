package com.latebloomers.MovieInMine.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @ToString
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ex) aa's party
    private String partyName;
    private String OttServiceName;
    private String accountName;
    private String accountPassword;

    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    // NullPointException을 방지하기 위해 ArrayList로 초기화
    @OneToMany(mappedBy = "party")
    private List<User> users = new ArrayList<>();



}
