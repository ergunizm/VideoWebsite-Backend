package com.ergun.videos.business;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
