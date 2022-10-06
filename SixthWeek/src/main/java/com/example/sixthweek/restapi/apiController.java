package com.example.sixthweek.restapi;

import com.example.sixthweek.service.CreateService;
import com.example.sixthweek.service.MemberRepo;
import com.example.sixthweek.store.Member;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class apiController {

    private final MemberRepo service;
    private final CreateService createService;

    ResponseEntity<?> entity = null;

    @ApiOperation(value="회원 등록", notes="회원 등록")
    @PostMapping(value="/register")
    public Member registerMember(@RequestParam String name, @RequestParam String phoneNumber, @RequestParam String address) {
        try {
            service.save(createService.member(name, phoneNumber, address));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return createService.member(name, phoneNumber, address);
    }

    @ApiOperation(value="회원 내역", notes="회원 내역")
    @GetMapping(value = "/read")
    public List<Member> readMember() {
        try {
            return service.findAll();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value="회원 내역 Key로 검색", notes="회원 내역 Key로 검색")
    @GetMapping(value = "/readKey")
    public Member readMemberByKey(@RequestParam String id, @RequestParam String regdate) {
        Member member = null;
        try {
            member =  service.findById(createService.memberId(id, regdate)).get();
            member.setId(createService.memberId(id, regdate));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return member;
    }

    @ApiOperation(value="회원 내역 Key로 삭제", notes="회원 내역 Key로 삭제")
    @DeleteMapping(value = "/deleteKey")
    public void deleteMemberByKey(@RequestParam String id, @RequestParam String regdate) {
        try {
            service.deleteById(createService.memberId(id, regdate));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value="회원 내역 Key로 수정", notes="회원 내역 Key로 수정")
    @PutMapping(value = "/updateKey")
    public void updateMemberByKey(@RequestParam String id, @RequestParam String regdate, @RequestParam String name, @RequestParam String phoneNumber, @RequestParam String address) {
        try {
            service.save(createService.member2(createService.memberId(id, regdate), name, phoneNumber, address));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
