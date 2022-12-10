package me.leansani.phasma.clickgui.settings;

public class NumberSetting extends Setting {
    private double min, max, inc;
    public double value;
    double defaultvalue;

    public void setInc(double inc) {
        this.inc = inc;
    }

    public double getDefaultvalue() {
        return defaultvalue;
    }

    public void setDefaultvalue(double defaultvalue) {
        this.defaultvalue = defaultvalue;
    }

    public NumberSetting(String name, double min, double max, double defaultvalue, double inc) {
        super(name);
        this.max = max;
        this.defaultvalue = defaultvalue;
        this.inc = inc;
        this.min = min;
    }

    public double getValue() {
        return value;
    }

    public double getValueFloat() {
        return (float) value;
    }

    public void setValDouble(double in) {
        this.value = in;
    }

    public void setValue(double value) {
        value = Math.round(value / inc) * inc;
        value = clamp((float) value, (float) min, (float) max);
        this.value = value;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getInc() {
        return inc;
    }

    public static float clamp(float num, float min, float max) {
        return num < min ? min : num > max ? max : num;
    }

    public int getValueInt() {
        return (int) value;
    }

    public void inc(boolean isPositive) {
        if (isPositive) setValue(getValue() + getInc());
        else setValue(getValue() - getInc());
    }
}
