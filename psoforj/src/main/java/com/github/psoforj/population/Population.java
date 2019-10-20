package com.github.psoforj.population;

import java.util.ArrayList;
import java.util.List;

import com.github.psoforj.particle.Particle;

public class Population {
  private List<SubPopulation> subPopulationList;
  private double[] bestPosition;

  public Population(int numberOfParticles, int dimension, double bottomDomainLimit, double topDomainLimit,
      TopologyType topologyType) {
    this.subPopulationList = initializePopulation(numberOfParticles, dimension, bottomDomainLimit, topDomainLimit,
        topologyType);
  }

  private List<SubPopulation> initializePopulation(int numberOfParticles, int dimension, double bottomDomainLimit,
      double topDomainLimit, TopologyType topologyType) {
    List<SubPopulation> subPopulationList = new ArrayList<SubPopulation>();
    Particle[] particles = new Particle[numberOfParticles];

    for (int i = 0; i < numberOfParticles; i++) {
      Particle particle = new Particle(dimension, bottomDomainLimit, topDomainLimit);
      particles[i] = particle;
    }

    this.bestPosition = particles[0].getBestPosition();

    switch (topologyType) {

    case GLOBAL: {
      SubPopulation subPopulation = new SubPopulation(dimension, particles);
      subPopulationList.add(subPopulation);

      break;
    }
    case RING: {
      for (int i = 0; i < numberOfParticles; i++) {
        Particle[] subParticle = new Particle[2];

        subParticle[0] = particles[i];

        if (i < numberOfParticles - 1) {
          subParticle[1] = particles[i + 1];
        } else {
          subParticle[1] = particles[0];
        }

        SubPopulation subPopulation = new SubPopulation(dimension, subParticle);
        subPopulationList.add(subPopulation);
      }

      break;
    }
    case MULTI_RING:
      break;
    case FOCAL:
      Particle center = particles[0];

      for (int i = 1; i < numberOfParticles; i++) {
        Particle[] subParticle = new Particle[2];

        subParticle[0] = center;
        subParticle[1] = particles[i];

        SubPopulation subPopulation = new SubPopulation(dimension, subParticle);
        subPopulationList.add(subPopulation);
      }

      break;
    case HIERARCHICAL:
      break;
    case VON_NEUMANN:
      break;
    case FOUR_CLUSTERS:
      break;

    }

    return subPopulationList;
  }

  public List<SubPopulation> getSubPopulationList() {
    return subPopulationList;
  }

  public void setSubPopulationList(List<SubPopulation> subPopulationList) {
    this.subPopulationList = subPopulationList;
  }

  public double[] getBestPosition() {
    return bestPosition;
  }

  public void setBestPosition(double[] bestPosition) {
    this.bestPosition = bestPosition.clone();
  }

}
