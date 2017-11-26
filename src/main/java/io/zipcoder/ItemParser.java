package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;

public class ItemParser {
    Integer count= 0;


    public ArrayList<String> parseRawDataIntoStringArray(String rawData){
        String stringPattern = "##";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawData);
        return response;
    }

    public boolean patternMatcherName(String rawItem) throws ItemParseException {
        Pattern itemNamePattern = Pattern.compile("(^[n|N][a|A][m|M][e|E]):([a-z|A-Z|0-9]+)");
        Matcher matchItemName = itemNamePattern.matcher(rawItem);

        return matchItemName.find();

    }

    public boolean patternMatcherPrice(String rawItem) throws ItemParseException {
        Pattern itemPricePattern = Pattern.compile("([p|P][r|R][i|I][c|C][e|E]):([0-9]+\\.[0-9]+)");
        Matcher matchItemPrice = itemPricePattern.matcher(rawItem);

        return matchItemPrice.find();

    }

    public boolean patternMatcherType(String rawItem) throws ItemParseException {
        Pattern itemTypePattern = Pattern.compile("([t|T][y|Y][p|P][e|E]):([a-z|A-z]+)");
        Matcher matchItemType = itemTypePattern.matcher(rawItem);

        return matchItemType.find();

    }

    public boolean patternMatcherExp(String rawItem) throws ItemParseException {
        Pattern itemExpPattern = Pattern.compile("([e|E][x|X][p|P][i|I][r|R][a|A][t|T][i|I][o|O|0][n|N]):([0-9]+/[0-9]+/[0-9]+)");
        Matcher matchItemExp = itemExpPattern.matcher(rawItem);

        return matchItemExp.find();
    }

    public String getStringValuePair(String rawItem){
        ArrayList<String> tofind = findKeyValuePairsInRawItemData(rawItem);
        String toReturn = tofind.get(0).toLowerCase();
        return toReturn;
    }

    public String getStringValueandItem(String rawItem) throws ItemParseException {
        ArrayList<String> name = new ArrayList<String>();
        if(patternMatcherName(rawItem)){
            ArrayList<String>keyValue =findKeyValuePairsInRawItemData(rawItem);
            name = splitStringWithRegexPattern(":", keyValue.get(0));
        }
        return name.get(1).toLowerCase();
    }

    private String compareMatches(Pattern patern, String keyField) {


        return null;

    }




//    public Item parseStringIntoItem(String rawItem) throws ItemParseException {
//
//        //ArrayList<String> valuePairs = findKeyValuePairsInRawItemData(rawItem);
//
//
//
//        return null;
//    }

        //        String itemName = itemNamePattern;
//        itemName = compareMatch(itemNamePattern,itemName);
//
//        String itemPrice = valuePairs.get(1);
//        itemPrice = compareMatch(itemPricePattern,itemPrice);
//
//        String itemType = valuePairs.get(2);
//        itemType = compareMatch(itemTypePattern,itemType);
//
//        String itemExp = valuePairs.get(3);
//        itemExp = compareMatch(itemExpPattern,itemExp);
//
//        return new Item(itemName, Double.parseDouble(itemPrice), itemType, itemExp);
//        while (matchItemName.find()) try {
//            String name = matchItemName.group(0);
//            String[] keyValue = itemSplit.split(name);
//            String nameValue = keyValue[1];
//            return new Item(name, Double.parseDouble(price), type, exp);
//        } catch (ItemParseException e) {
//            count++;
//        }
//        return  null;
//    }

//    private String compareMatch(Pattern pattern, String stringValue) throws ItemParseException {
//        if(pattern.matcher(stringValue).matches()) {
//            ArrayList<String> splitValue = splitStringWithRegexPattern(":", stringValue);
//            stringValue = splitValue.get(1).toUpperCase();
//        }
//
//        else {
//            throw new ItemParseException("This item can't be parsed: " + stringValue);
//        }
//
//        return stringValue;
//    }

    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem){
        String stringPattern = "[;|^|%|@|*|!]";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawItem);
        return response;
    }

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString){
        return new ArrayList<String>(Arrays.asList(inputString.split(stringPattern)));
    }



}
