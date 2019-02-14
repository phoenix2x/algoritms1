package org.example.algoritms2.shortespath;

public class WeightenedDirectedEdge implements Comparable<WeightenedDirectedEdge> {
	private int from;
	private int to;
	private Double weight;

	public WeightenedDirectedEdge(int from, int to, Double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public Double getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return "WeightenedDirectedEdge{" +
				"from=" + from +
				", to=" + to +
				", weight=" + weight +
				'}';
	}

	@Override
	public int compareTo(WeightenedDirectedEdge o) {
		return this.getWeight().compareTo(o.getWeight());
	}
}
