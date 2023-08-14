package fd;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class FDIATest extends AssertJSwingJUnitTestCase {

    private FrameFixture window;

    @BeforeEach
    @Override
    protected void onSetUp() {
        Robot robot = BasicRobot.robotWithNewAwtHierarchy();

        // launches App.java's main method on the event dispatch thread
        EntryPointFrame entryPointFrame = org.assertj.swing.edt.GuiActionRunner.execute(() -> {
            App.main(new String[]{});
            return (EntryPointFrame) JFrame.getFrames()[0];
        });

        // finds the main window using the instance of EntryPointFrame
        window = new FrameFixture(robot, entryPointFrame);
        window.show(); // shows the frame to test
    }

    @Test
    public void testInitialViewDisplayed() {
        window.requireVisible(); // checks that the EntryFrameView is visible
    }

    @AfterEach
    @Override
    protected void onTearDown() {
        window.cleanUp();
    }
}
