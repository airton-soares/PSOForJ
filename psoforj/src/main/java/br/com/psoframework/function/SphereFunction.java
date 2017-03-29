package br.com.psoframework.function;

public class SphereFunction extends Function {

	public SphereFunction() {
		super();
		this.setBottomDomainLimit(-10000);
		this.setTopDomainLimit(10000);
	}
	
	@Override
	public double fitness(double[] position) {
		double fitness = 0;
		
		for(int i = 0; i < position.length; i++){
			fitness += Math.pow(position[i], 2);
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
