package es.caser.spring.mvc.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import es.caser.spring.mvc.model.User;
import es.caser.spring.mvc.repository.IUserRepository;

public class UserControllerTest {
	@Test
	public void shouldProcessRegistration() throws Exception {
		IUserRepository mockRepository = mock(IUserRepository.class);
		User unsaved = new User("jbauer", "24hours", "Jack", "Bauer");
		User saved = new User(24L, "jbauer", "24hours", "Jack", "Bauer");
		when(mockRepository.save(unsaved)).thenReturn(saved);
		UserController controller = new UserController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(post("/users/register").param("firstName", "Jack").param("lastName", "Bauer")
				.param("username", "jbauer").param("password", "24hours")).andExpect(redirectedUrl("/users/jbauer"));
		
	}
	@Test
	public void should_ReturnRegisterFormView_whenRequestedOk() throws Exception {	
		IUserRepository mockRepository = mock(IUserRepository.class);
		UserController controller = new UserController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/registerForm.jsp")).build();
		/**
		 * infiere el nombre del tipo y la coleccion
		 */
		mockMvc.perform(get("/users/register")).andExpect(view().name("registerForm"));
	}
	
	@Test
	public void should_FindUser_whenRequestingByUsername() throws Exception {
		User user = createUser("bilbo","bolson","bbolson","comarca");
		IUserRepository mockRepository = mock(IUserRepository.class);
		when(mockRepository.findByUsername("bbolson")).thenReturn(user);

		UserController controller = new UserController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/users/bbolson")).andExpect(view().name("profile"))
				.andExpect(model().attributeExists("user"))
				.andExpect(model().attribute("user", user));

	}
	private User createUser(String name, String surname, String username, String password) {
		return new User(name, surname, username, password);
	}
}
