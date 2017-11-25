package io.zipcoder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemParserTest {

    private String nothing = "";

    private String rawSingleItem =    "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##";

    private String rawSingleItemIrregularSeperatorSample = "naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##";

    private String rawBrokenSingleItem =    "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##";

    private String rawMultipleItems = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##"
                                      +"naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##"
                                      +"NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##";
    private ItemParser itemParser;

    @Before
    public void setUp(){
        itemParser = new ItemParser();
    }

    @Test
    public void parseRawDataIntoStringArrayTest(){
        Integer expectedArraySize = 3;
        ArrayList<String> items = itemParser.parseRawDataIntoStringArray(rawMultipleItems);
        Integer actualArraySize = items.size();
        assertEquals(expectedArraySize, actualArraySize);
    }

    @Test
    public void patternMatcherNameTrueTest() throws ItemParseException {

        boolean expected = true;
        boolean actual = itemParser.patternMatcherName(rawBrokenSingleItem);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void patternMatcherNameFalseTest() throws ItemParseException {

        boolean expected = false;
        boolean actual = itemParser.patternMatcherName(nothing);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void patternMatcherPriceTrueTest() throws ItemParseException {

        boolean expected = true;
        boolean actual = itemParser.patternMatcherPrice(rawBrokenSingleItem);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void patternMatcherPriceFalseTest() throws ItemParseException {

        boolean expected = false;
        boolean actual = itemParser.patternMatcherPrice(nothing);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void patternMatcherTypeTrueTest() throws ItemParseException {

        boolean expected = true;
        boolean actual = itemParser.patternMatcherType(rawBrokenSingleItem);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void patternMatcherTypeFalseTest() throws ItemParseException {

        boolean expected = false;
        boolean actual = itemParser.patternMatcherType(nothing);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void patternMatcherExpTrueTest() throws ItemParseException {

        boolean expected = true;
        boolean actual = itemParser.patternMatcherExp(rawSingleItem);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void patternMatcherExpFalseTest() throws ItemParseException {

        boolean expected = false;
        boolean actual = itemParser.patternMatcherExp(nothing);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseStringIntoItemTest() throws ItemParseException{
        Item expected = new Item("milk", 3.23, "food","1/25/2016");
        Item actual = itemParser.parseStringIntoItem(rawSingleItem);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test(expected = ItemParseException.class)
    public void parseBrokenStringIntoItemTest() throws ItemParseException{
        itemParser.parseStringIntoItem(rawBrokenSingleItem);
    }

    @Test
    public void findKeyValuePairsInRawItemDataTest(){
        Integer expected = 4;
        Integer actual = itemParser.findKeyValuePairsInRawItemData(rawSingleItem).size();
        assertEquals(expected, actual);
    }

    @Test
    public void findKeyValuePairsInRawItemDataTestIrregular(){
        Integer expected = 4;
        Integer actual = itemParser.findKeyValuePairsInRawItemData(rawSingleItemIrregularSeperatorSample).size();
        assertEquals(expected, actual);
    }
}
