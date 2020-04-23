package diogoarm.demo.rsql.controller;

import diogoarm.demo.rsql.model.Document;
import diogoarm.demo.rsql.repository.DocumentRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DocumentController.class)
public class DocumentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentRepository documentRepository;

    @Test
    public void shouldSearchByTitleAndReturnOk() throws Exception {

        final LocalDateTime testTime = LocalDateTime
                .of(2020, 4, 23, 13, 56, 0);

        when(documentRepository.findAll(any(Specification.class), any(PageRequest.class)))
                .thenReturn(new PageImpl(Lists.newArrayList(
                        new Document(1L, "title 1", "subject 1", "body 1", testTime, null)
                )));

        this.mockMvc.perform(get("/v1/documents?search=title==title-%201"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("title 1"))
                .andExpect(jsonPath("$.content[0].subject").value("subject 1"))
                .andExpect(jsonPath("$.content[0].body").value("body 1"))
                .andExpect(jsonPath("$.content[0].createdAt").value("2020-04-23T13:56:00"));
    }

    @Test
    public void shouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/v1/documents"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
