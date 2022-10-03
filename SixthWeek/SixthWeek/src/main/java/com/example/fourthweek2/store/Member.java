package com.example.fourthweek2.store;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="member")
public class Member{

    @EmbeddedId
    MemberId id;

    @Column(name = "name")
    String name;

    @Column(name = "address")
    String address;

    @Column(name = "phoneNumber", unique = true)
    String phoneNumber;

    public Member(String name, String phoneNumber, String address) {
        this.id = new MemberId();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Member(MemberId id, String name, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}