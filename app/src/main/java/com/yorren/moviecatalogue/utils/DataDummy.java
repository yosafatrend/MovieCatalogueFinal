package com.yorren.moviecatalogue.utils;

import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;

import java.util.ArrayList;

public class DataDummy {
    public static ArrayList<MovieEntity> generateDummyMovies() {
        ArrayList<MovieEntity> movies = new ArrayList<>();

        movies.add(new MovieEntity("508943",
                "Luca",
                "",
                "2021-05-26",
                "8.2",
                "/620hnMVLu6RSZW6a5rwO8gqpt0t.jpg",
                "en",
                "7169.114",
                "Luca and his best friend Alberto experience an unforgettable summer on the Italian Riviera. But all the fun is threatened by a deeply-held secret: they are sea monsters from another world just below the water’s surface.",
                "/jTswp6KyDYKtvC52GbHagrZbGvD.jpg",
                false));

        movies.add(new MovieEntity("581726",
                "Infinite",
                "",
                "2021-09-08",
                "0",
                "/8ChCpCYxh9YXusmHwcE9YzP0TSG.jpg",
                "en",
                "5018.635",
                "Evan McCauley has skills he never learned and memories of places he has never visited. Self-medicated and on the brink of a mental breakdown, a secret group that call themselves “Infinites” come to his rescue, revealing that his memories are real.",
                "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                false));

        movies.add(new MovieEntity("520763",
                "A Quiet Place Part II",
                "",
                "2021-05-21",
                "7.7",
                "/z2UtGA1WggESspi6KOXeo66lvLx.jpg",
                "en",
                "5250.562",
                "Following the events at home, the Abbott family now face the terrors of the outside world. Forced to venture into the unknown, they realize that the creatures that hunt by sound are not the only threats that lurk beyond the sand path.",
                "/4q2hz2m8hubgvijz8Ez0T2Os2Yv.jpg",
                false));

        movies.add(new MovieEntity("385128",
                "F9",
                "",
                "2021-05-26",
                "7.9",
                "/xXHZeb1yhJvnSHPzZDqee0zfMb6.jpg",
                "en",
                "4155.593",
                "Dominic Toretto and his crew battle the most skilled assassin and high-performance driver they've ever encountered: his forsaken brother.",
                "/bOFaAXmWWXC3Rbv4u4uM9ZSzRXP.jpg",
                false));

        movies.add(new MovieEntity("337404",
                "Cruella",
                "",
                "2021-05-26",
                "8.5",
                "/8ChCpCYxh9YXusmHwcE9YzP0TSG.jpg",
                "en",
                "3565.228",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                false));

        return movies;
    }

    public static ArrayList<TvShowsEntity> generateDummyTvShows() {
        ArrayList<TvShowsEntity> tvshows = new ArrayList<>();

        tvshows.add(new TvShowsEntity("84958",
                "Loki",
                "",
                "2021-06-09",
                "8.1",
                "/ykElAtsOBoArgI1A8ATVH0MNve0.jpg",
                "en",
                "4823.75",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                false));

        tvshows.add(new TvShowsEntity("76669",
                "Elite",
                "",
                "2018-10-05",
                "8.2",
                "/1qOA3kMtQO8bjnW8M2smjA8tp10.jpg",
                "es",
                "2292.665",
                "When three working class kids enroll in the most exclusive school in Spain, the clash between the wealthy and the poor students leads to tragedy.",
                "/3NTAbAiao4JLzFQw6YxP1YZppM8.jpg",
                false));

        tvshows.add(new TvShowsEntity("119243",
                "iCarly",
                "",
                "2021-06-17",
                "8",
                "/5b5ZZGECl4FLDBKn3zJ6w6GBPRg.jpg",
                "en",
                "1787.534",
                "Ten years after signing off of one of TV's most iconic shows, Carly, Spencer, and Freddie are back, navigating the next chapter of their lives, facing the uncertainties of life in their twenties.",
                "/s5k4GqTUGXeUdScNrjpYfiQLKHI.jpg",
                false));

        tvshows.add(new TvShowsEntity("94722",
                "Tagesschau",
                "",
                "2021-06-09",
                "6.6",
                "/jWXrQstj7p3Wl5MfYWY6IHqRpDb.jpg",
                "de",
                "1798.157",
                "German daily news program, the oldest still existing program on German television.",
                "/7dFZJ2ZJJdcmkp05B9NWlqTJ5tq.jpg",
                false));

        tvshows.add(new TvShowsEntity("114868",
                "Record of Ragnarok",
                "",
                "2021-06-09",
                "8.9",
                "/iq5L971DFW1SwLJdvl7OpPI1QeZ.jpg",
                "ja",
                "1132.111",
                "Before eradicating humankind from the world, the gods give them one last chance to prove themselves worthy of survival. Let the Ragnarok battles begin.",
                "/kTs2WNZOukpWdNhoRlH94pSJ3xf.jpg",
                false));

        return tvshows;
    }
}
