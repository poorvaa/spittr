/*package spittr.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import spittr.web.SpitterController;

public class SpitterShowRegistrationForm {

	@Test
	public void shouldShowRegistration() throws Exception{
		
		SpitterController controller = new SpitterController();
		
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		mockMvc.perform(get("/spitter/register"))
			   .andExpect(view().name("registerForm"));
		
	}

}
*/