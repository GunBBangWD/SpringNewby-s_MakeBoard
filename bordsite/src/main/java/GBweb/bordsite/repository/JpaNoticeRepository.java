package GBweb.bordsite.repository;

import GBweb.bordsite.domain.Notice;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaNoticeRepository implements NoticeRepository{
    private final EntityManager em;
    public JpaNoticeRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Notice save(Notice notice) {
        return null;
    }

    @Override
    public Notice edit(Notice notice) {
        return null;
    }

    @Override
    public Optional<Notice> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Notice> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Notice> findAll() {
        System.out.println("!!!!!!!!!!!!!!!!!!jpa 진입!!!!!!!!!!!!!!!!");
        return em.createQuery("select m from Notice m", Notice.class)
                .getResultList();
    }
}
