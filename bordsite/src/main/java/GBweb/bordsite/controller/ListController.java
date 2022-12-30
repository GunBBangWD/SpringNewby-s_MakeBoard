package GBweb.bordsite.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListController {
    /*private final MemberService memberService;

    @Autowired
    public ListController(MemberService memberService) {
        this.memberService = memberService;
    }*/

    @GetMapping("/list")
    public String listview(Model model) {
       /* List<Bord> bords = bordService.findBords();
        model.addAttribute("bords", bords);*/
        return "list";
    }
    @GetMapping("/view")
    public String view() {
        return "view";
    }
    @GetMapping("/edit")
    public String editview() {
        return "edit";
    }
    @GetMapping("/write")
    public String writeview() {
        return "write";
    }
}
