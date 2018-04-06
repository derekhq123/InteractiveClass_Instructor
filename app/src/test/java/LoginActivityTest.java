

/**
 * Created by Administrator on 12/3/2018.
 */

import android.app.Activity;
import android.widget.EditText;
import android.widget.Button;

import com.example.qnmd.BuildConfig;
import com.example.qnmd.LoginActivity;
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
public class LoginActivityTest {
    private EditText ed1;
    private EditText ed2;
    private Button b1;
    private Button b2;

    @Before
    public void setUp() {
        Activity activity = Robolectric.setupActivity(LoginActivity.class);
        b1 = (Button) activity.findViewById(R.id.button);
        ed1 = (EditText) activity.findViewById(R.id.editText);
        ed2 = (EditText) activity.findViewById(R.id.editText2);
        b2 = (Button) activity.findViewById(R.id.button2);
    }
    @Test
    public void loginSuccess() {
        ed1.setText("Sudipta");
        ed2.setText("1");
        b1.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity has started", application.getNextStartedActivity(), is(notNullValue()));
    }

    @Test
    public void loginWithEmptyUsernameAndPassword() {
        b1.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity should not started", application.getNextStartedActivity(), is(nullValue()));
        assertThat("Show error for Email field ", ed1.getError(), is(CoreMatchers.notNullValue()));
        assertThat("Show error for Password field ", ed2.getError(), is(CoreMatchers.notNullValue()));
    }

    @Test
    public void loginFailure() {
        ed1.setText("invalidusername");
        ed2.setText("invalidpassword");
        b1.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity should not started", application.getNextStartedActivity(), is(nullValue()));
        assertThat("Show error for Username field ", ed1.getError(), is(CoreMatchers.notNullValue()));
        assertThat("Show error for Password field ", ed2.getError(), is(CoreMatchers.notNullValue()));
    }
}
