package com.github.psoforj;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import com.github.psoforj.function.*;
import com.github.psoforj.population.TopologyType;

public class Main {
  /**
   * 
   * @param args [0] - Tamanho da população
   * @param args [1] - Dimensão do espaço de busca
   * @param args [2] - Número de iterações
   * @param args [3] - Coeficiente cognitivo
   * @param args [4] - Coeficiente Social
   * @param args [5] - Coeficiente de inércia inicial
   * @param args [6] - Coeficiente de inércia final
   * @param args [7] - Limite inferior de inicialização
   * @param args [8] - Limite superior de inicialização
   */
  public static void main(String[] args) {
    int populationSize = Integer.parseInt(args[0]);
    int dimension = Integer.parseInt(args[1]);
    int numberOfIteractions = Integer.parseInt(args[2]);
    double cognitiveCoefficient = Double.parseDouble(args[3]);
    double socialCoefficient = Double.parseDouble(args[4]);
    double initialInertiaCoefficient = Double.parseDouble(args[5]);
    double finalInertiaCoefficient = Double.parseDouble(args[6]);
    double bottomDomainLimit = Double.parseDouble(args[7]);
    double topDomainLimit = Double.parseDouble(args[8]);

    PSO pso = new PSO(new SphereFunction(bottomDomainLimit, topDomainLimit), populationSize, dimension,
        bottomDomainLimit, topDomainLimit, TopologyType.RING);
    double[] populationBestFitnessHistory = pso.optimize(initialInertiaCoefficient, finalInertiaCoefficient,
        cognitiveCoefficient, socialCoefficient, numberOfIteractions);
    double[] iteractions = new double[numberOfIteractions];

    for (int i = 0; i < numberOfIteractions; i++) {
      iteractions[i] = i + 1;
    }

    Plot2DPanel plot = new Plot2DPanel();
    plot.addLinePlot("Optimization result", iteractions, populationBestFitnessHistory);
    JFrame frame = new JFrame("Optimization report");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    frame.setContentPane(plot);
    frame.pack();
    frame.setVisible(true);
  }
}