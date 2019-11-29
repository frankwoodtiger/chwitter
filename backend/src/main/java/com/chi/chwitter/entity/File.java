package com.chi.chwitter.entity;

import javax.persistence.*;

@Entity
/*
    Using single table strategy for best performance of dealing with binary data. The subclasses will all be in the same
    single table. For fields that does not belong to it, it will be null. Therefore, the subclass cannot have non null
    constraint as it will stop its sibling class from inserting.

    See more: https://thoughts-on-java.org/complete-guide-inheritance-strategies-jpa-hibernate/
 */
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "FILE_TYPE")
public class File extends AbstractEntity {
    @Column(nullable = false)
    private String fileId;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private long fileSize;

    @Lob
    @Column(nullable = false)
    private byte[] data;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
