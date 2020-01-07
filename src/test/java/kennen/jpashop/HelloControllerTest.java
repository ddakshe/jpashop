package kennen.jpashop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
@AutoConfigureRestDocs(outputDir = "build/snippets")
public class HelloControllerTest {

//    @Rule
//    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/snippets");
//
//    @Autowired
//    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

//    @Before
//    public void setUp(){
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
//                .apply(documentationConfiguration(this.restDocumentation))
//                .build();
//    }

    @Test
    public void hello() throws Exception {
        this.mockMvc.perform(get("/hello2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"))
                .andDo(document("home"));
    }
}