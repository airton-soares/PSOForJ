package br.com.psoframework.function;

public abstract class Function {
	private double bottomDomainLimit;
	private double topDomainLimit;
	
	/**
	 * Função avaliadora da aptidão de uma determinada função para uma dada
	 * posição no espaço de busca.
	 * 
	 * @param position - Posição a ser avaliada pela função de "fitness"
	 * @return Aptidão da posição passada como parâmetro para essa função
	 */
	public abstract double fitness(double[] position);
	
	public abstract boolean compareFitness(double fitness1, double fitness2);
	
	public double getBottomDomainLimit() {
		return bottomDomainLimit;
	}

	public void setBottomDomainLimit(double bottomDomainLimit) {
		this.bottomDomainLimit = bottomDomainLimit;
	}
	
	public double getTopDomainLimit() {
		return topDomainLimit;
	}
	
	public void setTopDomainLimit(double topDomainLimit) {
		this.topDomainLimit = topDomainLimit;
	}
}
