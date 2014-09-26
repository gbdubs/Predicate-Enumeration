package test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import predicates.Triplet;


public class TestEvaluation {

	@Test
	public void testTripletEval(){
		List<Integer> vars = new ArrayList<Integer>();
		List<Integer> expt = new ArrayList<Integer>();
		
		vars.add(0); vars.add(0); vars.add(0);
		expt.add(1); expt.add(0); expt.add(1);
		
		Triplet t = new Triplet(vars, expt);
		
		int[] vector = {1};
		Assert.assertEquals(true, t.evaluate(vector));
		vector[0] = 0;
		Assert.assertEquals(true, t.evaluate(vector));
		
		
	}
	
}
