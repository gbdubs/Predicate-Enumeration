package predicates;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Predicate {

	private List<Triplet> triplets;
	private int v;
	
	public Predicate(int v, List<Triplet> triplets){
		this.v = v;
		this.triplets = triplets;
	}
	
	public boolean satisfiable(){
		for (int[] vector : this.getAllVectors()){
			if (this.evaluate(vector)){
				return true;
			}
		}
		return false;
	}
	
	public boolean evaluate(int[] vector){
		for (Triplet triplet : triplets){
			if (! triplet.evaluate(vector)){
				return false;
			}
		}
		return true;
	}
	
	private Set<int[]> getAllVectors(){
		Set<int[]> vectorSet = new HashSet<int[]>();
		for (int i = 0; i < Math.pow(2,v); i++){
			int[] vector = new int[v];
			int value = i;
			int divisor = (int) Math.pow(2, v - 1);
			for (int j = 0; j < v; j++){
				vector[j] = value / divisor;
				value = value % divisor;
				divisor = divisor / 2;
			}
			vectorSet.add(vector);
		}
		return vectorSet;
	}

	public String toString(){
		String result = "";
		for (Triplet t : triplets){
			result += " ^ " + t.toString();
		}
		return result.substring(3);
	}
	
	public static Predicate randomPredicate(int v){
		int n = (int) (10 * Math.random() + 1);
		List<Triplet> triplets = new ArrayList<Triplet>();
		for (int i = 0; i < n; i++){
			triplets.add(Triplet.randomTriplet(v));
		}
		return new Predicate(v, triplets);
	}
	
	public static List<Predicate> allThreeSatPredicates(int v, int n){
		List<Predicate> result = new ArrayList<Predicate>();
		List<Triplet> allTriplets = Triplet.getAllPossibleTriplets(v);
		int termCondition = (int) Math.pow(allTriplets.size(), n);
		for(int i = 0; i < termCondition; i++){
			int[] tripletNumbers = toBaseV(i, allTriplets.size(), n);
			List<Triplet> triplets = new ArrayList<Triplet>();
			for (int j = 0; j < n; j++){
				triplets.add(allTriplets.get(tripletNumbers[j]));
			}
			result.add(new Predicate(v, triplets));
		}
		return result;
	}
	
	public static Predicate getThreeSatPredicate(int i, int v, int n){
		int[] tripletNumbers = toBaseV(i, Triplet.numberOfPossibleTriplets(v), n);
		List<Triplet> triplets = new ArrayList<Triplet>();
		for (int j = 0; j < n; j++){
			triplets.add(Triplet.getPossibleTriplet(tripletNumbers[j], v));
		}
		return new Predicate(v, triplets);
	}
	
	public static int numberOfThreeSatPredicates(int v, int n){
		return (int) Math.pow(Triplet.numberOfPossibleTriplets(v), n);
	}
	
	public static int[] toBaseV(int n, int v, int l){
		int[] result = new int[l];
		
		for(int i = l - 1; i >= 0; i--){
			result[i] = n % v;
			n = n / v;
		}
		
		return result;
	}
	
}
