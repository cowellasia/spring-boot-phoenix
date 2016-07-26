/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coole.co.data.model;

/**
 *
 * @author nguyendanghung
 */
public class Feed {

    public Feed() {

    }

    public Feed(String communityId, String title, String description, String url, String content, String sourceId, int type, String userId, String bucket, String thumb, Long likes, Long shares, Long pins, Long comments, Long views, String[] tags, int status, String videoId, String category, String[] subCategories, String photoId, String articleId, String lang, String source_type) {
        this.communityId = communityId;
        this.title = title;
        this.description = description;
        this.url = url;
        this.content = content;
        this.sourceId = sourceId;
        this.type = type;
        this.userId = userId;
        this.bucket = bucket;
        this.thumb = thumb;
        this.likes = likes;
        this.shares = shares;
        this.pins = pins;
        this.comments = comments;
        this.views = views;
        this.tags = tags;
        this.status = status;
        this.videoId = videoId;
        this.category = category;
        this.subCategories = subCategories;
        this.photoId = photoId;
        this.articleId = articleId;
        this.lang = lang;
        this.source_type = source_type;
    }

    private String communityId;
    private String title;
    private String description;
    private String url;
    private String content;
    private String sourceId;
    private int type;
    private String userId;

    private String bucket;
    private String thumb;
    private Long likes;
    private Long shares;
    private Long pins;
    private Long comments;
    private Long views;
    private String[] tags;

    private int status;
    private String videoId;
    private String category;
    private String[] subCategories;
    private String photoId;
    private String articleId;
    private String lang;
    private String source_type;

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getShares() {
        return shares;
    }

    public void setShares(Long shares) {
        this.shares = shares;
    }

    public Long getPins() {
        return pins;
    }

    public void setPins(Long pins) {
        this.pins = pins;
    }

    public Long getComments() {
        return comments;
    }

    public void setComments(Long comments) {
        this.comments = comments;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(String[] subCategories) {
        this.subCategories = subCategories;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getSource_type() {
        return source_type;
    }

    public void setSource_type(String source_type) {
        this.source_type = source_type;
    }

}
