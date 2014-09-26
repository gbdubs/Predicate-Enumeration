package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import predicates.Predicate;
import predicates.Triplet;

public class PredicateProducer extends Thread{

	private boolean done;
	private LinkedBlockingQueue<Predicate> out;
	private int n;
	private int v;
	
	public PredicateProducer(LinkedBlockingQueue<Predicate> out, int n, int v){
		this.done = false;
		this.out = out;
		this.n = n;
		this.v = v;
	}
	
	public boolean done() {
		return this.done;
	}
	
	public String toString(){
		return "(N=" + n + " V=" + v +")";
	}
	
	public void run(){
		List<Triplet> allTriplets = Triplet.getAllPossibleTriplets(v);
		int termCondition = (int) Math.pow(allTriplets.size(), n);
		for(int i = 0; i < termCondition; i++){
			int[] tripletNumbers = Predicate.toBaseV(i, allTriplets.size(), n);
			List<Triplet> triplets = new ArrayList<Triplet>();
			for (int j = 0; j < n; j++){
				triplets.add(allTriplets.get(tripletNumbers[j]));
			}
			try {
				out.put(new Predicate(v, triplets));
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
		this.done = true;
	}

}
