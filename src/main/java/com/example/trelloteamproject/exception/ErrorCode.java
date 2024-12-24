package com.example.trelloteamproject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    //InvalidInputException
    //비밀번호가 틀렸을 때 출력하는 오류 메시지
    WRONG_PASSWORD("비밀 번호가 틀렸습니다.", HttpStatus.BAD_REQUEST),
    //아이디 비밀번호가 잘못됨
    DIFFERENT_EMAIL_PASSWORD("이메일 혹은 비밀번호가 잘못되었습니다.", HttpStatus.BAD_REQUEST),
    //탈퇴한 이메일로 가입을 시도할 때 출력하는 오류 메시지
    EMAIL_DELETED("삭제된 이메일 입니다.", HttpStatus.BAD_REQUEST),
    // 이미 삭제된 사용자가 조회 되었을 때 출력하는 오류 메시지
    DELETED_USER("이미 삭제된 회원입니다.", HttpStatus.BAD_REQUEST),

    // NotFoundException
    NOT_FOUND_MEMBER("멤버를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_CARD("카드를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // InternalServerException
    //세션이 만료되었을 때 출력하는 오류 메시지
    SESSION_TIMEOUT("세션이 만료되었습니다.", HttpStatus.SERVICE_UNAVAILABLE),
    //잘못된 요청
    WRONG_REQUEST("지원하지 않는 요청입니다.", HttpStatus.SERVICE_UNAVAILABLE),

    // DuplicatedException
    //중복된 이메일로 가일 할 때 출력하는 오류 메시지
    EMAIL_EXIST("중복된 아이디 입니다.", HttpStatus.BAD_REQUEST),

    // NoAuthorizedException
    //로그인이 안 되었을 떄 출력하는 오류 메시지
    NO_SESSION("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED),
    //이미 로그인이 되어있을 때 출력하는 오류 메시지
    ALREADY_LOGIN("이미 로그인이 되어있습니다.", HttpStatus.UNAUTHORIZED),
    //권한이 없는 사용자가 수정, 삭제를 하려고 할 때
    NO_AUTHOR_CHANGE("수정, 삭제는 작성자만 할 수 있습니다.", HttpStatus.UNAUTHORIZED);

    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
