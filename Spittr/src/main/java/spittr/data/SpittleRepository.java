package spittr.data;

import java.util.List;

import spittr.model.Spittle;

public interface SpittleRepository {
	
	List<Spittle> findSpittles(long max, int count);
	Spittle findOne(long id);
}
