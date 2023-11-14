package hello.servlet.web.springmvc.v3;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
	private MemberRepository memberRepository = MemberRepository.getInstance();

	// @RequestMapping(value = "/new-form", method = RequestMethod.GET)
	@GetMapping("/new-form")
	public String newForm() {
		return "new-form";
	}

	// @RequestMapping(value = "/save", method = RequestMethod.POST)
	@PostMapping("/save")
	// HttpRequest 안 써도 스프링은 HTTP 요청 파라미터를 @RequestParam으로 받을 수 있음
	public String save(
		@RequestParam("username") String username,
		@RequestParam("age") int age,
		Model model) {

		Member member = new Member(username, age);
		memberRepository.save(member);

		model.addAttribute("member", member);
		return "save-result";
	}

	@GetMapping
	// @RequestMapping(method = RequestMethod.GET)
	public String members(Model model) {
		List<Member> members = memberRepository.findAll();

		model.addAttribute("members", members);
		return "members";
	}
}
