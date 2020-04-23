package diogoarm.demo.rsql.controller;

import diogoarm.demo.rsql.model.Document;
import diogoarm.demo.rsql.repository.DocumentRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

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
    public void shouldReturnStatusOk() throws Exception {

        final LocalDateTime testTime = LocalDateTime
                .of(2020, 4, 23, 13, 56, 00);

        when(documentRepository.findAll()).thenReturn(Lists.newArrayList(
                Document.builder()
                        .title("title 1")
                        .subject("subject 1")
                        .body("body 1")
                        .createdAt(testTime)
                        .build()
        ));

        this.mockMvc.perform(get("/v1/documents"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("title 1"))
                .andExpect(jsonPath("$[0].subject").value("subject 1"))
                .andExpect(jsonPath("$[0].body").value("body 1"))
                .andExpect(jsonPath("$[0].createdAt").value("2020-04-23T13:56:00"));
    }

}
