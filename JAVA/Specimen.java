
public class Specimen {

	public int[] attributes;
	public int fitness;
	public int fitnessWeighed;
	

	public Specimen(int[] attributes, int fitness, int fitnessWeighed){
		this.attributes = attributes;
		this.fitness = fitness;
		this.fitnessWeighed = fitnessWeighed;
	}
	
	public int[] getAttribute() {
		return attributes;
	}
	
	public int getFitness(){
		return fitness;
	}
	
	public int getFitnessWeighed(){
		return fitnessWeighed;
	}
}
