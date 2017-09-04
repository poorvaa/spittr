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

public class PagedSpittleControllerTest {

	@Test
	public void shouldShowPagedSpittles() throws Exception {
		
		//create dummy data
		List<Spittle> expectedSpittles = createSpittles(50);
		
		//mock the Spittlerepository
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		
		when(mockRepository.findSpittles(238900, 50)).
		thenReturn(expectedSpittles);
		
		SpittleController controller = new SpittleController(mockRepository);
		
		MockMvc mockMvc = standaloneSetup(controller)
							.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
							.build();
		
		mockMvc.perform(get("/spittles?max=238900&count=50"))
			   .andExpect(view().name("spittles"))
			   .andExpect(model().attributeExists("spittleList"))
			   .andExpect(model().attribute("spittleList",hasItems(expectedSpittles.toArray())));
		
	}

	private List<Spittle> createSpittles(int count) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for(int i=0;i<count;i++)
		{
			spittles.add(new Spittle("Spittle"+i,new Date()));
		}
		return spittles;
	}

}
