package com.nmj.trendingNews.domain.guardian;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.base.MoreObjects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class GuardianArticle {

	private String type;
	private String sectionId;
	private String webTitle;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private Date webPublicationDate;
	private String id;
	private String webUrl;
	private String apiUrl;
	private String sectionName;

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

	public void setWebPublicationDate(final Date webPublicationDate) {
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

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("type", type) //
				.add("sectionId", sectionId) //
				.add("webTitle", webTitle) //
				.add("webPublicationDate", webPublicationDate) //
				.add("id", id) //
				.add("webUrl", webUrl) //
				.add("apiUrl", apiUrl) //
				.add("sectionName", sectionName) //
				.toString();
	}

}
