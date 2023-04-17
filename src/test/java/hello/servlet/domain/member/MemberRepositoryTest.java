package hello.servlet.domain.member;

import hello.servlet.basic.domain.member.Member;
import hello.servlet.basic.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("cjl", 31);

        //when
        memberRepository.save(member);

        //then
        Member foundMember = memberRepository.findById(member.getId());
        assertThat(foundMember).isEqualTo(member);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("최재량", 31);
        Member member2 = new Member("최재림", 28);
        Member member3 = new Member("김봄이", 6);

        //when
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        //then
        List<Member> result = memberRepository.findAll();
        assertThat(result.size()).isEqualTo(3);
        assertThat(result).contains(member1, member2, member3);
    }
}
