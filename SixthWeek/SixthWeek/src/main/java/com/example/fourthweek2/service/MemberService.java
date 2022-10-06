package com.example.sixthweek.service;

import com.example.sixthweek.store.Member;
import com.example.sixthweek.store.MemberId;
import org.springframework.stereotype.Service;


@Service
public class MemberService {
    private MemberRepo memberRepo;

    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }
}