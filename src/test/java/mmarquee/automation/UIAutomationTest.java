/*
 * Copyright 2016-17 inpwtepydjuf@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mmarquee.automation;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import mmarquee.automation.controls.Application;
import mmarquee.automation.controls.Panel;
import mmarquee.automation.controls.Window;
import org.junit.*;
import org.mockito.Mockito;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.uiautomation.IUIAutomation;
import mmarquee.uiautomation.IUIAutomationCondition;
import mmarquee.uiautomation.TreeScope;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

/**
 * @author Mark Humphreys
 * Date 19/07/2016.
 *
 * Currently these tests require windows to run
 */
public class UIAutomationTest extends BaseAutomationTest {
//    private static Logger logger = Logger.getLogger(UIAutomationTest.class.getName());
//
//    @Spy
//    private Unknown mockUnknown;
//
//    static {
//        logger.info("static");
//        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
//    }
//
//    @BeforeClass
//    public static void checkOs() throws Exception {
//        Assume.assumeTrue(isWindows());
//    }
//
//    private static boolean isWindows() {
//        return System.getProperty("os.name").toLowerCase().contains("windows");
//    }
//
//    @Before
//    public void setup() {
//        logger.info("setup start");
//        MockitoAnnotations.initMocks(this);
//        logger.info("setup end");
//    }
//
//    @Test
//    public void testGetInstance() {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        assertTrue("instance:" + instance.toString(), instance != null);
//    }
//
//    @Test
//    public void testGetRootElement() throws AutomationException {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        Element root = instance.getRootElement();
//
//        assertTrue("root:" + root.currentName(), root.currentName().startsWith("Desktop"));
//    }
//
//    @Test
//    public void testGetDesktop() throws Exception {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        Panel desktop = instance.getDesktop();
//
//        assertEquals(instance.getRootElement(),desktop.getElement());
//        assertTrue("desktop is correct: " + desktop.getName(), desktop.getName().startsWith("Desktop"));
//    }
//
//    @Test
//    public void testCompareElementsWhenTheSame() {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        IntByReference same = new IntByReference();
//
//        // The root element
//        PointerByReference pRoot = new PointerByReference();
//
//        instance.getRootElement(pRoot);
//
//        instance.compareElements(pRoot.getValue(), pRoot.getValue(), same);
//
//        int value = same.getValue();
//
//        assertTrue("Compare Element:" + value, value != 0);
//    }
//
//    @Test
//    public void testCreateFalseCondtion() {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        try {
//            PointerByReference condition = instance.createFalseCondition();
//
//            Unknown unk = new Unknown(condition.getValue());
//
//            PointerByReference pUnknown1 = new PointerByReference();
//
//            WinNT.HRESULT result = unk.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pUnknown1);
//
//            assertTrue("Create FalseCondition:" + COMUtils.SUCCEEDED(result), COMUtils.SUCCEEDED(result));
//        } catch (AutomationException ex) {
//            assertTrue("Ouch", false);
//        }
//    }
//
//    @Test
//    public void testGetDesktopWindows() throws PatternNotFoundException, AutomationException {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        List<Window> windows = instance.getDesktopWindows();
//
//        assertFalse("getDesktopWindows finds more than one window", windows.size() == 0);
//    }
//
//    @Test
//    public void testGetDesktopObjects() throws PatternNotFoundException, AutomationException {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        List<Panel> objects = instance.getDesktopObjects();
//
//        assertFalse("getDesktopObjects finds more than one object", objects.size() == 0);
//    }
//
//    @Test
//    public void testCreatePropertyCondition() {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
//        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString("SOMETHING");
//        variant.setValue(Variant.VT_BSTR, sysAllocated);
//
//        try {
//            try {
//                PointerByReference pCondition = instance.createPropertyCondition(PropertyID.AutomationId.getValue(), variant);
//
//                Unknown unk = new Unknown(pCondition.getValue());
//
//                PointerByReference pUnknown1 = new PointerByReference();
//
//                WinNT.HRESULT result = unk.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pUnknown1);
//
//                assertTrue("CreatePropertyCondition:" + COMUtils.SUCCEEDED(result), COMUtils.SUCCEEDED(result));
//            } catch (AutomationException ex) {
//                assertTrue("Exception", false);
//            }
//        } finally {
//            OleAuto.INSTANCE.SysFreeString(sysAllocated);
//        }
//    }
//
//    @Test
//    public void testCreateNotCondition() {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
//        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString("SOMETHING");
//        variant.setValue(Variant.VT_BSTR, sysAllocated);
//
//        try {
//            try {
//                // Create first condition to use
//                PointerByReference pCondition =
//                        instance.createPropertyCondition(PropertyID.AutomationId.getValue(), variant);
//
//                // Create the actual condition
//                PointerByReference notCondition =
//                        instance.createNotCondition(pCondition);
//
//                // Checking
//                Unknown unk = new Unknown(notCondition.getValue());
//
//                PointerByReference pUnknown1 = new PointerByReference();
//
//                WinNT.HRESULT result = unk.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pUnknown1);
//
//                assertTrue("CreateNotCondition:" + COMUtils.SUCCEEDED(result), COMUtils.SUCCEEDED(result));
//            } catch (AutomationException ex) {
//                assertTrue("Exception", false);
//            }
//        } finally {
//            OleAuto.INSTANCE.SysFreeString(sysAllocated);
//        }
//    }
//
//    @Test
//    public void testCreateAndCondition() {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
//        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString("SOMETHING");
//        variant.setValue(Variant.VT_BSTR, sysAllocated);
//
//        try {
//            try {
//                // Create first condition to use
//                PointerByReference pCondition0 =
//                        instance.createPropertyCondition(PropertyID.AutomationId.getValue(), variant);
//
//                // Create first condition to use
//                PointerByReference pCondition1 =
//                        instance.createPropertyCondition(PropertyID.AutomationId.getValue(), variant);
//
//                // Create the actual condition
//                PointerByReference andCondition =
//                        instance.createAndCondition(pCondition0, pCondition1);
//
//                // Checking
//                Unknown unk = new Unknown(andCondition.getValue());
//                PointerByReference pUnk = new PointerByReference();
//
//                PointerByReference pUnknown1 = new PointerByReference();
//
//                WinNT.HRESULT result = unk.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pUnknown1);
//
//                assertTrue("CreateAndCondition:" + COMUtils.SUCCEEDED(result), COMUtils.SUCCEEDED(result));
//            } catch (AutomationException ex) {
//                assertTrue("Exception", false);
//            }
//        } finally {
//            OleAuto.INSTANCE.SysFreeString(sysAllocated);
//        }
//    }
//
//    @Test
//    public void testCreateOrCondition() {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
//        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString("SOMETHING");
//        variant.setValue(Variant.VT_BSTR, sysAllocated);
//
//        try {
//            try {
//                // Create first condition to use
//                PointerByReference pCondition0 =
//                        instance.createPropertyCondition(PropertyID.AutomationId.getValue(), variant);
//
//                // Create first condition to use
//                PointerByReference pCondition1 =
//                        instance.createPropertyCondition(PropertyID.AutomationId.getValue(), variant);
//
//                // Create the actual condition
//                PointerByReference condition =
//                        instance.createOrCondition(pCondition0, pCondition1);
//
//                // Checking
//                Unknown unk = new Unknown(condition.getValue());
//                PointerByReference pUnk = new PointerByReference();
//
//                PointerByReference pUnknown1 = new PointerByReference();
//
//                WinNT.HRESULT result = unk.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pUnknown1);
//
//                assertTrue("CreateOrCondition:" + COMUtils.SUCCEEDED(result), COMUtils.SUCCEEDED(result));
//            } catch (AutomationException ex) {
//                assertTrue("Exception", false);
//            }
//        } finally {
//            OleAuto.INSTANCE.SysFreeString(sysAllocated);
//        }
//    }
//
//    @Test
//    public void testCreateTrueCondition() {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        try {
//            PointerByReference pCondition = instance.createTrueCondition();
//            PointerByReference first = new PointerByReference();
//
//            // Check whether it is a condition
//
//            Unknown unk = new Unknown(pCondition.getValue());
//            PointerByReference pUnk = new PointerByReference();
//
//            Guid.REFIID refiid3 = new Guid.REFIID(IUIAutomationCondition.IID);
//
//            PointerByReference pUnknown1 = new PointerByReference();
//
//            WinNT.HRESULT result = unk.QueryInterface(refiid3, pUnknown1);
//
//            assertTrue("Create TrueCondition:" + COMUtils.SUCCEEDED(result), COMUtils.SUCCEEDED(result));
//        } catch (AutomationException ex) {
//            assertTrue("Exception", false);
//        }
//    }
//
//    @Test
//    public void testCompareElementsWhenNotTheSame() throws Exception {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        IntByReference same = new IntByReference();
//
//        // The root element
//        PointerByReference pRoot = new PointerByReference();
//
//        instance.getRootElement(pRoot);
//
//        // Get the first descendant of the root element
//        Element root = instance.getRootElement();
//
//        PointerByReference pCondition = instance.createTrueCondition();
//        PointerByReference first = new PointerByReference();
//
//        root.getElement().findFirst(new TreeScope(TreeScope.DESCENDANTS), pCondition.getValue(), first);
//
//        instance.compareElements(pRoot.getValue(), first.getValue(), same);
//
//        int value = same.getValue();
//
//        assertTrue("Compare Element:" + value, value != -1);
//    }
//
//    @Test
//    public void testCreateNamePropertyCondition_Does_Not_Throw_Exception() throws AutomationException {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        instance.createNamePropertyCondition("ID");
//    }
//
//    @Test
//    public void testCreateControlTypeCondition_Does_Not_Throw_Exception() throws AutomationException {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        instance.createControlTypeCondition(ControlType.Button);
//    }
//
//    @Test
//    public void testCreateClassNamePropertyCondition_Does_Not_Throw_Exception() throws AutomationException {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        instance.createClassNamePropertyCondition("XTestButton");
//    }
//
//    @Test
//    public void testCreateAutomationIdPropertyCondition_Does_Not_Throw_Exception() throws AutomationException {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        instance.createAutomationIdPropertyCondition("ID");
//    }
//
//    @Test
//    public void testLaunchOrAttach_Fails_When_No_executable() {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        boolean failure = false;
//
//        try {
//            instance.launchOrAttach("notepad99.exe");
//        } catch (Throwable e) {
//            failure = true;
//        }
//
//        assertTrue("Should have failed", failure);
//    }
//
//    @Test
//    public void testLaunchOrAttach_Succeeds_When_Not_Running() throws Exception {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        Application app = null;
//        try {
//        	app = instance.launchOrAttach("notepad.exe");
//        } finally {
//        	if (app != null) {
//                this.andRest();
//        		app.quit(getLocal("notepad.title"));
//        	}
//        }
//    }
//
//    interface TestWithNotepad {
//    	void doTest(UIAutomation instance, Application app) throws Exception;
//    }
//
//    void doTestWithNotepad(TestWithNotepad test) throws Exception {
//		UIAutomation instance = UIAutomation.getInstance();
//        Application app = instance.launch("notepad.exe");
//        this.andRest();
//
//        try {
//        	test.doTest(instance, app);
//        } finally {
//            app.quit(getLocal("notepad.title"));
//        }
//    }
//
//    @Test
//    public void testLaunchOrAttach_Succeeds_When_Already_Running() throws Exception {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        doTestWithNotepad((instance, app) -> {
//            Application launched = instance.launchOrAttach("notepad.exe");
//            assertTrue("Should be the same name", launched.getName().equals(app.getName()));
//        });
//    }
//
//    @Test(expected=AutomationException.class)
//    public void findProcess_Fails_When_No_executable() throws Exception {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//        instance.findProcess("notepad99.exe");
//    }
//
//    @Test(expected=AutomationException.class)
//    public void findProcess_fails_When_Not_Running() throws Exception {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//        instance.findProcess("notepad.exe");
//    }
//
//    @Test
//    public void findProcess_Succeeds_When_Already_Running() throws Exception {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        doTestWithNotepad((instance, app) -> {
//    	    Application launched = instance.findProcess("notepad.exe");
//            assertTrue("Should be the same name", launched.getName().equals(app.getName()));
//        });
//    }
//
//    @Test(expected=AutomationException.class)
//    public void findProcess_ByRegexPattern_Fails_When_No_executable() throws Exception {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//        instance.findProcess(Pattern.compile("n\\S+pad99\\.exe"));
//    }
//
//    @Test(expected=AutomationException.class)
//    public void findProcess_ByRegexPattern_fails_When_Not_Running() throws Exception {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//        instance.findProcess(Pattern.compile("n\\S+pad\\.exe"));
//    }
//
//    @Test
//    public void findProcess_ByRegexPattern_Succeeds_When_Already_Running() throws Exception {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation instance = UIAutomation.getInstance();
//
//        Application app = instance.launch("notepad.exe");
//
//        try {
//            this.andRest();
//
//            Application launched = instance.findProcess(Pattern.compile("n\\S+pad\\.exe"));
//
//            assertTrue("Should be the same name", launched.getName().equals(app.getName()));
//
//        } finally {
//            app.quit(getLocal("notepad.title"));
//        }
//    }
//
//    @Test
//    public void testCreateTrueCondition_Succeeds_When_Automation_Returns_True()
//            throws AutomationException {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
//
//        when(mocked_automation.createTrueCondition(isA(PointerByReference.class))).thenReturn(0);
//
//        UIAutomation instance = new UIAutomation(mocked_automation);
//
//        instance.createTrueCondition();
//    }
//
//    @Test(expected = AutomationException.class)
//    public void testCreateTrueCondition_Throws_Exception_When_Automation_Returns_False()
//            throws AutomationException {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
//
//        when(mocked_automation.createTrueCondition(isA(PointerByReference.class))).thenReturn(-1);
//
//        UIAutomation local_instance = new UIAutomation(mocked_automation);
//
//        local_instance.createTrueCondition();
//    }
//
//    @Test(expected = AutomationException.class)
//    public void testCreateNotCondition_Throws_Exception_When_Automation_Returns_False()
//            throws AutomationException {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
//
//        when(mocked_automation.createNotCondition(any(), any(PointerByReference.class))).thenReturn(-1);
//
//        UIAutomation local_instance = new UIAutomation(mocked_automation);
//
//        PointerByReference pbr = new PointerByReference();
//
//        local_instance.createNotCondition(pbr);
//    }
//
//    @Test(expected = AutomationException.class)
//    @Ignore("Mocking fails - needs investigation")
//    public void testCreateAndCondition_Throws_Exception_When_Automation_Returns_False()
//            throws AutomationException {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        IUIAutomation mocked = Mockito.mock(IUIAutomation.class);
//
//        when(mocked.createAndCondition(any(Pointer.class), any(Pointer.class), any(PointerByReference.class))).thenReturn(-1);
//
//        UIAutomation instanceWithMocking = new UIAutomation(mocked);
//
//        instanceWithMocking.createAndCondition(new PointerByReference(), new PointerByReference());
//    }
//
//    @Test(expected = AutomationException.class)
//    @Ignore("Mocking seems to fail - needs investigation")
//    public void testCreateOrCondition_Throws_Exception_When_Automation_Returns_False()
//            throws AutomationException {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        IUIAutomation mocked = Mockito.mock(IUIAutomation.class);
//
//        when(mocked.createOrCondition(any(Pointer.class), any(Pointer.class), any(PointerByReference.class))).thenReturn(-1);
//
//        UIAutomation instanceWithMocking = new UIAutomation(mocked);
//
//        instanceWithMocking.createOrCondition(new PointerByReference(), new PointerByReference());
//    }
//
//    @Test (expected = ItemNotFoundException.class)
//    public void test_GetDesktopMenu_Throws_Exception_When_Not_Found()
//            throws IOException, AutomationException, PatternNotFoundException {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation.getInstance().getDesktopMenu("Menu");
//    }
//
//    @Test
//    public void testGetDesktopWindow_succeeds() throws Exception {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        doTestWithNotepad((instance, app) -> {
//            Window window = instance.getDesktopWindow(getLocal("notepad.title"));
//            assertNotNull(window);
//        });
//    }
//
//    @Test(expected = ItemNotFoundException.class)
//    public void testGetDesktopWindow_Fails_When_Not_Window_Present() throws IOException, AutomationException, PatternNotFoundException {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation.getInstance().getDesktopWindow("Untitled - Notepad99");
//    }
//
//    @Test
//    public void testGetDesktopWindow_WithRegexp_succeeds() throws Exception {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        doTestWithNotepad((instance, app) -> {
//            Window window = instance.getDesktopWindow(Pattern.compile(Pattern.quote(getLocal("notepad.title"))));
//            assertNotNull(window);
//        });
//    }
//
//    @Test(expected = ItemNotFoundException.class)
//    public void testGetDesktopWindow_WithRegexp_Fails_When_Not_Window_Present() throws IOException, AutomationException, PatternNotFoundException {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation.getInstance().getDesktopWindow(Pattern.compile(".*Notepad99"));
//    }
//
//    @Test
//    public void testGetWindow_succeeds() throws Exception {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        doTestWithNotepad((instance, app) -> {
//            Window window = instance.getWindow(getLocal("notepad.title"));
//            assertNotNull(window);
//        });
//    }
//
//    @Test(expected = ItemNotFoundException.class)
//    public void testGetWindow_Fails_When_Not_Window_Present() throws IOException, AutomationException, PatternNotFoundException {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation.getInstance().getWindow("Untitled - Notepad99");
//    }
//
//    @Test
//    public void testGetWindow_WithRegexp_succeeds() throws Exception {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        doTestWithNotepad((instance, app) -> {
//            Window window = instance.getWindow(Pattern.compile(Pattern.quote(getLocal("notepad.title"))));
//            assertNotNull(window);
//        });
//    }
//
//    @Test(expected = ItemNotFoundException.class)
//    public void testGetWindow_WithRegexp_Fails_When_Not_Window_Present() throws IOException, AutomationException, PatternNotFoundException {
//        logger.info(new Throwable().getStackTrace()[0].getMethodName());
//        UIAutomation.getInstance().getWindow(Pattern.compile(".*Notepad99"));
//    }
}
