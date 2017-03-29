package br.com.psoframework;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import br.com.psoframework.function.*;

public class Main {
	/**
	 * 
	 * @param args
	 *            [0] - Tamanho da população
	 * @param args
	 *            [1] - Dimensão do espaço de busca
	 * @param args
	 *            [2] - Número de iterações
	 * @param args
	 *            [3] - Coeficiente cognitivo
	 * @param args
	 *            [4] - Coeficiente Social
	 * @param args
	 *            [5] - Coeficiente de inércia
	 */
	public static void main(String[] args) {
		int populationSize = Integer.parseInt(args[0]);
		int dimension = Integer.parseInt(args[1]);
		int numberOfIteractions = Integer.parseInt(args[2]);
		double cognitiveCoefficient = Double.parseDouble(args[3]);
		double socialCoefficient = Double.parseDouble(args[4]);
		double inertiaCoefficient = Double.parseDouble(args[5]);

		PSO pso = new PSO(new SphereFunction(), populationSize, dimension);
		double[] populationBestFitnessHistory = pso.optimize(inertiaCoefficient, cognitiveCoefficient, socialCoefficient, numberOfIteractions);
		double[] iteractions = new double[numberOfIteractions];
		
		for(int i = 0; i < numberOfIteractions; i++){
			iteractions[i] = i + 1;
		}

		Plot2DPanel plot = new Plot2DPanel();
		plot.addLinePlot("Optimization result", iteractions, populationBestFitnessHistory);
		JFrame frame = new JFrame("Optimization report");
		frame.setContentPane(plot);
		frame.pack();
		frame.setVisible(true);
	}
}