package diogoarm.demo.rsql.controller;

import diogoarm.demo.rsql.model.Document;
import diogoarm.demo.rsql.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController @RequiredArgsConstructor
@RequestMapping("/v1/documents")
public class DocumentController {

    private final DocumentRepository documentRepository;

    @GetMapping
    public List<Document> search() {
        return documentRepository.findAll();
    }

}
