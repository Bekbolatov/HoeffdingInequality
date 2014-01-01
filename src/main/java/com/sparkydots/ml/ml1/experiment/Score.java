package com.sparkydots.ml.ml1.experiment;

public class Score {
    public double[] vals;
    public String[] keys;
    public double count;
    public Score(String[] keys) {
        this.keys = keys;
        vals = new double[this.keys.length];
    }
    public Score(int num) {
        vals = new double[num];
        keys = new String[num];
        count = 0;
    }
    public void addResult(ExperimentResult r) {
        for (int i = 0; i < keys.length; i++) {
            vals[i] = vals[i] + r.getArg(keys[i]);
        }
        count++;
    }
    public void printResults() {
        System.out.println("---results---");
        System.out.println("total number of runs: " + count);
        System.out.println("(label, sum, avg)");
        for (int i = 0; i < keys.length; i++) {
            System.out.println(keys[i] + ": " + vals[i] + ": " + vals[i]/count);
        }
    }
}
