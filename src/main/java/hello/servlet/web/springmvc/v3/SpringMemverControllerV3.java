package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 스프링 MVC - 실용적인 방식
 * 1. ModelView를 개발자가 직접 생성해서 반환했기 때문에 불편 -> ViewName 직접 반환, Model 파라미터
 * 2. 요청 파라미터 파싱의 번거로움 개선 -> 메소드의 파라미터로 받기
 * 3. HTTP Method 구분 -> 용도에 맞도록 제한
 */
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemverControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    //@RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }

    @PostMapping("/save")
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {
        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    //springmvc/v2/members
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }
}
