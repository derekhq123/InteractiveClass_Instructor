package com.example.qnmd;

/**
 * Created by derek on 4/4/2018.
 */
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
@MediumTest
@RunWith(AndroidJUnit4.class)
public class ExamActivityTest {
    @Rule
    public ActivityTestRule<ExamActivity> rule  = new  ActivityTestRule<ExamActivity>(ExamActivity.class){
        @Override
        protected Intent getActivityIntent() {
            InstrumentationRegistry.getTargetContext();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.putExtra("classname", "Classroom1");
            return intent;
        }
    };

    @Test
    public void testIntent() throws Exception{
        ExamActivity activity = rule.getActivity();
        assertEquals(activity.className,"Classroom1");
    }
    @Test
    public void ensureLayout() throws Exception{
        ExamActivity activity = rule.getActivity();
        EditText quizTitle=(EditText)activity.findViewById(R.id.quizTitle);;
        Button submit=(Button)activity.findViewById(R.id.SUBMIT);
        assertThat(quizTitle,notNullValue());
        assertThat(quizTitle, instanceOf(EditText.class));
        assertThat(submit,notNullValue());
        assertThat(submit, instanceOf(Button.class));
    }
    @Test
    public void ensureListViewIsPresent() throws Exception {
        ExamActivity activity = rule.getActivity();
        View viewById = activity.findViewById(R.id.questionList);
        assertThat(viewById,notNullValue());
        assertThat(viewById, instanceOf(ListView.class));
        ListView listView = (ListView) viewById;
        ListAdapter adapter = listView.getAdapter();
        assertThat(adapter, instanceOf(BaseAdapter.class));
        assertThat(adapter.getCount(), greaterThan(0));
        assertThat(adapter.getCount(), greaterThan(5));

    }

}
