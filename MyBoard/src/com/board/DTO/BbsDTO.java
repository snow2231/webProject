package com.board.DTO;

import java.sql.Timestamp;

public class BbsDTO {
    private int bbsId = 1, bbsHit = 0;
    private String bbsTitle, bbsContent, id;
    private Timestamp bbsDate;

    public int getBbsId() {
	return bbsId;
    }

    public void setBbsId(int bbsId) {
	this.bbsId = bbsId;
    }

    public int getBbsHit() {
	return bbsHit;
    }

    public void setBbsHit(int bbsHit) {
	this.bbsHit = bbsHit;
    }

    public String getBbsTitle() {
	return bbsTitle;
    }

    public void setBbsTitle(String bbsTitle) {
	this.bbsTitle = bbsTitle;
    }

    public String getBbsContent() {
	return bbsContent;
    }

    public void setBbsContent(String bbsContent) {
	this.bbsContent = bbsContent;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public Timestamp getBbsDate() {
	return bbsDate;
    }

    public void setBbsDate(Timestamp bbsDate) {
	this.bbsDate = bbsDate;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("BbsDTO [bbsId=" + bbsId + ", bbsHit=" + bbsHit + ", bbsTitle=" + bbsTitle + ", bbsContent="
		+ bbsContent + ", bbsDate=" + bbsDate + ", id=" + id + "]");
	return builder.toString();
    }
}
