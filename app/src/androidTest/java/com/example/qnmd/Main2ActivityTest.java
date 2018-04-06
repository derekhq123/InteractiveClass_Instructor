package com.example.qnmd;

/**
 * Created by derek on 4/3/2018.
 */
import android.content.ClipData;
import android.content.Intent;
import android.support.design.internal.BottomNavigationItemView;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class Main2ActivityTest {

    @Rule
    public ActivityTestRule<Main2Activity> rule  = new  ActivityTestRule<Main2Activity>(Main2Activity.class){
        @Override
        protected Intent getActivityIntent() {
            InstrumentationRegistry.getTargetContext();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.putExtra("class name", "Classroom1");
            return intent;
        }
    };
    @Test
    public void ensureComponentIsDisplayed() throws Exception {
        Main2Activity activity = rule.getActivity();

        View view1 = activity.findViewById(R.id.navigation);
        assertThat(view1,notNullValue());


        View view2 = activity.findViewById(R.id.navigation_home);
        assertThat(view2,notNullValue());

        View view3 = activity.findViewById(R.id.navigation_dashboard);
        assertThat(view3,notNullValue());

        View view4 = activity.findViewById(R.id.navigation_notifications);
        assertThat(view4,notNullValue());



    }
    @Test
    public void testIntent() throws Exception{
        Main2Activity activity = rule.getActivity();
        assertEquals(activity.className,"Classroom1");
    }
}
