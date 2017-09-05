package com.protoplus.newscircle.Bean;

import java.io.Serializable;

public class SportChildSceneBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7874324535738656135L;
	public String imageLink;
	public String mainImageLink;
	public String publisher;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String category;
	public String getMainContentClass() {
		return mainContentClass;
	}

	public void setMainContentClass(String mainContentClass) {
		this.mainContentClass = mainContentClass;
	}

	private String mainContentClass;

	public String getSource() {
		return Source;
	}

	public void setSource(String source) {
		Source = source;
	}

	public String Source;
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public String getMainImageLink() {
		return mainImageLink;
	}
	public void setMainImageLink(String mainImageLink) {
		this.mainImageLink = mainImageLink;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String title;
	public String postId;
	public String description;
	public String pubDate;
	public SportChildSceneBean(){
		super();
	}
	public SportChildSceneBean(String ImageLink,String title,String postId,String description,String pubDate,String mainImageLink){
		this.description=description;
		this.pubDate=pubDate;
		this.imageLink=ImageLink;
		this.title=title;
		this.postId=postId;
		this.mainImageLink=mainImageLink;
	}
}
