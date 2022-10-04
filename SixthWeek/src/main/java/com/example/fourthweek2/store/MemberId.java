package com.example.fourthweek2.store;

import lombok.Data;
import org.springframework.web.util.HtmlUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Embeddable
@Data
public class MemberId implements Serializable {
    @Column(name = "id")
    String id;

    @Column(name = "regdate")
    String regdate;

    public MemberId() {
        Date now = new Date();
        String strId = UUID.randomUUID().toString();
        String now2 = String.format("%tF", now) +" " +String.format("%tT", now);
        this.id = strId;
        this.regdate = now2;
    }

    public MemberId(String id, String regdate) {
        id = HtmlUtils.htmlEscape(id);
        regdate = HtmlUtils.htmlEscape(regdate);
        this.id = id;
        this.regdate = regdate;
    }
}
