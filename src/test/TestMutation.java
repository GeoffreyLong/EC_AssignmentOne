package test;

import evolutionary.Individual;
import evolutionary.Mutation;
import evolutionary.Population;

public class TestMutation {

	public static void main(String[] args) {
		Individual iA = new Individual(10,10);
		Individual iB = new Individual(5,5);
		//Population p = new Population(3);

		Mutation m = new Mutation();
		
		System.out.println("ORIGINALS");
		System.out.println(iA);
		System.out.println(iB);
	
		System.out.println("INSERT");
		Individual iAa = m.insert(iA);
		//Individual iAb = m.insert(iA);
		Individual iBa = m.insert(iB);
		//Individual iBb = m.insert(iB);		
		
		System.out.println(iAa);
		//System.out.println(iAb);
		System.out.println(iBa);
		//System.out.println(iBb);
		
		System.out.println("SWAP");
		iAa = m.swap(iA);
		//iAb = m.swap(iA);
		iBa = m.swap(iB);
		//iBb = m.swap(iB);		
		
		System.out.println(iAa);
		//System.out.println(iAb);
		System.out.println(iBa);
		//System.out.println(iBb);
		
		System.out.println("INVERSION");
		iAa = m.inversion(iA);
		//iAb = m.inversion(iA);
		iBa = m.inversion(iB);
		//iBb = m.inversion(iB);		
		
		System.out.println(iAa);
		//System.out.println(iAb);
		System.out.println(iBa);
		//System.out.println(iBb);
		
		System.out.println("SCRAMBLE");
		iAa = m.scramble(iA);
		//iAb = m.scramble(iA);
		iBa = m.scramble(iB);
		//iBb = m.scramble(iB);		
		
		System.out.println(iAa);
		//System.out.println(iAb);
		System.out.println(iBa);
		//System.out.println(iBb);
	}

}
