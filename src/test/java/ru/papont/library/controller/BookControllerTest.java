package ru.papont.library.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import ru.papont.library.dto.BookDto;
import ru.papont.library.entity.Book;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class BookControllerTest extends AbstractControllerTest {

    @Test
    void createBookValidation() throws Exception {
        BookDto testBook = mockBook();
        testBook.setName(null);
        testBook.setAuthor("");

        mockMvc.perform(post("/book").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBook)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", equalTo(HttpStatus.BAD_REQUEST.name())))
                .andExpect(jsonPath("$.errors", containsInAnyOrder("author is mandatory", "name is mandatory")));

        testBook = mockBook();
        testBook.setIsbn("wrong format");

        mockMvc.perform(post("/book").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBook)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", equalTo(HttpStatus.BAD_REQUEST.name())))
                .andExpect(jsonPath("$.errors", containsInAnyOrder("isbn must match \"^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})$)[\\d-]+$\"")));

    }

    @Test
    void getNotExistingBook() throws Exception {
        mockMvc.perform(get("/book/000"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("status", equalTo(HttpStatus.NOT_FOUND.name())))
                .andExpect(jsonPath("errors", contains("Book not found")));
    }

    @Test
    void createBook() throws Exception {
        BookDto testBook = mockBook();

        String json = objectMapper.writeValueAsString(testBook);
        MvcResult mvcResult = mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        Book bookResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Book.class);
        assertThat(bookResult.getId(), notNullValue());
        assertThat(bookResult.getName(), equalTo(testBook.getName()));
        assertThat(bookResult.getAuthor(), equalTo(testBook.getAuthor()));
        assertThat(bookResult.getDescription(), equalTo(testBook.getDescription()));
        assertThat(bookResult.getPublisher(), equalTo(testBook.getPublisher()));
        assertThat(bookResult.getYear(), equalTo(testBook.getYear()));
        assertThat(bookResult.getIsbn(), equalTo(testBook.getIsbn()));
    }

//    @Test
//    void editBook() throws Exception {
//        BookDto testBook = mockBook();
//
//        String json = objectMapper.writeValueAsString(testBook);
//        MvcResult mvcResult = mockMvc.perform(put("/book")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andDo(print())
//                .andExpect(status().isUpgradeRequired())
//                .andReturn();
//
//        Book bookResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Book.class);
//        assertThat(bookResult.getId(), notNullValue());
//        assertThat(bookResult.getName(), equalTo(testBook.getName()));
//        assertThat(bookResult.getAuthor(), equalTo(testBook.getAuthor()));
//        assertThat(bookResult.getDescription(), equalTo(testBook.getDescription()));
//        assertThat(bookResult.getPublisher(), equalTo(testBook.getPublisher()));
//        assertThat(bookResult.getYear(), equalTo(testBook.getYear()));
//        assertThat(bookResult.getIsbn(), equalTo(testBook.getIsbn()));
//    }


    @Test
    void findBook() throws Exception {
        BookDto testBook = mockBook();

        String json = objectMapper.writeValueAsString(testBook);
        mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated());

        mockMvc.perform(get("/book")
                        .param("page", "0")
                        .param("size", "1")
                        .param("query", "qwet"))
                .andDo(print())
                .andExpect(jsonPath("$.content", empty()))
                .andReturn();

        mockMvc.perform(get("/book")
                        .param("page", "0")
                        .param("size", "1")
                        .param("query", "author"))
                .andDo(print())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andReturn();

    }


    private BookDto mockBook() {
        BookDto testBook = new BookDto();
        testBook.setName("Test name");
        testBook.setDescription("Test description");
        testBook.setAuthor("Test author");
        testBook.setPublisher("Test publisher");
        testBook.setYear(2020);
        testBook.setIsbn("978-617-7866-64-9");
        return testBook;
    }

//    @Test
//    void getAllBooks() {
//    }
//

//    @Test
//    void deleteBook() {
//    }
}