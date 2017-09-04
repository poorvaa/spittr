package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.data.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
	
	private static final long MAXVALUE = Long.MAX_VALUE;//Get the long value first
	
	//to access the list of spittles from repo
	private SpittleRepository spittleRepository;

	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		
		this.spittleRepository = spittleRepository;
	}
	
	/*//to return list of 20 spittles
	@RequestMapping(method=RequestMethod.GET)
	public String spittles(Model model)
	{
		model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
		return "spittles";
	}*/
	
	//to handle query params
	@RequestMapping(method=RequestMethod.GET)
	public String spittles(
							@RequestParam(value="max",defaultValue = MAXVALUE+"") long max,
							@RequestParam(value="count",defaultValue="20") int count,
							Model model)
	{
		model.addAttribute(spittleRepository.findSpittles(max, count));
		return "spittles";
	}
	
	//to handle a single spittle request
	@RequestMapping(value="/{spittleId}",method=RequestMethod.GET)
	public String spittle(@PathVariable long spittleId,Model model)
	{
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}
	
	

}
