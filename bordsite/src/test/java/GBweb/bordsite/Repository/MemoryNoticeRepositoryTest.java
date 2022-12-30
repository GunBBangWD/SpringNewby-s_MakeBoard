package GBweb.bordsite.Repository;

import GBweb.bordsite.domain.Notice;
import GBweb.bordsite.repository.MemoryNoticeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MemoryNoticeRepositoryTest {
    MemoryNoticeRepository repository = new MemoryNoticeRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Notice notice = new Notice();
        notice.setName("spring");

        repository.save(notice);
        Notice result = repository.findById(notice.getId()).get();
        Assertions.assertThat(notice).isEqualTo(result);

    }


}
