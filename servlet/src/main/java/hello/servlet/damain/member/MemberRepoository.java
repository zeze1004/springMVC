package hello.servlet.damain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepoository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepoository instance = new MemberRepoository(); // 싱글톤

    public static MemberRepoository getInstance() {                            // getInstance()를 통해 외부에서 객체를 얻을 수 있음
        return instance;                                                       // 동일한 객체를 반환함
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
