package br.com.psoframework;

import br.com.psoframework.function.Function;
import br.com.psoframework.particle.Particle;
import br.com.psoframework.population.Population;

public class PSO {
	private Function function;
	private Population population;

	public PSO(Function function, int populationSize, int dimension) {
		this.function = function;
		this.population = new Population(populationSize, dimension, this.function.getBottomDomainLimit(), this.function.getTopDomainLimit());
	}
	
	public double[] optimize(double inertiaCoefficient, double cognitiveCoefficient, double socialCoefficient, int numberOfIteractions){
		double[] populationBestFitnessHistory = new double[numberOfIteractions];
		
		for(int i = 0; i < numberOfIteractions; i++){
			updatePopulationValues(inertiaCoefficient, cognitiveCoefficient, socialCoefficient);
			populationBestFitnessHistory[i] = this.function.fitness(this.population.getBestPosition());
			
			System.out.println("[INFO] Iteraction " + (i + 1) + " of " + numberOfIteractions + " | Best fitness: " + populationBestFitnessHistory[i]);
		}
		
		return populationBestFitnessHistory;
	}

	private void updatePopulationValues(double inertiaCoefficient, double cognitiveCoefficient, double socialCoefficient) {
		if (this.population != null && this.population.getParticles().length > 0) {
			double bestPopulationPositionFitness = this.function.fitness(this.population.getBestPosition());
			
			for (int i = 0; i < this.population.getParticles().length; i++) {
				Particle particle = this.population.getParticles()[i];
				
				updateParticleValues(inertiaCoefficient, cognitiveCoefficient, socialCoefficient, particle);
				
				double bestParticlePositionFitness = this.function.fitness(particle.getBestPosition());
				
				if(this.function.compareFitness(bestParticlePositionFitness, bestPopulationPositionFitness)){
					this.population.setBestPosition(particle.getBestPosition());
					bestPopulationPositionFitness = this.function.fitness(this.population.getBestPosition());
				}
			}
		}

	}

	private void updateParticleValues(double inertiaCoefficient, double cognitiveCoefficient, double socialCoefficient, Particle particle) {

		int dimension = this.population.getDimension();
		
		for (int i = 0; i < dimension; i++) {
			double e1 = Math.random();
			double e2 = Math.random();
			
			double particleBestPosition = particle.getBestPosition()[i];
			double particleCurrentPosition = particle.getCurrentPosition()[i];
			double particleVelocity = particle.getVelocity()[i];
			double populationBestPosition = this.population.getBestPosition()[i];

			double cognitivePart = cognitiveCoefficient * e1 * (particleBestPosition - particleCurrentPosition);
			double socialPart = socialCoefficient * e2 * (populationBestPosition - particleCurrentPosition);

			double newVelocity = inertiaCoefficient * particleVelocity + cognitivePart + socialPart;
			double newPosition = particleCurrentPosition + newVelocity;
			
			particle.getCurrentPosition()[i] = newPosition;
			particle.getVelocity()[i] = newVelocity;
		}
		
		double currentPositionFitness = this.function.fitness(particle.getCurrentPosition());
		double bestParticlePositionFitness = this.function.fitness(particle.getBestPosition());
		
		if(this.function.compareFitness(currentPositionFitness, bestParticlePositionFitness)){
			particle.setBestPosition(particle.getCurrentPosition());
		}
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public Population getPopulation() {
		return population;
	}

	public void setPopulation(Population population) {
		this.population = population;
	}

}
