package com.example.sixthweek.store;

import lombok.*;
import org.springframework.web.util.HtmlUtils;

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
        name = HtmlUtils.htmlEscape(name);
        phoneNumber = HtmlUtils.htmlEscape(phoneNumber);
        address = HtmlUtils.htmlEscape(address);
        this.id = new MemberId();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Member(MemberId id, String name, String phoneNumber, String address) {
        name = HtmlUtils.htmlEscape(name);
        phoneNumber = HtmlUtils.htmlEscape(phoneNumber);
        address = HtmlUtils.htmlEscape(address);
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}