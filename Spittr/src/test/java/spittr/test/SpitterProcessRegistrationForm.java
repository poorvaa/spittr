package spittr.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import spittr.data.SpitterRepository;
import spittr.model.Spitter;
import spittr.web.SpitterController;

public class SpitterProcessRegistrationForm {

	@Test
	public void shouldProcessRegistrationForm() throws Exception {
		
		//Mock repository
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		
		//create dummy data
		Spitter unsaved = new Spitter("jbauer","24hrs","Jack","Bauer");
		Spitter saved = new Spitter(24L,"jbauer","24hrs","Jack","Bauer");
		
		
		when(mockRepository.save(unsaved)).thenReturn(saved);
		
		
		SpitterController controller = new SpitterController(mockRepository);
		
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(post("/spitter/register")
				.param("username", "jbauer")
				.param("password", "24hours")
				.param("firstName", "Jack")
				.param("lastName", "Bauer")
						)
		.andExpect(redirectedUrl("/spitter/jbauer"));
		
		/*verify(mockRepository, atLeastOnce()).save(unsaved);*/
		
		
		
				}

}
