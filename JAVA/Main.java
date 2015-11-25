import java.util.*;
import java.io.*;

public class GeneticTest {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Replacement?");
		int A = scanner.nextInt();
		System.out.println("Mutation?");
		int C = scanner.nextInt();
		System.out.println("# of generations?");
		int B = scanner.nextInt();
		new GeneticTest(A,B,C);

	}


	private ArrayList<int[]> H;
	private MutationFunction M;
	private FitnessFunction F;
	public int[] topFitArray;
	public int generations;




	public GeneticTest(int replacement, int gen, int mutant) throws IOException{

		M = new MutationFunction();
		F = new FitnessFunction();
		H = new ArrayList<int[]>();
		BufferedReader reader = new BufferedReader(new FileReader("File1.txt"));
		boolean T = true;
		topFitArray = null;
		
		this.generations = gen;
		while(T == true){
			
			
			String line = reader.readLine();
			
			
			if (line == null){
				break;
			}
			
			line.trim();
			if(line == ""){
				T = false;
			}
			else{
				String[] row = line.split("\\s");
				int[] att = new int[row.length];
				for(int i = 0; i<row.length; i++){
					att[i] = Integer.parseInt(row[i]);
				}
				H.add(att);
			}
		}
		reader.close();

		
		while(generations > 0){
		evolve(M,F,H,replacement, generations, mutant);
		generations = generations-1;
		}
		Iterator i = H.iterator();
	

		System.out.println("Top array : ");
		for(int top : topFitArray){
			   
			     		System.out.print(top+" ");
			    
				}
		F.calculateFitness(topFitArray);
	
		

	}
	



	public void evolve(MutationFunction M,FitnessFunction F, ArrayList<int[]> H,int replacement,int gen, int mutant){
		ArrayList<int[]> newG = new ArrayList<int[]>();
		ArrayList<int[]> rep = new ArrayList<int[]>();
		Random rng = new Random();
		Specimen[] specimen = F.fitness(H);
		Specimen[] weighedSpec = new Specimen[F.getTotalFit(H)];
		
		int a = 0;
		for (Specimen s: specimen){
			
			for(int i = 0;i<(s.getFitness());i++){
				weighedSpec[a]=s;
				a++;
			}
		}
		
		for (int i = 0; i<replacement;i++){
			int[] temp = null;
			
			
			temp = weighedSpec[rng.nextInt(weighedSpec.length)].getAttribute();
			
			rep.add(temp);	
	}
		
		F.averageFitness(rep);
		newG = M.generate(rep, mutant);
		

		for (int i = 0; i<H.size()-replacement;i++){
			int[] temp = weighedSpec[rng.nextInt(weighedSpec.length)].getAttribute();
			newG.add(temp);	
	}
	
		H = newG;
		
	
		Iterator iter = H.iterator();
		
		int topFit = 0;
		int averageFit = 0;
		while(iter.hasNext()){
			
			int[] ab = (int[]) iter.next();
			for(int nm : ab){
		   /*
		    * 		System.out.print(nm+" ");
		    */
			}
			if (F.calculateFitness(ab)>topFit){
				topFit = F.calculateFitness(ab);
				topFitArray = ab;
			}
			
			averageFit = averageFit + F.calculateFitness(ab);
			/*
			 * System.out.println("Tot Fit = " +F.calculateFitness(ab));
			 */
		}	
		if (F.calculateFitness(topFitArray) > 58){
			generations = 0;
			
		}
		
		Iterator asd = H.iterator();

		System.out.println("Population");
		
		while(asd.hasNext()){
			System.out.print(F.calculateFitness((int[]) asd.next())+" ");
		}
		System.out.println("Average Fitness = "+averageFit/H.size());
		System.out.println("Top fitness = " + topFit);
	
		
	}




	public boolean compareArrays(int[] array1, int[] array2) {
		boolean b = true;
		if (array1 != null && array2 != null){
			if (array1.length != array2.length)
				b = false;
			else
				for (int i = 0; i < array2.length; i++) {
					if (array2[i] != array1[i]) {
						b = false;    
					}                 
				}
		}else{
			b = false;
		}
		return b;
	}


}
