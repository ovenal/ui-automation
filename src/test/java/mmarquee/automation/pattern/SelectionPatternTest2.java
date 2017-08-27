package mmarquee.automation.pattern;

import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.IntByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationElementArray;
import mmarquee.automation.uiautomation.IUIAutomationSelectionPattern;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

/**
 * Created by Mark Humphreys on 15/01/2017.
 *
 * Tests for the lower level calls to COM used in the Selection pattern.
 */
@RunWith(MockitoJUnitRunner.class)
public class SelectionPatternTest2 {
    @Mock
    IUIAutomationSelectionPattern rawPattern;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Spy
    private Unknown mockUnknown;

    @Test
    @Ignore("Needs better tests")
    public void test_getCurrentSelection() throws Exception {

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return 0;
            }
        }).when(rawPattern).getCurrentSelection(any());

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(0);
            }
        }).when(mockUnknown).QueryInterface(any(), any());

        Selection spyPattern = Mockito.spy(new Selection(rawPattern));

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        IUIAutomationElementArray mockArray = Mockito.mock(IUIAutomationElementArray.class);

        doReturn(mockArray)
                .when(spyPattern)
                .convertPointerToElementArray(any());

        spyPattern.getCurrentSelection();
    }

    @Test
    public void test_canSelectMultiple_Throws_Error_When_Query_Interface_Returns_Error() throws Exception {

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return 0;
            }
        }).when(rawPattern).getCurrentCanSelectMultiple(any());

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(-1);
            }
        }).when(mockUnknown).QueryInterface(any(), any());

        Selection spyPattern = Mockito.spy(new Selection(rawPattern));

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        spyPattern.canSelectMultiple();
    }

    @Test(expected=AutomationException.class)
    public void test_getCurrentSelection_Throws_Error_When_Query_Interface_Returns_Error() throws Exception {

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return 1;
            }
        }).when(rawPattern).getCurrentSelection(any());

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(-1);
            }
        }).when(mockUnknown).QueryInterface(any(), any());

        Selection spyPattern = Mockito.spy(new Selection(rawPattern));

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        spyPattern.getCurrentSelection();
    }

    public void test_canSelectMultiple_Returns_False_When_Interface_Returns_True() throws Exception {

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(0);

                return 0;
            }
        }).when(rawPattern).getCurrentCanSelectMultiple(any());

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(-1);
            }
        }).when(mockUnknown).QueryInterface(any(), any());

        Selection pattern = new Selection(rawPattern);

        Selection spyPattern = Mockito.spy(new Selection(rawPattern));

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        Boolean value = pattern.canSelectMultiple();

        assertFalse(value);
    }

    @Test(expected = AutomationException.class)
    public void test_canSelectMultiple_Throws_Exception_When_Interface_Returns_Error() throws Exception {

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(0);

                return -1;
            }
        }).when(rawPattern).getCurrentCanSelectMultiple(any());

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(-1);
            }
        }).when(mockUnknown).QueryInterface(any(), any());

        Selection pattern = new Selection(rawPattern);

        Selection spyPattern = Mockito.spy(new Selection(rawPattern));

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        Boolean value = pattern.canSelectMultiple();

        assertFalse(value);
    }

    public void test_canSelectMultiple_Returns_True_When_Interface_Returns_True() throws Exception {

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(1);

                return 0;
            }
        }).when(rawPattern).getCurrentCanSelectMultiple(any());

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(-1);
            }
        }).when(mockUnknown).QueryInterface(any(), any());

        Selection pattern = new Selection(rawPattern);

        Selection spyPattern = Mockito.spy(new Selection(rawPattern));

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        Boolean value = pattern.canSelectMultiple();

        assertTrue(value);
    }
}
