import java.util.ArrayList;
import java.util.List;

public class Vertex {
    Vertex parent;
    List<Vertex> children = new ArrayList<>();
    String label;
    int id;
    int nodeValue;
    double cost;
    int count = 0;




    Vertex(String label, int id, int value, double cost) {
        this.label = label;
        this.id = id;
        this.nodeValue = value;
        this.cost = cost;
        this.count++;
    }


    Vertex(int id) {
        this.label = getLabel();
        this.id = id;
        this.nodeValue = getNodeValue();
        this.cost = getCost();
    }


    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getLabel() {
        return label;
    }


    public int getId() {
        return id;
    }

    public int getNodeValue() {
        return nodeValue;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNodeValue(int nodeValue) {
        this.nodeValue = nodeValue;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "label='" + label + '\'' +
                ", id=" + id +
                ", nodeValue=" + nodeValue +
                ", cost=" + cost +
                '}';
    }



    // equals and hashCode






}
