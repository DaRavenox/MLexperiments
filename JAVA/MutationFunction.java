import java.util.*;

public class MutationFunction {



	public MutationFunction(){
	}

	/*
	 * The nested Pair class, used in easier handling of multiple pairs of children, in particular in the 
	 * Breed method.
	 */


	public class Pair{

		public int[] child1;
		public int[] child2;
		public Pair(int[] A, int[] B){
			child1 = A;
			child2 = B;
		}
		public int[] getChild1(){
			return child1;

		}
		public int[] getChild2(){
			return child2;

		}
	}
	/*
	 * Produces a new generation. Breeds children from the given specimen along with a number of mutations.
	 */


	public ArrayList<int[]> generate(ArrayList<int[]> current, int replaced){
		Random rng = new Random();
		ArrayList<int[]> newGeneration = new ArrayList<int[]>();
		Iterator<int[]> currentIterator = current.iterator();
		while(currentIterator.hasNext()){
			int[] A =  currentIterator.next();
			if(currentIterator.hasNext()){
				int[] B = currentIterator.next();
				Pair children = breed(A,B);
				newGeneration.add(children.getChild1());
				newGeneration.add(children.getChild2());
			}
		}
		for(int i = 0; i<replaced; i++){
			newGeneration.add(mutateCertain(current.get(rng.nextInt(current.size()))));
		}
		return newGeneration;
	}

	/*
	 * Crossover operator. Takes two int[] A and B and creates two children; One containing the first half of A and 
	 * the second half of B, the other containing the first half of B and the second half of A.
	 */

	public Pair breed(int[] parent1, int[] parent2){
		FitnessFunction f = new FitnessFunction();
		int[] child1 = new int[parent1.length];
		int[] child2 = new int[parent2.length];
		int i = 0;
		Random rng = new Random();
		int a = rng.nextInt(parent1.length) - 1;
		
		if((parent1.length % 2) == 0){
			i = parent1.length/2;
		}
		else{
			i = (parent1.length-1)/2;
		}
		for(int k = 0; k<parent1.length;k++){
			if (k<a){
				child1[k] = parent1[k];
				child2[k] = parent2[k];
			}
			
			if(k>a-1){
				child1[k] = parent1[k];
				child2[k] = parent2[k];
			}
			
		}
		
		Pair pair = new Pair(child1,child2);
		return pair;
	}

	/*
	 * May changes some characteristics of the given individual.
	 */

	public int[] mutate(int[] parent){
		Random rng = new Random();
		int[] mutant = parent;
		for(int i = 0; i<mutant.length;i++){
			if(rng.nextInt(100)>97){
				mutant[i]++;}
			else if(rng.nextInt(100)<3){
				mutant[i] = mutant[i]+2;
			}
		}
		return mutant;
	}

	/*
	 * Changes one characteristic of a given individual, either by incrementing it once or decrementing it once.
	 */

	public int[] mutateCertain(int[] parent){
		Random rng = new Random();
		int[] mutant = parent;
		int a = rng.nextInt(100);
		
		if(a<50){
			int temp = rng.nextInt(mutant.length);
			mutant[temp]++;
			}
		
		if(a>50){
			int temp = rng.nextInt(mutant.length);
			mutant[temp]--;
			if(mutant[temp]<0){
				mutant[temp] = 0;
			}
		}
		return mutant;
	}

}
