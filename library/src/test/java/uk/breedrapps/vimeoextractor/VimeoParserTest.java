package uk.breedrapps.vimeoextractor;

import android.text.TextUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by edgeorge on 13/04/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class VimeoParserTest {

    private VimeoParser validParser, invalidParser;

    @Before
    public void setUp(){
        //Create parsers for a valid and an invalid Vimeo URL
        validParser = new VimeoParser("http://vimeo.com/12345");
        invalidParser = new VimeoParser("http://vimeo.com/foo");

        //Mock TextUtils.isDigitsOnly() response
        PowerMockito.mockStatic(TextUtils.class);
        when(TextUtils.isDigitsOnly(any(CharSequence.class))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                final CharSequence arg = (CharSequence) invocation.getArguments()[0];
                //Same as TextUtils source
                final int len = arg.length();
                for (int i = 0; i < len; i++) {
                    if (!Character.isDigit(arg.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        });
    }

    @Test
    public void testIsVimeoURLValid() throws Exception {
        assertTrue(validParser.isVimeoURLValid());
        assertFalse(invalidParser.isVimeoURLValid());
    }

    @Test
    public void testGetExtractedIdentifier() throws Exception {
        assertEquals(validParser.getExtractedIdentifier(), "12345");
        assertEquals(invalidParser.getExtractedIdentifier(), "foo");
    }

    @Test
    public void testGetUrl() throws Exception {
        assertEquals(validParser.getUrl(), "http://vimeo.com/12345");
        assertEquals(invalidParser.getUrl(), "http://vimeo.com/foo");
    }
}