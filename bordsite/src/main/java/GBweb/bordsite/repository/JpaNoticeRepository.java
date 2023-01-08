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
    public Notice write(Notice notice) {
        em.persist(notice);
        return notice;
    }

    @Override
    public Notice edit(Notice notice) {
        em.merge(notice);
        return notice;
    }

    @Override
    public Optional<Notice> findById(Long id) {
        Notice notice = em.find(Notice.class, id);
        notice.setCount(notice.getCount()+1L);
        em.merge(notice);
        return Optional.ofNullable(notice);
    }

    @Override
    public Optional<Notice> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Notice> findAll() {
        //System.out.println("!!!!!!!!!!!!!!!!!!jpa 진입!!!!!!!!!!!!!!!!");
        return em.createQuery("select m from Notice m order by id desc", Notice.class)
                .getResultList();
    }
}
