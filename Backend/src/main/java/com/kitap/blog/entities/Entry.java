package com.kitap.blog.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "entry")
public class Entry {
    @Id
    @SequenceGenerator(name = "entry_sequence", sequenceName = "entry_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entry_sequence")
    @Column(name = "entry_id", updatable = false)
    private Long entry_id;
    @Column(name = "userid", nullable = false)
    private Long userid;
    @Column(name = "bookid", nullable = false)
    private Long bookid;
    @Column(name = "header", nullable = false)
    private String header;
    @Column(name = "entry", nullable = false, columnDefinition = "TEXT")
    private String entry;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

    public Entry(Long entry_id, Long userid, Long bookid, String header, String entry, Date createdOn, Date updatedOn) {
        this.entry_id = entry_id;
        this.userid = userid;
        this.bookid = bookid;
        this.header = header;
        this.entry = entry;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public Entry() {
    }

    public Long getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(Long entry_id) {
        this.entry_id = entry_id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getBookid() {
        return bookid;
    }

    public void setBookid(Long bookid) {
        this.bookid = bookid;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "entry_id=" + entry_id +
                ", userid=" + userid +
                ", bookid=" + bookid +
                ", header='" + header + '\'' +
                ", entry='" + entry + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
