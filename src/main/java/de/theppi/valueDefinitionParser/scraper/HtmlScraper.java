package de.theppi.valueDefinitionParser.scraper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import de.theppi.valueDefinitionParser.CssValueDefinition;

public class HtmlScraper {
    
    public HashMap<String, CssValueDefinition> parse() {
        try {
            System.out.println("Start reading website");
            List<String> linklist = getLinkList();
            HashMap<String, CssValueDefinition> definitionMap = getDefinitionMap(linklist);
            return definitionMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private HashMap<String, CssValueDefinition> getDefinitionMap(List<String> linklist) throws IOException {
        HashMap<String, CssValueDefinition> defintionMap = new HashMap<>();
        for(String link : linklist) {
            String name = link.substring(link.lastIndexOf("/") + 1);
            if(name.startsWith("-") || name.startsWith(":") || name.startsWith("@")) {
                continue;
            }
            Document doc = null;
            try {
                doc = Jsoup.connect(link).get();
            } catch (IOException ex) {
                System.err.println("Could not get " + link);
                continue;
            }
            Element syntax = doc.selectFirst("#Formal_syntax");
            if(syntax == null) continue;
            
            Element definition = syntax.nextElementSibling();
            while(definition != null && !definition.hasClass("syntaxbox")) definition = definition.nextElementSibling();
            if(definition == null) continue;
            
            CssValueDefinition valueDef = new CssValueDefinition();
            StringBuilder defSb = new StringBuilder();
            for(Element child : definition.children()) {
                if(child.tagName().equalsIgnoreCase("p")) {
                    valueDef.addCondition(child.selectFirst("code").text());
                    child.remove();
                    continue;
                }
                defSb.append(child.text());
                
            }
            valueDef.setDefinition(definition.text());
            valueDef.setName(name);
            defintionMap.put(name, valueDef);
        }
        return defintionMap;
    }

    private List<String> getLinkList() throws IOException {
        URL url = new URL("https://developer.mozilla.org/en-US/docs/Web/CSS/Reference");
        Document doc = Jsoup.connect(url.toExternalForm()).get();
        Element index = doc.selectFirst("#Keyword_index").nextElementSibling();
        
        while(index.selectFirst(".index") == null) {
            index = index.nextElementSibling();
        }
        List<String> linklist = new ArrayList<>();
        for(Element linkElement : index.select("a")) {
            linklist.add(url.getProtocol() + "://" + url.getHost() + linkElement.attr("href"));
        }
        return linklist;
    }
}
