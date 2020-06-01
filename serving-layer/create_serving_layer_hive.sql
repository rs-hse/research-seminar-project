CREATE SCHEMA IF NOT EXISTS speed_layer;

DROP VIEW IF EXISTS speed_layer.serving_twitter_tops;

CREATE VIEW speed_layer.serving_twitter_tops AS
    SELECT * FROM batch_layer.batch_twitter_tops
        UNION ALL
    SELECT * FROM speed_layer.speed_twitter_tops;




