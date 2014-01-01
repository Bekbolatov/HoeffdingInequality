package com.sparkydots.ml.ml1;

import com.sparkydots.ml.ml1.experiment.*;
import java.util.Random;

class CoinExperimentFactory implements ExperimentFactory {
    public static final String PROB_HEADS = "prob_heads";
    public static final String FAIR_COIN = "fair_coin"; //if >0 then fair coin 0.5
    public static final int numBins = 1000;
    public static final int numSamples = 10;

    class CoinExperiment extends Experiment {
        private Random rnd;
        private boolean fairCoin = true;
        private int ofTotal = 10000;
        private double threshold = 0.5;

        public CoinExperiment(ExperimentInput input) {
            super(input);
            rnd = new Random();
            fairCoin = input.getArg(FAIR_COIN) > 0;
            if (!fairCoin) {
                threshold = input.getArg(PROB_HEADS)*ofTotal;
            }
        }

        //false = TAIL, true = HEAD
        public boolean nextToss() {
            if (fairCoin) {
                return rnd.nextBoolean();
            } else {
                return rnd.nextInt(ofTotal) < threshold;
            }
        }
 
        public void run() {
            double curVmin = 2.0;
            double curV1 = 2.0;
            double curVrand = 2.0;
        
            Random rnd = new Random();

            int i_rand = rnd.nextInt(numBins); 
            double curV; 
            for (int i = 0; i < numBins; i++) {
                // for each bin
                int heads = 0;
                int tails = 0;
                //for each sample in a bin
                for (int j = 0; j < numSamples; j++) {
                    if (nextToss()) {
                        heads++;
                    } else {
                        tails++;
                    }
                }
                curV = heads*1.0/numSamples;
                //update curVmin if it is lower
                if (curV < curVmin) {
                    curVmin = curV;
                } 
                //update curV1
                if (i == 0) {
                    curV1 = curV;
                }
                //update curVrand
                if (i == i_rand) {
                    curVrand = curV;
                }
            }
            result.setArg("n1", curV1);
            result.setArg("nrand", curVrand);
            result.setArg("nmin", curVmin);
        }    
    }

    public Experiment newExperiment(ExperimentInput input) {
        return new CoinExperiment(input);
    }
}

public class Experiment0110 {
    public static void main(String[] args) {
        int numOfRuns = 1;
        try {
            numOfRuns = Integer.parseInt(args[0]);
        } catch(Exception e) {}
        System.out.println("Number of runs is: " + numOfRuns);
        ExperimentInput input = new ExperimentInput();
        input.setArg(CoinExperimentFactory.FAIR_COIN, +100);
        //input.setArg(CoinExperimentFactory.PROB_HEADS, 0.5);
        ExperimentSetup es = new ExperimentSetup(input, numOfRuns,
                new CoinExperimentFactory(),
                true, new String[] { "n1", "nrand", "nmin" });
        es.run();
        es.getScore().printResults();
    }
}

