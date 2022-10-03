package com.example.fourthweek2.service;

import com.example.fourthweek2.store.Member;
import com.example.fourthweek2.store.MemberId;
import org.springframework.stereotype.Service;

@Service
public class CreateService {

    CreateService() {}

    public Member member(String name, String address, String phoneNumber) {
        MemberId id = new MemberId();
        Member member = new Member(id, name, address, phoneNumber);
        return member;
    }

    public Member member2(MemberId id, String name, String address, String phoneNumber) {
        Member member = new Member(id, name, address, phoneNumber);
        return member;
    }

    public MemberId memberId(String uuid, String regdate) {
        MemberId memberId = new MemberId(uuid, regdate);
        return memberId;
    }
}
