package GBweb.bordsite.repository;

import GBweb.bordsite.domain.Notice;

import java.util.List;
import java.util.Optional;


public interface NoticeRepository {
    Notice save(Notice member);
    Optional<Notice> findById(Long id);
    Optional<Notice> findByName(String name);
    List<Notice> findAll();
}
