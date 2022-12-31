package GBweb.bordsite.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

public class NoticeForm {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String contents;
    @Getter @Setter
    private String pwd;

}
