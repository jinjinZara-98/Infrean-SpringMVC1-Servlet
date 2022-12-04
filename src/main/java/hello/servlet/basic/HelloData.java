package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

//lombok라이브러리를 다운받아 사용가능
//필드 생성하면 자동으로 겟터 셋터 생성됨
@Getter @Setter
public class HelloData {

    private String username;
    private int age;

//    //==== lombok 생성 코드 ====//
//    public String getUsername() {
//        return username;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
//    public int getAge() {
//        return age;
//    }
//    public void setAge(int age) {
//        this.age = age;
//    }
}
