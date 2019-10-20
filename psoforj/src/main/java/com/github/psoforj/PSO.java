package com.github.psoforj;

import com.github.psoforj.function.Function;
import com.github.psoforj.particle.Particle;
import com.github.psoforj.population.Population;
import com.github.psoforj.population.SubPopulation;
import com.github.psoforj.population.TopologyType;
import com.github.psoforj.util.PSOConstants;

public class PSO {
  private Function function;
  private Population population;

  public PSO(Function function, int numberOfParticles, int dimension, double bottomDomainLimit, double topDomainLimit,
      TopologyType topologyType) {
    this.function = function;
    this.population = new Population(numberOfParticles, dimension, bottomDomainLimit, topDomainLimit, topologyType);
  }

  public double[] optimize(double inertiaCoefficient, double cognitiveCoefficient, double socialCoefficient,
      int numberOfIteractions) {
    double[] populationBestFitnessHistory = new double[numberOfIteractions];

    for (int i = 0; i < numberOfIteractions; i++) {
      updatePopulationValues(inertiaCoefficient, cognitiveCoefficient, socialCoefficient);
      populationBestFitnessHistory[i] = this.function.fitness(this.population.getBestPosition());

      System.out.println("[INFO] Iteraction " + (i + 1) + " of " + numberOfIteractions + " | Best fitness: "
          + populationBestFitnessHistory[i]);
    }

    return populationBestFitnessHistory;
  }

  public double[] optimize(double initialInertiaCoefficient, double finalInertiaCoefficient,
      double cognitiveCoefficient, double socialCoefficient, int numberOfIteractions) {
    double[] populationBestFitnessHistory = new double[numberOfIteractions];
    double inertiaCoefficient = initialInertiaCoefficient;
    double rate = (initialInertiaCoefficient - finalInertiaCoefficient) / numberOfIteractions;

    for (int i = 0; i < numberOfIteractions; i++) {
      updatePopulationValues(inertiaCoefficient, finalInertiaCoefficient, cognitiveCoefficient, socialCoefficient);
      populationBestFitnessHistory[i] = this.function.fitness(this.population.getBestPosition());
      inertiaCoefficient -= rate;

      System.out.println("[INFO] Iteraction " + (i + 1) + " of " + numberOfIteractions + " | Best fitness: "
          + populationBestFitnessHistory[i]);
    }

    return populationBestFitnessHistory;
  }

  public double[] optimize(double cognitiveCoefficient, double socialCoefficient, int numberOfIteractions) {
    double[] populationBestFitnessHistory = new double[numberOfIteractions];

    for (int i = 0; i < numberOfIteractions; i++) {
      updatePopulationValues(cognitiveCoefficient, socialCoefficient);
      populationBestFitnessHistory[i] = this.function.fitness(this.population.getBestPosition());

      System.out.println("[INFO] Iteraction " + (i + 1) + " of " + numberOfIteractions + " | Best fitness: "
          + populationBestFitnessHistory[i]);
    }

    return populationBestFitnessHistory;
  }

  private void updatePopulationValues(double inertiaCoefficient, double cognitiveCoefficient,
      double socialCoefficient) {
    double bestPopulationPositionFitness = this.function.fitness(this.population.getBestPosition());
    int populationSize = this.population.getSubPopulationList().size();

    for (int i = 0; i < populationSize; i++) {
      SubPopulation subPopulation = this.population.getSubPopulationList().get(i);

      updateSubPopulationValues(inertiaCoefficient, cognitiveCoefficient, socialCoefficient, subPopulation);

      double bestSubPopulationPositionFitness = this.function.fitness(subPopulation.getBestPosition());

      if (this.function.compareFitness(bestSubPopulationPositionFitness, bestPopulationPositionFitness)) {
        this.population.setBestPosition(subPopulation.getBestPosition());
        bestPopulationPositionFitness = this.function.fitness(this.population.getBestPosition());
      }
    }

  }

  private void updatePopulationValues(double inertiaCoefficient, double finalInertiaCoefficient,
      double cognitiveCoefficient, double socialCoefficient) {
    double bestPopulationPositionFitness = this.function.fitness(this.population.getBestPosition());
    int populationSize = this.population.getSubPopulationList().size();

    for (int i = 0; i < populationSize; i++) {
      SubPopulation subPopulation = this.population.getSubPopulationList().get(i);

      updateSubPopulationValues(inertiaCoefficient, cognitiveCoefficient, socialCoefficient, subPopulation);

      double bestSubPopulationPositionFitness = this.function.fitness(subPopulation.getBestPosition());

      if (this.function.compareFitness(bestSubPopulationPositionFitness, bestPopulationPositionFitness)) {
        this.population.setBestPosition(subPopulation.getBestPosition());
        bestPopulationPositionFitness = this.function.fitness(this.population.getBestPosition());
      }
    }

  }

  private void updatePopulationValues(double cognitiveCoefficient, double socialCoefficient) {
    double bestPopulationPositionFitness = this.function.fitness(this.population.getBestPosition());
    int populationSize = this.population.getSubPopulationList().size();

    for (int i = 0; i < populationSize; i++) {
      SubPopulation subPopulation = this.population.getSubPopulationList().get(i);

      updateSubPopulationValues(cognitiveCoefficient, socialCoefficient, subPopulation);

      double bestSubPopulationPositionFitness = this.function.fitness(subPopulation.getBestPosition());

      if (this.function.compareFitness(bestSubPopulationPositionFitness, bestPopulationPositionFitness)) {
        this.population.setBestPosition(subPopulation.getBestPosition());
        bestPopulationPositionFitness = this.function.fitness(this.population.getBestPosition());
      }
    }

  }

  private void updateSubPopulationValues(double inertiaCoefficient, double cognitiveCoefficient,
      double socialCoefficient, SubPopulation subPopulation) {
    double bestSubPopulationPositionFitness = this.function.fitness(subPopulation.getBestPosition());

    for (int i = 0; i < subPopulation.getParticles().length; i++) {
      Particle particle = subPopulation.getParticles()[i];

      updateParticleValues(inertiaCoefficient, cognitiveCoefficient, socialCoefficient, particle, subPopulation);

      double bestParticlePositionFitness = this.function.fitness(particle.getBestPosition());

      if (this.function.compareFitness(bestParticlePositionFitness, bestSubPopulationPositionFitness)) {
        subPopulation.setBestPosition(particle.getBestPosition());
        bestSubPopulationPositionFitness = this.function.fitness(subPopulation.getBestPosition());
      }
    }

  }

  private void updateSubPopulationValues(double cognitiveCoefficient, double socialCoefficient,
      SubPopulation subPopulation) {
    double bestSubPopulationPositionFitness = this.function.fitness(subPopulation.getBestPosition());

    for (int i = 0; i < subPopulation.getParticles().length; i++) {
      Particle particle = subPopulation.getParticles()[i];

      updateParticleValues(cognitiveCoefficient, socialCoefficient, particle, subPopulation);

      double bestParticlePositionFitness = this.function.fitness(particle.getBestPosition());

      if (this.function.compareFitness(bestParticlePositionFitness, bestSubPopulationPositionFitness)) {
        subPopulation.setBestPosition(particle.getBestPosition());
        bestSubPopulationPositionFitness = this.function.fitness(subPopulation.getBestPosition());
      }
    }

  }

  private void updateParticleValues(double inertiaCoefficient, double cognitiveCoefficient, double socialCoefficient,
      Particle particle, SubPopulation subPopulation) {
    int dimension = subPopulation.getDimension();

    for (int i = 0; i < dimension; i++) {
      double e1 = Math.random();
      double e2 = Math.random();

      double particleBestPosition = particle.getBestPosition()[i];
      double particleCurrentPosition = particle.getCurrentPosition()[i];
      double particleVelocity = particle.getVelocity()[i];
      double populationBestPosition = subPopulation.getBestPosition()[i];

      double cognitivePart = cognitiveCoefficient * e1 * (particleBestPosition - particleCurrentPosition);
      double socialPart = socialCoefficient * e2 * (populationBestPosition - particleCurrentPosition);

      double newVelocity = inertiaCoefficient * particleVelocity + cognitivePart + socialPart;
      double newPosition = particleCurrentPosition + newVelocity;

      particle.getCurrentPosition()[i] = newPosition;
      particle.getVelocity()[i] = newVelocity;
    }

    double currentPositionFitness = this.function.fitness(particle.getCurrentPosition());
    double bestParticlePositionFitness = this.function.fitness(particle.getBestPosition());

    if (this.function.compareFitness(currentPositionFitness, bestParticlePositionFitness)) {
      particle.setBestPosition(particle.getCurrentPosition());
    }
  }

  private void updateParticleValues(double cognitiveCoefficient, double socialCoefficient, Particle particle,
      SubPopulation subPopulation) {
    int dimension = subPopulation.getDimension();

    for (int i = 0; i < dimension; i++) {
      double e1 = Math.random();
      double e2 = Math.random();

      double particleBestPosition = particle.getBestPosition()[i];
      double particleCurrentPosition = particle.getCurrentPosition()[i];
      double particleVelocity = particle.getVelocity()[i];
      double populationBestPosition = subPopulation.getBestPosition()[i];

      double cognitivePart = cognitiveCoefficient * e1 * (particleBestPosition - particleCurrentPosition);
      double socialPart = socialCoefficient * e2 * (populationBestPosition - particleCurrentPosition);

      double newVelocity = PSOConstants.CLERC_KENNEDY_CONSTRICTION_FACTOR
          * (particleVelocity + cognitivePart + socialPart);
      double newPosition = particleCurrentPosition + newVelocity;

      particle.getCurrentPosition()[i] = newPosition;
      particle.getVelocity()[i] = newVelocity;
    }

    double currentPositionFitness = this.function.fitness(particle.getCurrentPosition());
    double bestParticlePositionFitness = this.function.fitness(particle.getBestPosition());

    if (this.function.compareFitness(currentPositionFitness, bestParticlePositionFitness)) {
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
