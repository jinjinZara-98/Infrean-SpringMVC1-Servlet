package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {
    //객체를 갖고올때 new로 못함, 스프링 자체가 싱글톤 보장
    MemberRepository memberRepository = MemberRepository.getInstance();

    //하나의 테스트 끝날때마다 Map객체 비워주기
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        //Member객체 생성
        Member member = new Member("hello", 20);

        //when
        //Map객체에 Member객체 저장하고 그 객체 반환
        Member savedMember = memberRepository.save(member);

        //then
        //반환된 객체의 id에 맞는 객체를 갖고와 비교
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        //모든 객체를 배열로 받음
        List<Member> result = memberRepository.findAll();

        //then
        //배열 크기가 2고 넣은 객체들을 포함하고 있는지
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}
