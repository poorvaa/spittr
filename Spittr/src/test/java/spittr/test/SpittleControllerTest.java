package spittr.test;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import spittr.data.SpittleRepository;
import spittr.model.Spittle;
import spittr.web.SpittleController;

public class SpittleControllerTest {

	@Test
	public void shouldShowRecentSpittles() throws Exception {
		
		//create dummy spittles list of 20 spittle objects
		List<Spittle> expectedSpittles = createSpittleList(20);
		
		//create mock for SpittleRepository
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		
		//tells what to return when method of repo is called
		when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).
		thenReturn(expectedSpittles);
		
		//pass mock to spittleController i.e. perform injrction
		SpittleController spittleController = new SpittleController(mockRepository);
		
		//mock mvc
		MockMvc  mockMvc = standaloneSetup(spittleController)
						   .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
						   .build();
		
		mockMvc.perform(get("/spittles"))
			   .andExpect(view().name("spittles"))
			   .andExpect(model().attributeExists("spittleList"))
			   .andExpect(model().attribute("spittleList",hasItems(expectedSpittles.toArray())));
		
		
		
	}

	private List<Spittle> createSpittleList(int count) {
		List <Spittle> spittles = new ArrayList<Spittle>();
		for(int i =0;i<count;i++)
		{
			spittles.add(new Spittle("Spittle"+i,new Date()));
			
		}
		return spittles;
	}

}
