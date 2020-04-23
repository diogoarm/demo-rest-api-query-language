package diogoarm.demo.rsql.controller;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import diogoarm.demo.rsql.dao.rsql.DemoRsqlVisitor;
import diogoarm.demo.rsql.model.Document;
import diogoarm.demo.rsql.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController @RequiredArgsConstructor
@RequestMapping("/v1/documents")
public class DocumentController {

    private final DocumentRepository documentRepository;

    @GetMapping
    public Page<Document> search(@RequestParam String search,
                                 @RequestParam(required = false, defaultValue = "0") Integer page,
                                 @RequestParam(required = false, defaultValue = "10") Integer size) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Document> spec = rootNode.accept(new DemoRsqlVisitor<>());
        return documentRepository.findAll(spec, PageRequest.of(page, size));
    }

}
