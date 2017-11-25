package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;

public class ItemParser {


    public ArrayList<String> parseRawDataIntoStringArray(String rawData){
        String stringPattern = "##";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawData);
        return response;
    }

    public Item parseStringIntoItem(String rawItem) throws ItemParseException{

        ArrayList<String> valuePairs = findKeyValuePairsInRawItemData(rawItem);

        String itemName = valuePairs.get(0);
        Pattern itemNamePattern = Pattern.compile("([n|N][a|A][m|M][e|E]):([a-z|A-Z|0-9]+)");
        itemName = compareMatch(itemNamePattern,itemName);

        String itemPrice = valuePairs.get(1);
        Pattern itemPricePattern = Pattern.compile("([p|P][r|R][i|I][c|C][e|E]):([0-9]+\\.[0-9]+)");
        itemPrice = compareMatch(itemPricePattern,itemPrice);

        String itemType = valuePairs.get(2);
        Pattern itemTypePattern = Pattern.compile("([t|T][y|Y][p|P][e|E]):([a-z|A-z]+)");
        itemType = compareMatch(itemTypePattern,itemType);

        String itemExp = valuePairs.get(3);
        Pattern itemExpPattern = Pattern.compile("([e|E][x|X][p|P][i|I][r|R][a|A][t|T][i|I][o|O|0][n|N]):([0-9]+\\/[0-9]+\\/[0-9]+)");
        itemExp = compareMatch(itemExpPattern,itemExp);

        return new Item(itemName, Double.parseDouble(itemPrice), itemType, itemExp);
    }

    private String compareMatch(Pattern pattern, String string) {
        

        return string;
    }

    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem){
        String stringPattern = "[;|^|%|@|*|!]";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawItem);
        return response;
    }

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString){
        return new ArrayList<String>(Arrays.asList(inputString.split(stringPattern)));
    }



}
