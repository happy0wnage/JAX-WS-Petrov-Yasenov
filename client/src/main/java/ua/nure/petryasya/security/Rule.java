package ua.nure.petryasya.security;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlType(name = "rule",
        propOrder = {
                "role",
                "urls"
        })
@XmlAccessorType(XmlAccessType.FIELD)
public class Rule {

    @XmlElement
    private String role;

    @XmlElement(name = "url")
    @XmlElementWrapper(name = "urls")
    private List<String> urls;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "role='" + role + '\'' +
                ", urls=" + urls +
                '}';
    }
}
