package com.example.sixthweek.service;

import com.example.sixthweek.store.Member;
import com.example.sixthweek.store.MemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member, MemberId> {
}
