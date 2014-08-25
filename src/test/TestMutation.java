package test;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import evolutionary.Config;
import evolutionary.Individual;
import evolutionary.Mutation;
import evolutionary.Mutation.MutationType;
import evolutionary.Population;

public class TestMutation {

	public static void main(String[] args) {
		Population p = new Population(5,10);
		Config g = new Config();
		g=g.getInstance();
		
		Mutation insert = new Mutation(MutationType.INSERT);
		Mutation swap = new Mutation(MutationType.SWAP);
		Mutation inversion = new Mutation(MutationType.INVERSION);
		Mutation scramble = new Mutation(MutationType.SCRAMBLE);

		Mutation inver = new Mutation(MutationType.INVEROVER);
		Map<String, Point> mapp = new HashMap<String, Point>();
		
		for(int i=1; i<=10; i++){
			mapp.put(Integer.toString(i), new Point(0,i));
		}
		
		//System.out.println("ORIGINAL POP");
		g.setAlleleMap(mapp);
		
		for(int i=0; i<p.population.size(); i++){
			//System.out.println(p.population.get(i)+" .. F: "+g.calculateFitness(p.population.get(i)));
		}
		
		/*
		//System.out.println("INSERT");
		p=insert.mutate(p);
		//System.out.println(p);
		
		//System.out.println("SWAP");
		p=swap.mutate(p);
		//System.out.println(p);
		
		//System.out.println("INVERSION");
		p=inversion.mutate(p);
		//System.out.println(p);
		
		//System.out.println("SCRAMBLE");
		p=scramble.mutate(p);
		//System.out.println(p);
		*/
		//System.out.println("INVEROVER");
		p=inver.mutate(p);
		for(int i=0; i<p.population.size(); i++){
			//System.out.println(p.population.get(i)+" .. F: "+g.calculateFitness(p.population.get(i)));
		}
		
		//System.out.println("------------------------------------------------------");
	}
}
