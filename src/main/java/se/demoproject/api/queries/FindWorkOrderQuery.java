package se.demoproject.api.queries;

public class FindWorkOrderQuery {
    private String id;

    public FindWorkOrderQuery() {
    }

    public FindWorkOrderQuery(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
