package GBweb.bordsite.controller;


import GBweb.bordsite.domain.Notice;
import GBweb.bordsite.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class BordController {
    private final NoticeService noticeService;

    @Autowired
    public BordController(NoticeService noticeService) {this.noticeService = noticeService;}

    @GetMapping("/list")
    public String listview(Model model) {
        List<Notice> notices = noticeService.findNotices();
        model.addAttribute("notices", notices);
        return "list";
    }
    @PostMapping("/notice/new")
    public String create(NoticeFormWrite form) {
        Notice notice = new Notice();
        notice.setName(form.getName());
        notice.setTitle(form.getTitle());
        //notice.setContents(form.getContents().replace("\n","<br/>"));
        notice.setContents(form.getContents());
        notice.setPwd(form.getPwd());
        noticeService.write(notice);

        return "redirect:/list";
    }

    @GetMapping("/view{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        System.out.println(id);
        Optional<Notice> notices = noticeService.findOne(id);
//        notices.get().setContents(notices.get().getContents().replace("\n","<br/>"));
        model.addAttribute("notices", notices.get());
        return "view";
    }
    @GetMapping("/edit{id}")
    public String editview(@PathVariable("id") Long id, Model model) {
        System.out.println(id);
        Optional<Notice> notice = noticeService.findOne(id);
        model.addAttribute("notices", notice.get());
        return "edit";
    }
    @PostMapping("/edit{id}")
    public String editview(@PathVariable("id") Long id, NoticeFormWrite form) {
        System.out.println(id);
        Optional<Notice> notice = noticeService.findOne(id);
        notice.get().setTitle(form.getTitle());
        notice.get().setName(form.getName());
        notice.get().setPwd(form.getPwd());
        notice.get().setContents(form.getContents());
        noticeService.edit(notice.get());
        return "redirect:/view"+id;
    }
    @GetMapping("/write")
    public String writeview() {
        return "write";
    }
}
