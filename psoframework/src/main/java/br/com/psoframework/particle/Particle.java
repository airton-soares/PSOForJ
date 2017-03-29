package br.com.psoframework.particle;

import br.com.psoframework.util.VectorUtil;

public class Particle {
	private double[] currentPosition;
	private double[] bestPosition;
	private double[] velocity;
	
	public Particle(int dimension, double bottomDomainLimit, double topDomainLimit) {
		this.currentPosition = VectorUtil.generateRandomDoubleVector(dimension, bottomDomainLimit, topDomainLimit);
		this.bestPosition = this.currentPosition.clone();
		this.velocity = VectorUtil.generateRandomDoubleVector(dimension, bottomDomainLimit, topDomainLimit);
	}

	public double[] getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(double[] currentPosition) {
		this.currentPosition = currentPosition.clone();
	}
	
	public double[] getBestPosition() {
		return bestPosition;
	}

	public void setBestPosition(double[] bestPosition) {
		this.bestPosition = bestPosition.clone();
	}

	public double[] getVelocity() {
		return velocity;
	}

	public void setVelocity(double[] velocity) {
		this.velocity = velocity.clone();
	}
	
}
