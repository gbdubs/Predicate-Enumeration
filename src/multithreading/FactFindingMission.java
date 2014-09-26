package multithreading;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import predicates.Predicate;

public class FactFindingMission {
	
	
	public static void main(String[] args){
		factFind();
	}
	
	public static void factFind(){
		for (int n = 1; n < 8; n++){
			for (int v = 1; v < 8; v++){
				if (isFeasable(n,v)){
					runCalculation(n,v);
				} else {
					System.out.println("unfeasable for n=" + n + " v=" + v + " preds=" + Predicate.numberOfThreeSatPredicates(v, n));
				}
			}
		}
	}
	
	public static boolean isFeasable(int n, int v){
		if (Predicate.numberOfThreeSatPredicates(v, n) > 5000000){
			return false;
		}
		return true;
	}
	
	public static void runCalculation(int n, int v){
		long l = System.currentTimeMillis();
		LinkedBlockingQueue<Predicate> lbc = new LinkedBlockingQueue<Predicate>();
		PredicateProducer pp = new PredicateProducer(lbc, n, v);
		TestAndCounter tac = new TestAndCounter(lbc, pp);
		
		int numPredicates = Predicate.numberOfThreeSatPredicates(v, n);
		System.out.println("\nStarting calculations on n=" + n + ", v=" + v + ", num predicates=" + numPredicates);
		
		pp.start();
		tac.start();
		try {
			pp.join();
			tac.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int time = (int) (System.currentTimeMillis() - l + 1);
		System.out.println("calc time: " + time + " ms, " + (int) (numPredicates / time) + " pred per ms");
	}
	
	
}
