package predicates;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Triplet {
	
	private List<Integer> variables;
	private List<Integer> expectations;
	
	public Triplet(List<Integer> vars, List<Integer> expt){
		this.variables = vars;
		this.expectations = expt;
	}
	
	public boolean evaluate(int[] vector){
		for (int i = 0; i < 3; i++){
			if (expectations.get(i) == vector[variables.get(i)]){
				return true;
			}
		}
		return false;
	}
	
	public String toString(){
		String result = "";
		for (int i = 0; i < 3; i++){
			result += " v ";
			if (expectations.get(i) == 0){
				result += "!";
			}
			result += numToVariable(variables.get(i));
		}
		return "(" + result.substring(3) + ")";
	}
	
	private static char numToVariable(int i){
		return (char) (65 + i);
	}

	public static Triplet randomTriplet(int v) {
		Map<Integer, Integer> mapping = new HashMap<Integer, Integer>();
		ArrayList<Integer> vars = new ArrayList<Integer>();
		ArrayList<Integer> exps = new ArrayList<Integer>();
		
		for (int i = 0; i < 3; i++){
			int newKey = (int) (v * Math.random());
			while (mapping.keySet().contains(newKey)){
				newKey = (int) (v * Math.random());
			}
			vars.add(newKey);
			exps.add((int) (2 * Math.random()));
		}
		return new Triplet(vars, exps);
	}

	public static int numberOfPossibleTriplets(int v){
		return (int) Math.pow(2*v, 3);
	}
	
	public static Triplet getPossibleTriplet(int n, int v){
		int a = n / (4 * v * v);
		n = n % (4 * v * v);
		int b = n / (2 * v);
		n = n % (2 * v);
		int c = n;
		
		ArrayList<Integer> vars = new ArrayList<Integer>();
		ArrayList<Integer> exps = new ArrayList<Integer>();
		vars.add(a / 2); exps.add(a % 2);
		vars.add(b / 2); exps.add(b % 2);
		vars.add(c / 2); exps.add(c % 2);
		
		Triplet t = new Triplet(vars, exps);
		return t;
	}
	
	public static List<Triplet> getAllPossibleTriplets(int v){
		ArrayList<Triplet> result = new ArrayList<Triplet>();
		for(int i = 0; i < numberOfPossibleTriplets(v); i++){
			result.add(getPossibleTriplet(i, v));
		}
		return result;
	}
	
}


