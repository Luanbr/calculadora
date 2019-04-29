package com.lbr.calculadora.com.lbr.calculadora.model;

public class Model {
    private Double number;
    private StringBuilder numberStr;
    private Integer index;
    private Integer indexOperation;
    private Integer lastIndexOperation;

    public Model(){
        this.numberStr = new StringBuilder();
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public StringBuilder getNumberStr() {
        return numberStr;
    }

    public void setNumberStr(StringBuilder numberStr) {
        this.numberStr = numberStr;
    }

    public void setIndexOperation(Integer indexOperation) {
        this.indexOperation = indexOperation;
    }

    public Integer getIndexOperation() {
        return indexOperation;
    }

    public Integer getLastIndexOperation() {
        return lastIndexOperation;
    }

    public void setLastIndexOperation(Integer lastIndexOperation) {
        this.lastIndexOperation = lastIndexOperation;
    }
}
