package test;

import java.util.List;
import java.util.Arrays;

import org.junit.Test;

import predicates.Predicate;


public class TestPredicateEnum {
/*
	@Test
	public void testVectorEnum(){
		for (int i = 0; i < Triplet.numberOfPossibleTriplets(3); i++){
			Triplet t = Triplet.getPossibleTriplet(i, 3);
			System.out.println(t);
		}
	}
	
	@Test
	public void testBaseConversion(){
		for(int i = 0; i < 256; i++){
			System.out.println(Arrays.toString(Predicate.toBaseV(i, 2, 8)));
		}
	}
	
	@Test
	public void testAllTriplets(){
		List<Triplet> triplets = Triplet.getAllPossibleTriplets(2);
		for(Triplet t : triplets){
			System.out.println(t);
		}
		System.out.println("Number of triplets: " + triplets.size());
	}
	
	@Test
	public void testAllPredicates(){
		List<Predicate> predicates = Predicate.allThreeSatPredicates(3, 2);
		int unsatisfied = 0, satsified = 0;
		for(Predicate p : predicates){
			if (!p.satisfiable()){
				System.out.println("UNSATISFIABLE: " + p);
				unsatisfied++;
			} else {
				satsified++;
				//System.out.println(p);
			}
		}
		System.out.println("Number of predicates: " + (satsified + unsatisfied) + ", Number Unsatisfied: " + unsatisfied);	
	}
*/
	@Test
	public void testWarningInNumbers(){
		for (int v = 1; v < 8; v++){
			for (int n = 1; n < 8; n++){
				int p = Predicate.numberOfThreeSatPredicates(v, n);
				String s = "V=" + v + " N=" + n + " ==> " + p + " possible predicates.";
				System.out.println(s);
			}
		}
	}
}
