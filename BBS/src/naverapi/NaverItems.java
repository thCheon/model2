package naverapi;

public class NaverItems {
	private String title; //공용
	private String link; //공용
	//-----------------------------blog
	private String description;
	private String bloggerName;
	private String bloggerLink;
	private String postDate;
	//------------------------------movie
	private String actor;//배우
	private String director;//감독
	private String image;//영화사진
	private String pubDate;//개봉년
	private String subtitle;//부제
	private String userRating;//평점
	
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getUserRating() {
		return userRating;
	}
	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}
	
	//-------------------------------------------------------------------------
	
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBloggerName() {
		return bloggerName;
	}
	public void setBloggerName(String bloggerName) {
		this.bloggerName = bloggerName;
	}
	public String getBloggerLink() {
		return bloggerLink;
	}
	public void setBloggerLink(String bloggerLink) {
		this.bloggerLink = bloggerLink;
	}
}
