package GBweb.bordsite.repository;

import GBweb.bordsite.domain.Notice;

import java.util.List;
import java.util.Optional;


public interface NoticeRepository {
    Notice write(Notice notice);
    Notice edit(Notice notice);
    Optional<Notice> findById(Long id);
    Optional<Notice> findByName(String name);
    List<Notice> findAll();
}
