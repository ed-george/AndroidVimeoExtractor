package vimeoextractor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by edgeorge on 13/04/16.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class VimeoParserTest {

    private VimeoParser validParser, invalidParser;

    @Before
    public void setUp() {
        //Create parsers for a valid and an invalid Vimeo URL
        validParser = new VimeoParser("http://vimeo.com/12345");
        invalidParser = new VimeoParser("http://vimeo.com/foo");
    }

    @Test
    public void testIsVimeoURLValid() {
        assertTrue(validParser.isVimeoURLValid());
        assertFalse(invalidParser.isVimeoURLValid());
    }

    @Test
    public void testGetExtractedIdentifier() {
        assertEquals(validParser.getExtractedIdentifier(), "12345");
        assertEquals(invalidParser.getExtractedIdentifier(), "foo");
    }

    @Test
    public void testGetUrl() {
        assertEquals(validParser.getUrl(), "http://vimeo.com/12345");
        assertEquals(invalidParser.getUrl(), "http://vimeo.com/foo");
    }
}