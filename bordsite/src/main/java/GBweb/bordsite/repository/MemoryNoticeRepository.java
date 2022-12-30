package GBweb.bordsite.repository;

import GBweb.bordsite.domain.Notice;

import java.util.*;


public class MemoryNoticeRepository implements NoticeRepository {

    private static Map<Long, Notice> store = new HashMap<>();
    private static Long sequence = 0L;
    @Override
    public Notice save(Notice member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Notice> findById(Long id) {
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
