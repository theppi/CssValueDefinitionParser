package de.theppi.valueDefinitionParser;

import java.util.ArrayList;
import java.util.List;

public class CssValueDefinition {
    private String name;
    private String definition;
    private List<String> conditions;
    public String getName() {
        return name;
    }
    public String getDefinition() {
        return definition;
    }
    public List<String> getConditions() {
        return conditions;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDefinition(String definition) {
        this.definition = definition;
    }
    
    public void addCondition(String condition) {
        if(this.conditions == null) this.conditions = new ArrayList<>();
        this.conditions.add(condition);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        sb.append("[ ").append("name: ").append(this.name)
            .append(", definition: ") .append(this.definition)
            .append(", conditions:[ ");
        if(this.conditions != null) {
            for(String condition : this.conditions) {
                sb.append(condition).append(", ");
            }
            sb.replace(sb.length()- 2, sb.length(), " ]");
        }
        sb.append("]");
        return sb.toString();
    }
}
