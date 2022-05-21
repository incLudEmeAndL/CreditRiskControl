package com.chenwei.csust.spark.model;

import java.util.Arrays;

public class RiskControlResult {
    private Double label;

    private Double[] rawPrediction;

    private Double[] probability;

    private Double prediction;

    public RiskControlResult() {
    }

    public RiskControlResult(Double label, Double[] rawPrediction, Double[] probability, Double prediction) {
        this.label = label;
        this.rawPrediction = rawPrediction;
        this.probability = probability;
        this.prediction = prediction;
    }

    public Double getLabel() {
        return label;
    }

    public void setLabel(Double label) {
        this.label = label;
    }

    public Double[] getRawPrediction() {
        return rawPrediction;
    }

    public void setRawPrediction(Double[] rawPrediction) {
        this.rawPrediction = rawPrediction;
    }

    public Double[] getProbability() {
        return probability;
    }

    public void setProbability(Double[] probability) {
        this.probability = probability;
    }

    public Double getPrediction() {
        return prediction;
    }

    public void setPrediction(Double prediction) {
        this.prediction = prediction;
    }

    @Override
    public String toString() {
        return "RiskControlResult{" +
                "label=" + label +
                ", rawPrediction=" + Arrays.toString(rawPrediction) +
                ", probability=" + Arrays.toString(probability) +
                ", prediction=" + prediction +
                '}';
    }
}
