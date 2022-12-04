package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용
    //싱글톤으로 생성
    private static final MemberRepository instance = new MemberRepository();
    //싱글톤이니 생성자로만 생성할 수 있게 설정
    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
    }

    //파라미터로 들어온 member에 id값 세팅하고 Map객체에 세팅
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    //id에 맞는 Member객체 반환
    public Member findById(Long id) {
        return store.get(id);
    }

    //Map객체에 모든걸 꺼내 새로운 배열에 담아 반환
    //이렇게하면 배열의 값을 설정해도 Map객체에는 영향이 안가게됨
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    //Map객체 비우기
    public void clearStore() {
        store.clear();
    }
}
