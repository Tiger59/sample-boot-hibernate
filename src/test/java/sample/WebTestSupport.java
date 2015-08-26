package sample;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Springコンテナを用いたフルセットのWeb検証用途。
 * <p>Controllerに対するURL検証はこちらを利用して下さい。
 */
public abstract class WebTestSupport extends UnitTestSupport {

	protected static final Logger logger = LoggerFactory.getLogger("ControllerTest");

	@Autowired
	protected WebApplicationContext wac;

	protected MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	protected String url(String path) {
		return prefix() + path;
	}

	protected String prefix() {
		return "/";
	}
	
	protected MvcResult performGet(String path) throws Exception {
		return mockMvc.perform(get(url(path))).andExpect(status().isOk()).andReturn();
	}
	
	protected MvcResult performPost(String path) throws Exception {
		return mockMvc.perform(post(url(path))).andExpect(status().isOk()).andReturn();
	}
	
}