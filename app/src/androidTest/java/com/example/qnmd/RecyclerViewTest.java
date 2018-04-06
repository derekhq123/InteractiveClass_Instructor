/**
 * Created by derek on 4/4/2018.
 */
package com.example.qnmd;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
@MediumTest
@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {

    @Rule
    public ActivityTestRule<ShowStudentDetailsActivity> rule  = new  ActivityTestRule<ShowStudentDetailsActivity>(ShowStudentDetailsActivity.class){
        @Override
        protected Intent getActivityIntent() {
            InstrumentationRegistry.getTargetContext();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.putExtra("classname", "Classroom1");
            return intent;
        }
    };


    @Test
    public void ensureRecyclerViewIsPresent() throws Exception {
        ShowStudentDetailsActivity activity = rule.getActivity();

        View viewById = activity.findViewById(R.id.recyclerView);
        //assertThat(viewById.canScrollVertically(-1));
        assertThat(viewById, instanceOf(RecyclerView.class));
        RecyclerView recyclerView = (RecyclerView) viewById;
        sleep(6000);
        assertThat(recyclerView.getAdapter(), instanceOf(RecyclerView.Adapter.class));
        assertThat(recyclerView.getAdapter().getItemCount(), greaterThan(0));

    }
}
