package application;

public class Planets {
	String planetName;
    double planetRadius;
    float planetWeightRatio;
    double planetDistanceFromTheSun;

    public Planets(String planetName, double planetRadius, float planetWeightRatio, double planetDistanceFromTheSun) {
        this.planetName = planetName;
        this.planetRadius = planetRadius;
        this.planetWeightRatio = planetWeightRatio;
        this.planetDistanceFromTheSun = planetDistanceFromTheSun;
    }
}
