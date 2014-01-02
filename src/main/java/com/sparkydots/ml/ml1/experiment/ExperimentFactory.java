package com.sparkydots.ml.ml1.experiment;

public interface ExperimentFactory {
    String[] getStatNames();
    String getTemplateName();
    Experiment newExperiment(ExperimentInput input);
}
