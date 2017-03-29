package br.com.psoframework.population;

import br.com.psoframework.particle.Particle;

public class Population {
	private Particle[] particles;
	private double[] bestPosition;
	private int dimension;
	
	public Population(int populationSize, int dimension, double bottomDomainLimit, double topDomainLimit) {
		this.particles = new Particle[populationSize];
		
		for(int i = 0; i < populationSize; i++){
			this.particles[i] = new Particle(dimension, bottomDomainLimit, topDomainLimit);
		}
		
		this.bestPosition = this.particles[0].getBestPosition();
		this.dimension = dimension;
	}
	
	public Particle[] getParticles() {
		return particles;
	}
	
	public void setParticles(Particle[] particles) {
		this.particles = particles.clone();
	}

	public double[] getBestPosition() {
		return bestPosition;
	}

	public void setBestPosition(double[] bestPosition) {
		this.bestPosition = bestPosition.clone();
	}

	public int getDimension() {
		return dimension;
	}
	
}
