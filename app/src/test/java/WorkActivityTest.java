/**
 * Created by derek on 3/15/2018.
 */
import android.app.Activity;
import android.widget.EditText;
import android.widget.Button;

import com.example.qnmd.BuildConfig;
import com.example.qnmd.LoginActivity;

import com.example.qnmd.R;
import com.example.qnmd.WorkActivity;

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
public class WorkActivityTest {

    private Button btn;
    @Before
    public void setUp(){
        Activity activity = Robolectric.setupActivity(WorkActivity.class);
        btn=(Button)activity.findViewById(R.id.submit);

    }

    @Test
    public void testSubmit(){
        btn.performClick();
        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity has started", application.getNextStartedActivity(), is(notNullValue()));
    }
}
