package de.theppi.valueDefinitionParser;

import java.util.HashMap;
import de.theppi.valueDefinitionParser.parser.CssValueDefinitionParser;
import de.theppi.valueDefinitionParser.scraper.HtmlScraper;

public class ValueDefinitionParser {
    public static void main (String...args) {
        HtmlScraper scraper = new HtmlScraper();
        HashMap<String, CssValueDefinition> definitionMap = scraper.parse();
    	CssValueDefinitionParser parser = new CssValueDefinitionParser(System.in);
    }
}
