package ai152.Heorhiev;

public class Node {
    private String name;
    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node)) return false;
        else return name.equals(((Node) obj).getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
