package com.js.qa.qatools.hr.user;

import org.springframework.stereotype.Service;

@Service
public class GenerateUser {

    // 패스워드 생성
    public String getneratePassword(String passwordType, String customPassword) {
        if(passwordType.equals("auto")){
            return String.valueOf((int)(Math.random() * 1000000 + 9999999));
        }
        return customPassword;
    }

    // 이메일 길이만큼 이름 생성
    public String generateName() {
        String name = "";
        int EMAIL_LENGTH = getEmailLength();
        for (int i = 0; i < EMAIL_LENGTH; i++) {
            name += getRandomChar();
        }
        return name;
    }

    // 이메일 생성
    public String generateEmail(String[] domainList) {
        int domainIndex = domainList.length;
        int EMAIL_LENGTH = getEmailLength();
        String emailAddress = "";

        for (int i = 0; i < EMAIL_LENGTH; i++) {
            emailAddress += getRandomChar();
        }

        int randomValue = (int) (Math.random() * 10);
        // 20% Append Numbering
        if (randomValue <= 2) {
            emailAddress += (int) (Math.random() * 1000);
        }
        // 30% Append close(.)
        else if (randomValue <= 3) {
            int replaceIndex = (int) (Math.random() * emailAddress.length() - 1);
            if (replaceIndex == 0) {
                replaceIndex += 1;
            }
            if (replaceIndex == emailAddress.length() - 1) {
                replaceIndex = (int) emailAddress.length() / 2;
            }

            // 특정 문자를 마침표로 치환하나 문자가 2개이상 포함된 상태의 경우
            // 첫번째 문자만 마침표로 치환
            String replaceChar = String.valueOf(emailAddress.charAt(replaceIndex));
            emailAddress = emailAddress.replaceFirst(replaceChar, ".");
        }

        // 도메인 Append 및 textarea 개행문자 제거
        emailAddress += "@" + domainList[(int) (Math.random() * domainIndex)].replaceAll(System.getProperty("line.separator"), "");

        return emailAddress;
    }

    // 이메일 아이디 길이 생성
    private int getEmailLength() {
        return (int) (Math.random() * 3 + 10);
    }

    // 영문[a-z] 소문자 생성
    private char getRandomChar() {
        char ch = (char) ((Math.random() * 26) + 97);
        return ch;
    }


}

