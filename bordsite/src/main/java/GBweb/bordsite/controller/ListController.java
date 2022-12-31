package GBweb.bordsite.controller;


import GBweb.bordsite.domain.Notice;
import GBweb.bordsite.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ListController {
    private final NoticeService noticeService;

    @Autowired
    public ListController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/list")
    public String listview(Model model) {
        List<Notice> notices = noticeService.findMembers();
        model.addAttribute("notices", notices);
        return "list";
    }
    @PostMapping("/notice/new")
    public String create(NoticeForm form) {
        Notice notice = new Notice();
        notice.setName(form.getName());
        notice.setTitle(form.getTitle());
        notice.setContents(form.getContents());

        noticeService.join(notice);

        return "redirect:/";
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
