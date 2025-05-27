package Admin.ViewTransactions.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Drink {
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
