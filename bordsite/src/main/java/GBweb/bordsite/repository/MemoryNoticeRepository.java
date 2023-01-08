package GBweb.bordsite.repository;

import GBweb.bordsite.domain.Notice;

import java.time.LocalDate;
import java.util.*;


public class MemoryNoticeRepository implements NoticeRepository {

    private static Map<Long, Notice> store = new HashMap<>();
    private static Long sequence = 0L;
    @Override
    public Notice write(Notice notice) {
        notice.setId(++sequence);
        notice.setCount(0L);
        notice.setDay(LocalDate.now());
        store.put(notice.getId(), notice);
        return notice;
    }

    @Override
    public Notice edit(Notice notice) {
        store.put(notice.getId(), notice);
        return notice;
    }

    @Override
    public Optional<Notice> findById(Long id) {
        Long coun = store.get(id).getCount();
        store.get(id).setCount(++coun);
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Notice> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Notice> findAll() {

        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
