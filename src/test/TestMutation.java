package test;

import evolutionary.Individual;
import evolutionary.Mutation;
import evolutionary.Mutation.MutationType;
import evolutionary.Population;

public class TestMutation {

	public static void main(String[] args) {
		Population p = new Population(5,10);

		Mutation insert = new Mutation(MutationType.INSERT);
		Mutation swap = new Mutation(MutationType.SWAP);
		Mutation inversion = new Mutation(MutationType.INVERSION);
		Mutation scramble = new Mutation(MutationType.SCRAMBLE);
			
		System.out.println("ORIGINAL POP");
		System.out.println(p);
		
		System.out.println("INSERT");
		p=insert.mutate(p);
		System.out.println(p);
		
		System.out.println("SWAP");
		p=swap.mutate(p);
		System.out.println(p);
		
		System.out.println("INVERSION");
		p=inversion.mutate(p);
		System.out.println(p);
		
		System.out.println("SCRAMBLE");
		p=scramble.mutate(p);
		System.out.println(p);
		
		System.out.println("------------------------------------------------------");
	}
}
