package com.sparkydots.ml.ml1.experiment;

public class ExperimentSetup {
    public int num;
    public ExperimentInput input;
    public ExperimentResult[] results;
    public boolean oneByOne = true;
    public Score runningScore;
    public ExperimentFactory experiments;
    
    public ExperimentSetup(ExperimentInput input, int num, ExperimentFactory experiments, boolean oneByOne, String[] scoreKeys, String template) {
        this.input = input;
        this.num = num;
        this.experiments = experiments;
        if (oneByOne) {
            runningScore = new Score(scoreKeys, template);
        } else {
            this.results = new ExperimentResult[this.num];
        }
    } 
    public void run() {
        ExperimentResult r; 
        for (int i = 0; i < num; i++) {
            Experiment experiment = experiments.newExperiment(this.input);
            r = new ExperimentResult();
            experiment.setResult(r); 
            //single-threaded
            experiment.run();
            if (oneByOne) {
                runningScore.addResult(r);
            } else {
                this.results[i] = r;
            }
        }
    }
    public Score getScore() {
        return this.runningScore;
    }
}
