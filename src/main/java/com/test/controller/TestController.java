package com.test.controller;

import com.test.payload.EmailList;
import com.test.util.EmailUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    private final EmailUtil emailUtil;

    public TestController(EmailUtil emailUtil) {
        this.emailUtil = emailUtil;
    }

    @PostMapping(value = {"/email-test"})
    public ResponseEntity<String> forgotPassword(@RequestBody Queue<EmailList> emails) {

        Queue<EmailList> queue = emails;

        Iterator<EmailList> itr = queue.iterator();
        while (itr.hasNext()) {

            EmailList next = itr.next();
            String email = next.getEmail();
            String name = next.getName();

            String subject = "Testing purpose email for multiple user";
            String body = name + "sir How are you...!!!";
            emailUtil.sendMail(email, subject, body);

        }

//        String sendTo = email;
//        String subject = "Testing purpose email for multiple user";
//        String body = "I got your email";
//        emailUtil.sendMail(sendTo, subject, body);
//
        return new ResponseEntity<>("Email has been sent", HttpStatus.OK);

    }
}
