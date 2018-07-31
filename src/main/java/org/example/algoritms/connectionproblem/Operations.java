package org.example.algoritms.connectionproblem;

import io.vavr.collection.Vector;

public class Operations {

    private Vector<String> unions;

    private Vector<String> find;

    public Operations() {
    }

    public Vector<String> getUnions() {
        return unions;
    }

    public void setUnions(Vector<String> unions) {
        this.unions = unions;
    }

    public Vector<String> getFind() {
        return find;
    }

    public void setFind(Vector<String> find) {
        this.find = find;
    }
}
