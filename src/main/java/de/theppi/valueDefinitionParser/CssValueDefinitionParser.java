package de.theppi.valueDefinitionParser;

import java.util.HashMap;

import de.theppi.valueDefinitionParser.scraper.HtmlScraper;

public class CssValueDefinitionParser {
    public static void main (String...args) {
        HtmlScraper scraper = new HtmlScraper();
        HashMap<String, CssValueDefinition> definitionMap = scraper.parse();
        
    }
}
