package diogoarm.demo.rsql.repository;

import diogoarm.demo.rsql.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
