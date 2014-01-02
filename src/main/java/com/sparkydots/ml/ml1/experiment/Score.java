package com.sparkydots.ml.ml1.experiment;

import org.apache.log4j.Logger;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.VelocityContext;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import org.apache.velocity.tools.generic.NumberTool;

public class Score {
    private double[] vals;
    private String[] keys;
    private int count;
    private String outputTemplate;

    public static Logger log = Logger.getLogger(Score.class);

    public Score(String[] keys, String outputTemplate) {
        this.keys = keys;
        vals = new double[this.keys.length];
        this.outputTemplate = outputTemplate;
    }
    public Score(int num) {
        vals = new double[num];
        keys = new String[num];
        count = 0;
    }

    public String[] getKeys() {
        return keys;
    }
    public double[] getVals() {
        return vals;
    }
    public int getCount() {
        return count;
    }
    public void addResult(ExperimentResult r) {
        for (int i = 0; i < keys.length; i++) {
            vals[i] = vals[i] + r.getArg(keys[i]);
        }
        count++;
    }
    public void printResults() {
        VelocityContext ctx = new VelocityContext();
        ctx.put("score", this); 
        ctx.put("number", new NumberTool());
        StringWriter writer = new StringWriter();
        Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(this.outputTemplate));
        Velocity.evaluate(ctx, writer, "", reader);
        log.info(writer.toString());
    }
}
