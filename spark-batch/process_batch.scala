case class UserShort(id: Long,
                     id_str: String,
                     indices: Seq[Long],
                     name: String,
                     screen_name: String)

case class User(contributors_enabled: Option[Boolean],
                created_at: Option[String],
                description: Option[String],
                favourites_count: Option[BigInt],
                followers_count: Option[BigInt],
                friends_count: Option[BigInt],
                id_str: Option[String],
                name: Option[String],
                screen_name: Option[String],
                statuses_count: Option[BigInt],
                lang: Option[String],
                geo_enabled: Option[Boolean],
                verified: Option[Boolean])

case class HashTag(indices: Seq[Long],
                   text: String)

case class Entities(hashtags: Seq[HashTag],
                    user_mentions: Seq[UserShort])

case class QuotedStatus(created_at: String,
                        entities: Entities,
                        favorite_count: BigInt,
                        full_text: String,
                        lang: String,
                        retweet_count: BigInt,
                        user: User)

case class Tweet(created_at: String,
                 entities: Entities,
                 full_text: String,
                 //geo: Option[String],
                 // place: Option[String],
                 source: Option[String],
                 lang: Option[String],
                 id_str: String,
                 quoted_status: QuotedStatus,
                 retweet_count: Option[BigInt],
                 user: User)

import java.text.SimpleDateFormat
import java.util.Locale
import org.apache.spark.sql.{Dataset, SQLImplicits, SaveMode}
import scala.util.Try

case class TweetShort(created_at: String,
                      hashtags: Seq[String],
                      user_mentions: Seq[String],
                      full_text: String,
                      //geo: Option[String],
                      lang: Option[String],
                      // place: Option[String],
                      retweet_count: Option[BigInt],
                      source: Option[String],
                      user_created_at: Option[String],
                      user_default_profile: Option[String],
                      user_description: Option[String],
                      user_favourites_count: Option[BigInt],
                      user_followers_count: Option[BigInt],
                      user_friends_count: Option[BigInt],
                      user_geo_enabled: Option[Boolean],
                      user_lang: Option[String],
                      user_name: Option[String],
                      user_screen_name: Option[String],
                      user_statuses_count: Option[BigInt],
                      user_verified: Option[Boolean])


object TweetShort {

  val format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

  def apply(t: Tweet): TweetShort = new TweetShort(
    created_at = parseDate(t.created_at),
    hashtags = if (t.entities == null) Seq() else t.entities.hashtags.map(_.text),
    user_mentions = if (t.entities == null) Seq() else t.entities.user_mentions.map(_.screen_name),
    full_text = t.full_text,
    // geo = t.geo,
    lang = t.lang,
    // place = t.place,
    retweet_count = t.retweet_count,
    source = t.source,

    user_created_at = if (t.user == null) None else t.user.created_at.map(d => parseDate(d)),
    user_default_profile = if (t.user == null) None else t.user.description,
    user_description = if (t.user == null) None else t.user.description,
    user_favourites_count = if (t.user == null) None else t.user.favourites_count,
    user_followers_count = if (t.user == null) None else t.user.followers_count,
    user_friends_count = if (t.user == null) None else t.user.friends_count,
    user_geo_enabled = if (t.user == null) None else t.user.geo_enabled,
    user_lang = if (t.user == null) None else t.user.lang,
    user_name = if (t.user == null) None else t.user.name,
    user_screen_name = if (t.user == null) None else t.user.screen_name,
    user_statuses_count = if (t.user == null) None else t.user.statuses_count,
    user_verified = if (t.user == null) None else t.user.verified
  )

  def parseDate(date: String): String = {
    if (date == null || date.isEmpty) ""
    else Try {
      import org.joda.time.format.DateTimeFormat
      val formatter = DateTimeFormat.forPattern("EEE MMM dd HH:mm:ss yyyy")
      val formatter1 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
      val dt = formatter.parseDateTime(date.replace(" +0000", ""))

      dt.toString(formatter1)
    }.getOrElse("")
  }
}

val path = "hdfs://localhost:9000/batch_dataset/winter_olympics_200R.json"

import spark.implicits._
val dataset = spark.read.json(path).as[Tweet]


dataset
  .map(t => TweetShort(t))
  .write
  .mode(SaveMode.Overwrite)
  .saveAsTable("twitter_tops.batch_process")