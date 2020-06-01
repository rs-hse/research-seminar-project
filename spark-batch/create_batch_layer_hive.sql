CREATE SCHEMA IF NOT EXISTS batch_layer;

DROP TABLE IF EXISTS batch_layer.batch_twitter_tops;

CREATE EXTERNAL TABLE batch_layer.batch_twitter_tops (
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
    user_verified BOOLEAN)
ROW FORMAT SERDE 'org.apache.hive.hcatalog.data.JsonSerDe'
LOCATION '/apps/hive/warehouse/batch_layer/twitter_tops/';