package com.alive_backend.serviceimpl.mail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
class MailServiceTest {

    @Mock
    private JavaMailSender mockMailSender;
    @Mock
    private Logger mockLogger;

    @InjectMocks
    private MailService mailServiceUnderTest;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(mailServiceUnderTest, "from", "from");
        mailServiceUnderTest.logger = mockLogger;
    }

    @Test
    void testSendSimpleMail() {
        // Setup
        // Run the test
        mailServiceUnderTest.sendSimpleMail("to", "title", "content");

        // Verify the results
        // Confirm JavaMailSender.send(...).
        final SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setFrom("from");
//        simpleMessage.setReplyTo("replyTo");
        simpleMessage.setTo("to");
        simpleMessage.setSubject("title");
        simpleMessage.setText("content");
        verify(mockMailSender).send(simpleMessage);
        verify(mockLogger).info("邮件发送成功");
    }

//    @Test
//    void testSendSimpleMail_JavaMailSenderThrowsMailException() {
//        // Setup
//        // Configure JavaMailSender.send(...).
//        final SimpleMailMessage simpleMessage = new SimpleMailMessage();
//        simpleMessage.setFrom("from");
//        simpleMessage.setReplyTo("replyTo");
//        simpleMessage.setTo("to");
//        simpleMessage.setSubject("title");
//        simpleMessage.setText("content");
//        doThrow(MailException.class).when(mockMailSender).send(simpleMessage);
//
//        // Run the test
//        assertThatThrownBy(() -> mailServiceUnderTest.sendSimpleMail("to", "title", "content"))
//                .isInstanceOf(MailException.class);
//    }
}
