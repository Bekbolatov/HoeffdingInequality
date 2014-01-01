package com.sparkydots.ml.ml1.experiment;

public interface ExperimentFactory {
    public Experiment newExperiment(ExperimentInput input);
}
