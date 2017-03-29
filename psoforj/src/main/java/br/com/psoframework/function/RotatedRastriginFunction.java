package br.com.psoframework.function;

public class RotatedRastriginFunction extends Function {

	public RotatedRastriginFunction() {
		super();
		this.setBottomDomainLimit(-5);
		this.setTopDomainLimit(5);
	}
	
	@Override
	public double fitness(double[] position) {
		double fitness = 0;
				
		for(int i = 0; i < position.length; i++){
			fitness += Math.pow(position[i], 2) - 10 * Math.cos(2 * Math.PI * position[i]) + 10;
		}
		
		return fitness;
	}

	@Override
	public boolean compareFitness(double fitness1, double fitness2) {
		boolean compare = false;
		
		if(fitness1 < fitness2){
			compare = true;
		}
		
		return compare;
	}

}
