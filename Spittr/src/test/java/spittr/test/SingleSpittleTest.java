package spittr.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Date;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import spittr.data.SpittleRepository;
import spittr.model.Spittle;
import spittr.web.SpittleController;

public class SingleSpittleTest {

	@Test
	public void testSpittle() throws Exception {
		
		Spittle expectedSpittle = new Spittle("Hello World", new Date());
		
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		
		when(mockRepository.findOne(12345)).thenReturn(expectedSpittle);
		
		SpittleController controller = new SpittleController(mockRepository);
		
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		mockMvc.perform(get("/spittles/12345"))
			   .andExpect(view().name("spittle"))
			   .andExpect(model().attributeExists("spittle"))
			   .andExpect(model().attribute("spittle",expectedSpittle));
	}

}
