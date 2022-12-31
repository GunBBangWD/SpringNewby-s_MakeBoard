package GBweb.bordsite.service;

import GBweb.bordsite.domain.Notice;
import GBweb.bordsite.repository.NoticeRepository;

import java.util.List;
import java.util.Optional;

public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository memberRepository) {
        this.noticeRepository = memberRepository;
    }

    // 글 작성
    public Long write(Notice notice) {
        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(notice);
        noticeRepository.save(notice);
        return notice.getId();
    }

    private void validateDuplicateMember(Notice notice) {
        noticeRepository.findByName(notice.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Notice> findMembers() {
        return noticeRepository.findAll();
    }

    public Optional<Notice> findOne(Long memberId) {
        return noticeRepository.findById(memberId);
    }
}
