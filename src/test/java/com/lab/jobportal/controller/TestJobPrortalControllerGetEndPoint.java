package com.lab.jobportal.controller;

import static com.lab.jobportal.filter.SecurityConstants.JWT_CONTEXT_PATH;
import static com.lab.jobportal.filter.SecurityConstants.TOKEN_PREFIX;
import static com.lab.jobportal.filter.SecurityConstants.USER_CONTEXT_PATH;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasXPath;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.xml.HasXPath;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.util.NestedServletException;

import com.lab.jobportal.config.JwtAuthenticationEntryPoint;
import com.lab.jobportal.config.JwtRequestFilter;
import com.lab.jobportal.config.JwtTokenUtil;
import com.lab.jobportal.config.WebSecurityConfig;
import com.lab.jobportal.security.UserDetailsServiceImpl;
import com.lab.jobportal.users.ApplicationUserRepository;
import com.lab.jobportal.users.ApplicationUserRepositoryImpl;

/**
 * rathr1
 * 
 **/
//@Ignore
/*
 * @RunWith(SpringJUnit4ClassRunner.class) //@WebAppConfiguration
 * 
 * @ContextConfiguration(classes = { ApplicationUserRepositoryImpl.class,
 * BCryptPasswordEncoder.class })
 */
//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@WebMvcTest(controllers = { JwtJobPortalController.class, JwtAuthenticationController.class, UserController.class })
@ContextConfiguration(classes = { ApplicationUserRepositoryImpl.class, 
		BCryptPasswordEncoder.class,
		JwtTokenUtil.class,
		UserDetailsServiceImpl.class,
		JwtAuthenticationEntryPoint.class,
		JwtRequestFilter.class})
@Import(WebSecurityConfig.class)
public class TestJobPrortalControllerGetEndPoint {

	//@Autowired
	private MockMvc userControllerMvc;
	private MockMvc jwtAuthControllerMvc;
	private MockMvc mvc;
	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	@Autowired
	@Qualifier("BCryptPasswordEncoder")
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	
	private static String TOKEN = "";

	/*
	 * @Bean public BCryptPasswordEncoder bCryptPasswordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
	
	private String content = "{" + "\"username\" : \"rahul\"," + "\"password\" : \"rahul\"" + "}";

	private HttpHeaders getBasicAuthHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", TOKEN_PREFIX + " " + TOKEN);
		return headers;
	}

	@Before
	public void setUp() throws Exception {

		userControllerMvc = MockMvcBuilders.standaloneSetup(new UserController(applicationUserRepository, bCryptPasswordEncoder))
				.build();
		jwtAuthControllerMvc = MockMvcBuilders.standaloneSetup(new JwtAuthenticationController(authenticationManager,jwtTokenUtil,userDetailsService))
				.build();
		userControllerMvc.perform(post(USER_CONTEXT_PATH + "/sign-up").contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isOk());

		/*
		 * mvc.perform(post(USER_CONTEXT_PATH +
		 * "/sign-up").contentType(MediaType.APPLICATION_JSON).content(content))
		 * .andExpect(status().isOk());
		 */

		String response = jwtAuthControllerMvc.perform(
				post(USER_CONTEXT_PATH + "/authenticate").contentType(MediaType.APPLICATION_JSON).content(content))
				.andReturn().getResponse().getContentAsString();
		JSONObject token = new JSONObject(response);
		TOKEN = token.getString("token");
		// .getHeader("Authorization").split(" ")[1];

		/*
		 * String token = JWTAuthenticationFilter.getToken("rahul", "rahul"); TOKEN =
		 * token.split(" ")[1];
		 */

		/*
		 * mvc = MockMvcBuilders.standaloneSetup(new JwtJobPortalController()).build();
		 */
		mvc = MockMvcBuilders.standaloneSetup(new JwtJobPortalController()).build();
	}

	@Test
	public void test_Get_all_end_point_with_json_data() throws Exception {
		mvc.perform(
				get(JWT_CONTEXT_PATH + "/all").headers(getBasicAuthHeader()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].jobId", is(1)));
	}

	@Test
	public void test_Get_all_end_point_with_xml_data() throws Exception {
		mvc.perform(get(JWT_CONTEXT_PATH + "/all").header("Accept", MediaType.APPLICATION_XML_VALUE))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML_VALUE))
				.andExpect(content().node(HasXPath.hasXPath("/List/item[1]/jobId", equalTo("1"))));
	}

	@Test
	public void test_Get_witid_end_point_with_json_data() throws Exception {
		mvc.perform(get(JWT_CONTEXT_PATH + "/find?id=1&jobTitle=Software Engg").header("Accept",
				MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("jobId", is(1))).andExpect(jsonPath("jobTitle", is("Software Engg")));

	}

	@Test
	public void test_Get_witid_end_point_with_xml_data() throws Exception {
		mvc.perform(get(JWT_CONTEXT_PATH + "/find?id=1&jobTitle=Software Engg").header("Accept",
				MediaType.APPLICATION_XML_VALUE)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML_VALUE))
				.andExpect(content().node(hasXPath("/Job/jobTitle", equalTo("Software Engg"))));

	}

	@Test()
	public void test_Get_witid_End_point_not_found() throws Exception {
		try {
			mvc.perform(get(JWT_CONTEXT_PATH + "/find?id=2&jobTitle=Software Engg").header("Accept",
					MediaType.APPLICATION_XML_VALUE)).andExpect(status().isNotFound());
		}catch (Exception e) {
			Assert.isInstanceOf(NestedServletException.class, e);
			Assert.hasText(e.getMessage(), "com.lab.jobportal.exception.JobNotFoundException");
		}
		

	}

	@Test
	public void test_Get_witid_end_point_missing_query_param() throws Exception {
		mvc.perform(get(JWT_CONTEXT_PATH + "/find?jobTitle=Software Engg").header("Accept",
				MediaType.APPLICATION_XML_VALUE)).andExpect(status().isBadRequest());

	}
}
