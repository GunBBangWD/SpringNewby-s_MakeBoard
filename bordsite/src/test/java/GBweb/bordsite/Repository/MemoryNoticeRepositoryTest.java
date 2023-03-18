package GBweb.bordsite.Repository;

import GBweb.bordsite.domain.Notice;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

        repository.write(notice);
        Notice result = repository.findById(notice.getId()).get();
        Assertions.assertThat(notice).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Notice notice1 = new Notice();
        notice1.setName("spring1");
        repository.write(notice1);

        Notice notice2 = new Notice();
        notice2.setName("spring2");
        repository.write(notice2);

        Notice result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(notice1);

    }

    @Test
    public void findALL(){
        Notice member1 = new Notice();
        member1.setName("spring1");
        repository.write(member1);

        Notice member2 = new Notice();
        member2.setName("spring2");
        repository.write(member2);

        List<Notice> result=repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }


}
