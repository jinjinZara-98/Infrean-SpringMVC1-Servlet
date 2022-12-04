package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

//lombok 필드로 되어있는 것들 자동으로 겟터 셋터 만들어주는
@Getter @Setter
public class Member {

    private Long id;
    private String username;
    private int age;

    //기본 생성자
    public Member(){

    }

    //생성자
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
