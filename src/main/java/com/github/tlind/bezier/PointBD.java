package com.github.tlind.bezier;

import java.math.BigDecimal;

public class PointBD {
    public BigDecimal x;
    public BigDecimal y;

    public PointBD(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public PointBD(double x, double y) {
        this.x = BigDecimal.valueOf(x);
        this.y = BigDecimal.valueOf(y);
    }

    public PointBD multiply(BigDecimal factor) {
        return new PointBD(x.multiply(factor), y.multiply(factor));
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
