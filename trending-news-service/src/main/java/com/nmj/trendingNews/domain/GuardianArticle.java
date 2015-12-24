package com.nmj.trendingNews.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GuardianArticle {
	private String type;// : "liveblog"
	private String sectionId; // : "business"
	private String webTitle; // : "UK economy weaker than expected - live"
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
	private Date webPublicationDate; // : "2015-12-23T10:00:13Z"
	private String id; // "business/live/2015/dec/23/uk-economy-expected-to-see-slower-growth-live"
	private String webUrl; // http://www.theguardian.com/business/live/2015/dec/23/uk-economy-expected-to-see-slower-growth-live
	private String apiUrl; // http://content.guardianapis.com/business/live/2015/dec/23/uk-economy-expected-to-see-slower-growth-live
	private String sectionName; // : "Business"

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(final String sectionId) {
		this.sectionId = sectionId;
	}

	public String getWebTitle() {
		return webTitle;
	}

	public void setWebTitle(final String webTitle) {
		this.webTitle = webTitle;
	}

	
	public Date getWebPublicationDate() {
		return webPublicationDate;
	}

	public void setWebPublicationDate(Date webPublicationDate) {
		this.webPublicationDate = webPublicationDate;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(final String webUrl) {
		this.webUrl = webUrl;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(final String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
	}

}
