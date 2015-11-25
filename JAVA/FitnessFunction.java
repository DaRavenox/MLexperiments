import java.util.*;
import java.math.*;

public class FitnessFunction {

	public int totalFit;

	public FitnessFunction(){


	}

	public Specimen[] fitness(ArrayList<int[]> old){
		Specimen[] specimens = new Specimen[old.size()];
		Random rng = new Random();
		int averageFitness = averageFitness(old);
		if(averageFitness == 0){
			averageFitness = 1;
		}
		Iterator iterator = old.iterator();
		int i = 0;
		while(iterator.hasNext()){
			int[] current = (int[]) iterator.next();


			specimens[i] = new Specimen(current,calculateFitness(current),calculateFitness(current)/averageFitness);
			i++;
		}

		return specimens;
	}


	/*
	 * Returns the total fitness. 
	 */
	public int getTotalFit(ArrayList<int[]> old){
		int fitness = 0;
		Iterator iterator = old.iterator();
		while(iterator.hasNext()){
			fitness = fitness + calculateFitness( (int[]) iterator.next());
		}
		totalFit = fitness;
		return totalFit;
	}



	/*
	 * Finds the average fitness of the given int[]s.
	 */
	public int averageFitness(ArrayList<int[]> old){

		int fitness = 0;
		Iterator iterator = old.iterator();
		while(iterator.hasNext()){
			int temp = calculateFitness((int[]) iterator.next());

			fitness = fitness + temp;
		}
		totalFit = fitness;
		fitness = fitness/old.size();


		return fitness;
	}

	/*
	 * Finds the fitness of the given int[]
	 */

	public int calculateFitness(int[] A){
		int fitness = 0;
		for(int k = 0; k<A.length;k++){
			if (k < 3){
				if (A[k]>4){
					fitness = fitness - A[k];

				}
				else{
					fitness = fitness + A[k];
				}
			}

			if(k>2 && k<7){
				if(A[k]<5 || A[k]>8){
					fitness = fitness - A[k];

				}
				else{
					fitness = fitness + A[k];
				}
			}
			if(k>6){
				if(A[k]<9 || A[k]>10){
					fitness = fitness - A[k];

				}
				else{
					fitness = fitness + A[k];

				}
			}
		}

		if(fitness < 0){
			fitness = 0;
		}

		return fitness;

	}
}

