package hello.servlet.damain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepoositoryTest {

    MemberRepoository memberRepoository = MemberRepoository.getInstance(); // memberRepoository는 싱글톤이어서 new로 또 인스턴스 만들 수 없음

    // 각 테스트()를 진행할 때마다 객체를 지워준다
    @AfterEach
    void afterEach() {
        memberRepoository.clearStore();
    }

    @Test
    void getInstance() {
    }

    @Test
    void save() {
        // given
        Member member = new Member("hello", 20);

        // when
        Member saveMember = memberRepoository.save(member);

        // then
        Member findMember = memberRepoository.findById(saveMember.getId());
        assertThat(findMember).isEqualTo(saveMember);

    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        // given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepoository.save(member1);
        memberRepoository.save(member2);

        // when
        List<Member> result = memberRepoository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }

    @Test
    void clearStore() {
    }
}