package br.com.psoforj.util;

import java.util.concurrent.ThreadLocalRandom;

public class VectorUtil {
	public static double[] generateRandomDoubleVector(int dimension, double bottomLimit, double topLimit){
		double[] position = new double[dimension];
		
		for(int i = 0; i < dimension; i++){
			position[i] = ThreadLocalRandom.current().nextDouble(bottomLimit, topLimit);
		}
		
		return position;
	}
}
