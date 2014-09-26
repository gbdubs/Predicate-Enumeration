package multithreading;

import java.util.concurrent.LinkedBlockingQueue;

import predicates.Predicate;

public class TestAndCounter extends Thread{
	
	public LinkedBlockingQueue<Predicate> in;
	public PredicateProducer partner;
	
	public TestAndCounter(LinkedBlockingQueue<Predicate> in, PredicateProducer partner){
		this.in = in;
		this.partner = partner;
	}
	
	public void run(){
		int total = 0;
		int unsatisfiable = 0;
		while(!partner.done() || !in.isEmpty()){
			Predicate p;
			try {
				p = in.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
			if (!p.satisfiable()){
				unsatisfiable++;
			}
			total++;
		}
		System.out.println(partner.toString() + ":  unsatisfiable = " + unsatisfiable + "  total =" + total);
	}
}
