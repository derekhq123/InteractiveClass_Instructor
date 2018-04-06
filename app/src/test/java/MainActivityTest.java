/**
 * Created by derek on 4/3/2018.
 */

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


/**
 * Created by Administrator on 12/3/2018.
 */

import android.app.Activity;
import android.widget.EditText;
import android.widget.Button;

import com.example.qnmd.BuildConfig;
import com.example.qnmd.LoginActivity;
import com.example.qnmd.MainActivity;
import com.example.qnmd.R;


import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {
    Button b1,b2,b3;


    @Before
    public void setUp() {
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        b1 = (Button) activity.findViewById(R.id.button1);

        b2 = (Button) activity.findViewById(R.id.button2);
        b3 = (Button) activity.findViewById(R.id.button3);


    }
    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull("Button could not be found", b1);
        assertTrue("Button contains incorrect text",
                "Classroom1".equals( b1.getText().toString()));
    }
    @Test
    public void NextActivity() {

        b1.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity has started", application.getNextStartedActivity(), is(notNullValue()));
    }


}

