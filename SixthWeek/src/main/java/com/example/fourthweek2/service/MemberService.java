package com.example.fourthweek2.service;

import com.example.fourthweek2.store.Member;
import com.example.fourthweek2.store.MemberId;
import org.springframework.stereotype.Service;


@Service
public class MemberService {
    private MemberRepo memberRepo;

    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }
}