package GBweb.bordsite.controller;

import lombok.Getter;
import lombok.Setter;

public class NoticeFormWrite {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String contents;
    @Getter @Setter
    private String pwd;
    @Getter @Setter
    private Long count;
}
