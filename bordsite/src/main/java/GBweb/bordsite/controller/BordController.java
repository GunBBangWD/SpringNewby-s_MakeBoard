package GBweb.bordsite.controller;


import GBweb.bordsite.domain.Notice;
import GBweb.bordsite.service.NoticeService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BordController {
    private final NoticeService noticeService;

    @Autowired
    public BordController(NoticeService noticeService) {this.noticeService = noticeService;}

    @GetMapping("/list={id}")
    public String listview(@PathVariable("id") int id, Model model) {
        List<Notice> notices = noticeService.findNotices();

        System.out.println(notices.size());
        List<List<Notice>> pageing = new ArrayList<List<Notice>>();
        List<Notice> t = new ArrayList<>();

        for(int i = 0;i<notices.size();i++){
            t.add(notices.get(i));
            if((i+1)%5==0){
                pageing.add(new ArrayList<>(t));
                t.clear();
            }
        }
        if(!t.isEmpty()){
            pageing.add(t);
        }

        List<Integer> pagenum = new ArrayList<>();
        for(int i=1;i<pageing.size()+1;i++){
            pagenum.add(i);
        }

        model.addAttribute("notices", (List<Notice>)pageing.get(id-1));
        model.addAttribute("noticesNum", pagenum);

        return "list";
    }


    @PostMapping("/write/new")
    public String create(NoticeFormWrite form) {
        Notice notice = new Notice();
        notice.setName(form.getName());
        notice.setTitle(form.getTitle());
        notice.setContents(form.getContents());
        notice.setPwd(form.getPwd());
        notice.setDay(LocalDate.now());
        notice.setCount(0L);
        noticeService.write(notice);

        return "redirect:/";
    }

    @GetMapping("/view{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        System.out.println(id);
        Optional<Notice> notices = noticeService.findOne(id);
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
    @GetMapping("/delete{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        System.out.println(id);
        Optional<Notice> notice = noticeService.findOne(id);
        noticeService.delete(notice.get());
        return "redirect:/";
    }

    @GetMapping("/write")
    public String writeview() {
        return "write";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/list=1";
    }
}
