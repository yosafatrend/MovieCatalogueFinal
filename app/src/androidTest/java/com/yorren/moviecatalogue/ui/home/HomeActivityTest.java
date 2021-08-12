package com.yorren.moviecatalogue.ui.home;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.yorren.moviecatalogue.BuildConfig;
import com.yorren.moviecatalogue.R;
import com.yorren.moviecatalogue.data.source.CatalogueRepository;
import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;
import com.yorren.moviecatalogue.data.source.remote.RemoteDataSource;
import com.yorren.moviecatalogue.data.source.remote.response.GenresItem;
import com.yorren.moviecatalogue.ui.movies.MoviesAdapter;
import com.yorren.moviecatalogue.ui.movies.MoviesViewModel;
import com.yorren.moviecatalogue.utils.DataDummy;
import com.yorren.moviecatalogue.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class HomeActivityTest {

    @Before
    public void setup() {
        ActivityScenario.launch(HomeActivity.class);
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());

    }

    //movies
    @Test
    public void loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition(20));
    }

    @Test
    public void loadDetailMovies() {

        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_popular)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_user_score)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()));
    }

    @Test
    public void loadFavoriteMovies() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.action_favorite)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.navigation_favorite)).perform(click());
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_popular)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_user_score)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()));
        onView(withId(R.id.action_favorite)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
    }

    //tv shows
    @Test
    public void loadTvShows() {
        onView(withId(R.id.navigation_tv)).perform(click());
        onView(withId(R.id.rv_tvshows)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.scrollToPosition(20));
    }

    @Test
    public void loadDetailTvShows() {
        onView(withId(R.id.navigation_tv)).perform(click());
        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_popular)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_user_score)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()));
    }

    @Test
    public void loadFavoriteTvShows() {
        onView(withId(R.id.navigation_tv)).perform(click());
        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.action_favorite)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.navigation_favorite)).perform(click());
        onView(withText(R.string.tv_shows)).perform(click());
        onView(withId(R.id.rv_tvshows)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_popular)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_user_score)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()));
        onView(withId(R.id.action_favorite)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
    }
}