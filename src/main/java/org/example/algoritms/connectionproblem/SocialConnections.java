package org.example.algoritms.connectionproblem;

import io.vavr.Tuple2;
import io.vavr.collection.Vector;

import java.util.Date;


public class SocialConnections {

    private Vector<Tuple2<Date, String>> unions;


    public SocialConnections() {
    }

    public Vector<Tuple2<Date, String>> getUnions() {
        return unions;
    }

    public void setUnions(Vector<Tuple2<Date, String>> unions) {
        this.unions = unions;
    }

}
