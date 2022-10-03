package com.example.fourthweek2.restapi;

import com.example.fourthweek2.service.CreateService;
import com.example.fourthweek2.service.MemberRepo;
import com.example.fourthweek2.store.Member;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

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
        name = HtmlUtils.htmlEscape(name);
        phoneNumber = HtmlUtils.htmlEscape(phoneNumber);
        address = HtmlUtils.htmlEscape(address);
        try {
            service.save(createService.member(name, phoneNumber, address));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return createService.member(HtmlUtils.htmlUnescape(name), HtmlUtils.htmlUnescape(phoneNumber), HtmlUtils.htmlUnescape(address));
    }

    @ApiOperation(value="회원 내역", notes="회원 내역")
    @GetMapping(value = "/read")
    public List<Member> readMember() {
        try {
            List<Member> list = service.findAll();
            list.stream().forEach(r -> {
                r.setPhoneNumber(HtmlUtils.htmlUnescape(r.getPhoneNumber()));
                r.setName(HtmlUtils.htmlUnescape(r.getName()));
                r.setAddress(HtmlUtils.htmlUnescape(r.getAddress()));
            });
            return list;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value="회원 내역 Key로 검색", notes="회원 내역 Key로 검색")
    @GetMapping(value = "/readKey")
    public Member readMemberByKey(@RequestParam String id, @RequestParam String regdate) {
        id = HtmlUtils.htmlEscape(id);
        regdate = HtmlUtils.htmlEscape(regdate);
        Member member = null;
        try {
            member =  service.findById(createService.memberId(id, regdate)).get();
            member.setId(createService.memberId(HtmlUtils.htmlUnescape(id), HtmlUtils.htmlUnescape(regdate)));
            member.setAddress(HtmlUtils.htmlUnescape(member.getAddress()));
            member.setName(HtmlUtils.htmlUnescape(member.getName()));
            member.setPhoneNumber(HtmlUtils.htmlUnescape(member.getPhoneNumber()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return member;
    }

    @ApiOperation(value="회원 내역 Key로 삭제", notes="회원 내역 Key로 삭제")
    @DeleteMapping(value = "/deleteKey")
    public void deleteMemberByKey(@RequestParam String id, @RequestParam String regdate) {
        id = HtmlUtils.htmlEscape(id);
        regdate = HtmlUtils.htmlEscape(regdate);
        try {
            service.deleteById(createService.memberId(id, regdate));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value="회원 내역 Key로 수정", notes="회원 내역 Key로 수정")
    @PutMapping(value = "/updateKey")
    public void updateMemberByKey(@RequestParam String id, @RequestParam String regdate, @RequestParam String name, @RequestParam String phoneNumber, @RequestParam String address) {
        id = HtmlUtils.htmlEscape(id);
        regdate = HtmlUtils.htmlEscape(regdate);
        name = HtmlUtils.htmlEscape(name);
        phoneNumber = HtmlUtils.htmlEscape(phoneNumber);
        address = HtmlUtils.htmlEscape(address);
        try {
            service.save(createService.member2(createService.memberId(id, regdate), name, phoneNumber, address));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
