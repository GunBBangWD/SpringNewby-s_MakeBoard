package GBweb.bordsite.service;

import GBweb.bordsite.domain.Notice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class NoticeServiceTest {

    NoticeService noticeService;
    MemoryNoticeRepository noticeRepository;

    @BeforeEach
    public void beforeEach() {
        noticeRepository = new MemoryNoticeRepository();
        noticeService = new NoticeService(noticeRepository);
    }

    @AfterEach
    public void afterEach() {
        noticeRepository.clearStore();
    }

    @Test
    void write() {
        Notice notice = new Notice();
        notice.setName("spring");
        notice.setTitle("spring");
        notice.setContents("spring");
        notice.setPwd("spring");
        notice.setCount(13L);
        notice.setDay(LocalDate.now());

        Long saveId = noticeService.write(notice);

        Notice findMember = noticeService.findOne(saveId).get();
        assertThat(notice.getName()).isEqualTo(findMember.getName());
        assertThat(notice.getTitle()).isEqualTo(findMember.getTitle());
        assertThat(notice.getContents()).isEqualTo(findMember.getContents());
        assertThat(notice.getPwd()).isEqualTo(findMember.getPwd());
        assertThat(notice.getCount()).isEqualTo(findMember.getCount());
        assertThat(notice.getDay()).isEqualTo(findMember.getDay());
    }

}
