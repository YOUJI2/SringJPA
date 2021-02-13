package me.yjh.springTest6_5.sample;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// MOCK의 경우
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc
//public class SampleControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    public void hello() throws Exception {
//        mockMvc.perform(get("/hello"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("hello jihoon"))
//                .andDo(print());
//    }
//}

//RANDOM_PORT의 경우
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class SampleControllerTest {
//
//    @Autowired
//    TestRestTemplate testRestTemplate;
//
//    // samplecService 빈을 mock 빈으로 교체 해버린다 => 원본을 복사하여 바꾼것을 사용 )
//    @MockBean
//    SampleService mockSampleService;
//
//    @Test
//    public void hello() throws Exception {
//
//        when(mockSampleService.getName()).thenReturn("Whitejihoon");
//
//        String result = testRestTemplate.getForObject("/hello", String.class);
//        assertThat(result).isEqualTo("hello Whitejihoon");
//
//    }
//}


//webflux를 이용한 webclient 사용
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class SampleControllerTest {
//
//    @Autowired
//    WebTestClient webTestClient;
//
//    // samplecService 빈을 mock 빈으로 교체 해버린다 => 원본을 복사하여 바꾼것을 사용 )
//    @MockBean
//    SampleService mockSampleService;
//
//    @Test
//    public void hello() throws Exception {
//
//        when(mockSampleService.getName()).thenReturn("Whitejihoon");
//        webTestClient.get().uri("/hello").exchange()
//                .expectStatus().isOk()
//                .expectBody(String.class).isEqualTo("hello Whitejihoon");
//
//    }
//}


@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class SampleControllerTest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @MockBean
    SampleService mockSampleService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        when(mockSampleService.getName()).thenReturn("whitejihoon");

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello whitejihoon"));

        assertThat(outputCapture.toString())
                .contains("ArmyMan")
                .contains("skip");
    }
}




