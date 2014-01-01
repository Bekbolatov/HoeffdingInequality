package com.sparkydots.ml.ml1.experiment;

public abstract class Experiment {
    protected ExperimentResult result;
    protected ExperimentInput input;
    public Experiment(ExperimentInput input) {
        this.input = input;
    }
    public void setResult(ExperimentResult result) {
        this.result = result;
    }
    public abstract void run();
}
   
