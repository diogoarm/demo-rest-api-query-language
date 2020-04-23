package diogoarm.demo.rsql.repository;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import diogoarm.demo.rsql.dao.rsql.DemoRsqlVisitor;
import diogoarm.demo.rsql.model.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest
public class DocumentRepositoryTests {

    @Autowired
    private DocumentRepository repository;

    private Document doc1;

    private Document doc2;

    @BeforeEach
    public void init() {
        doc1 = Document.builder()
                .title("repository test title 1")
                .subject("repository test subject 1")
                .body("repository test body 1")
                .createdAt(LocalDateTime.now())
                .build();
        repository.save(doc1);

        doc2 = Document.builder()
                .title("repository test title 2")
                .subject("repository test subject 2")
                .body("repository test body 2")
                .createdAt(LocalDateTime.now())
                .build();
        repository.save(doc2);
    }

    @Test
    public void shouldFindRecordByTitleLike() {
        final Node rootNode = new RSQLParser().parse("title==*title*1*");
        final Specification<Document> spec = rootNode.accept(new DemoRsqlVisitor<>());
        final List<Document> results = repository.findAll(spec);

        assertThat(doc1, is(in(results)));
        assertThat(doc2, not(in(results)));
    }

    @Test
    public void shouldfindRecordByTitleAndSubject() {
        final Node rootNode = new RSQLParser()
                .parse("title==repository*test*title*1;subject==repository*test*subject*1");
        final Specification<Document> spec = rootNode.accept(new DemoRsqlVisitor<>());
        final List<Document> results = repository.findAll(spec);

        assertThat(doc1, is(in(results)));
        assertThat(doc2, not(in(results)));
    }

}
