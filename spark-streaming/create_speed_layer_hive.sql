CREATE SCHEMA IF NOT EXISTS speed_layer;

DROP TABLE IF EXISTS speed_layer.speed_twitter_tops;

CREATE TABLE speed_layer.speed_twitter_tops (
    created_at string,
    hashtags ARRAY<string>,
    user_mentions ARRAY<string>,
    full_text string,
    lang string,
    retweet_count BIGINT,
    source string,
    user_created_at string,
    user_default_profile string,
    user_description string,
    user_favourites_count BIGINT,
    user_followers_count BIGINT,
    user_friends_count BIGINT,
    user_geo_enabled BOOLEAN,
    user_name string,
    user_screen_name string,
    user_statuses_count BIGINT,
    user_verified BOOLEAN);