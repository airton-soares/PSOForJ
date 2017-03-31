package br.com.psoforj.population;

import br.com.psoforj.particle.Particle;

public class SubPopulation {
	private Particle[] particles;
	private double[] bestPosition;
	private int dimension;
	
	public SubPopulation(int dimension, Particle[] particles) {
		this.particles = particles;
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
