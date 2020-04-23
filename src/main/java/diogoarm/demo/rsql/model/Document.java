package diogoarm.demo.rsql.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Data @Builder
@Table(name = "documents")
public class Document {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String subject;

    private String body;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
